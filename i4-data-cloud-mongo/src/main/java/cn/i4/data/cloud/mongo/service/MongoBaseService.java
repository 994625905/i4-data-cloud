package cn.i4.data.cloud.mongo.service;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.i4.data.cloud.mongo.config.MongoConfig;
import cn.i4.data.cloud.mongo.entity.MongoBaseEntity;
import cn.i4.data.cloud.mongo.util.MongoUtil;
import com.alibaba.fastjson.JSON;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * mongoDB的业务基类,抽象类，泛型设置约束条件
 * @author wangjc
 * @title: MongoBaseService
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/910:21
 */
public abstract class MongoBaseService<T extends MongoBaseEntity>  {

    private static final Log logger = LogFactory.get(MongoBaseService.class);

    /**
     * 数据库名称
     * @return
     */
    public abstract String getDbName();

    /**
     * 表（集合）名称
     * @return
     */
    public abstract String getTableName();

    /**
     * 对应实体
     * @return
     */
    public abstract Class<T> getClazz();
    /**
     * 获取连接
     * @return
     */
    protected MongoCollection<Document> getCollection(){
        return MongoConfig.getMongoDS().getCollection(getDbName(),getTableName());
    }

    /**
     * 根据mongoId查询出对应的bean
     * @param mongoId
     * @return
     */
    public T selectByMongoId(String mongoId){
        MongoCollection<Document> table = getCollection();
        Document query = getIdQuery(mongoId);
        Document document = table.find(query).first();
        if(document == null){
            return null;
        }else{
            return documentToEntity(document);
        }
    }

    /**
     * 根据mongoId查询
     * @param mongoId
     * @return
     */
    public T selectByMongoId(ObjectId mongoId){
        return selectByMongoId(mongoId.toHexString());
    }

    /**
     * 根据实体对象字段值，获取单条
     * @param entity
     * @return
     */
    public T selectOne(T entity){
        List<T> list = listByEntity(entity);
        if(list.size() > 0){
            return list.get(0);
        }else {
            return null;
        }
    }

    /**
     * 根据实体对象字段查询
     * @param entity：为空时查全部，
     * @return
     */
    public List<T> listByEntity(T entity){
        return listByEntity(entity,null);
    }
    /**
     * 根据实体对象字段查询，并排序
     * @param entity：为null时，默认查询全部
     * @param sort 为null时默认不排序，设置排序字段为1升序，-1降序
     * @return
     */
    public List<T> listByEntity(T entity,T sort){
        try {
            MongoCollection<Document> table = getCollection();
            Document query = getDocumentByEntity(entity);
            logger.info("==============================listByEntity get query document is:[{}]",query.toJson().toString());
            final List<T> ret = new ArrayList<T>();

            Block<Document> processBlock = new Block<Document>() {
                public void apply(final Document document) {
                    ret.add(documentToEntity(document));
                }
            };

            if(sort == null){
                table.find(query).forEach(processBlock);
            }else{
                Document document = getDocumentByEntity(sort);
                table.find(query).sort(document).forEach(processBlock);
            }
            return ret;
        }catch (Exception e){
            logger.error("listByEntity error:[{}]",e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<T>();
    }

    /**
     * 查询所有
     * @return
     */
    public List<T> listAll(){
        return listAll(null);
    }

    /**
     * 查询所有并排序
     * @param sort
     * @return
     */
    public List<T> listAll(T sort){
        return listByEntity(null,sort);
    }

    /**
     * 插入单条记录
     * @param one
     * @return：返回mongoId
     */
    public String insertOne(T one){
        if(one == null){
            return null;
        }
        MongoCollection<Document> table = getCollection();

        Document docine = Document.parse(JSON.toJSONString(one));//转为json格式
        docine.remove("mongoId");
        docine.put("mongoId",new ObjectId());
        table.insertOne(docine);
        return docine.get("mongoId").toString();
    }

    /**
     * 更新数据，多余的字段，会在集合的这条记录上追加
     * @param one
     * @return
     */
    public Long updateOne(T one){
        Long num = 0L;
        try {
            MongoCollection<Document> table = getCollection();
            Document updateSet = getUpdateSet(one);
            logger.info("=======================updateOne mongoId is :[{}],get update set document is :[{}]",one.getMongoId().toHexString(),updateSet.toJson().toString());

            UpdateResult result = table.updateMany(getIdQuery(one.getMongoId()), updateSet);
            num = result.getModifiedCount();
            logger.info("updateOne:[{}]",num);
        }catch (Exception e){
            logger.info("updateOne error [{}]",e.getMessage());
        }
        return num;
    }

    /**
     * 批量更新
     * @param query：用于查询条件的实体对象（字段）
     * @param upset：用于更新的实体对象（字段）
     * @return
     */
    public Long updateBatch(T query,T upset){
        Long num = 0L;
        try {
            Document qfilter = getDocumentByEntity(query);
            logger.info("=======================updateBatch get query document is :[{}]",qfilter.toJson().toString());
            Document updateSet = getUpdateSet(upset);
            logger.info("=======================updateBatch get update set document is :[{}]",updateSet.toJson().toString());
            UpdateResult result = getCollection().updateMany(qfilter, updateSet);
            num = result.getModifiedCount();
            logger.info("updateBatch ModifiedCount;[{}]",num);
        }catch (Exception e){
            logger.info("updateBatch error [{}]",e.getMessage());
        }
        return num;
    }

    /**
     * 删除，根据mongoId
     * @param mongoId
     * @return
     */
    public Long deleteOne(ObjectId mongoId){
        return deleteOne(mongoId.toHexString());
    }
    /**
     * 删除，根据mongoId
     * @param mongoId
     * @return
     */
    public Long deleteOne(String mongoId){
        Document query = getIdQuery(mongoId);
        MongoCollection<Document> table = getCollection();
        DeleteResult result = table.deleteOne(query);
        return result.getDeletedCount();
    }
    /**
     * 批量删除，根据实体类的字段值
     * @param entity
     * @return
     */
    public Long deleteByEntity(T entity){
        if(entity == null){
            logger.info("deleteByEntity error: update entity is null");
            return null;
        }
        try {
            Document document = getDocumentByEntity(entity);
            logger.info("===========================deleteByEntity get query document is : [{}]",document.toJson().toString());
            if(document.size() <= 0){
                logger.error("deleteByEntity error:entity doesn't have any properties");
            }
            DeleteResult result = getCollection().deleteMany(document);
            return result.getDeletedCount();
        }catch (Exception e){
            logger.info("deleteByEntity error [{}] class:[{}]",e.getMessage(),entity.getClass().getName());
            return null;
        }
    }

    /**
     * 根据实体来获取Document
     * @param entity
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private Document getDocumentByEntity(T entity) throws IllegalAccessException,InvocationTargetException{
        Document query = new Document();
        if(entity == null){
            return query;
        }
        Field[] fields = MongoUtil.getFields(getClazz());
        for(Field f:fields){
            if("serialVersionUID".equals(f.getName())){//过滤掉序列化id
                continue;
            }
            f.setAccessible(true);
            Object obj = f.get(entity);
            if(obj == null){
                continue;
            }
            query.append(f.getName(),obj);
        }
        return query;
    }

    /**
     * 根据Document获取实体
     * @param document
     * @return
     */
    private T documentToEntity(Document document){
        try {
            T t = getClazz().newInstance();
            Field[] fields = MongoUtil.getFields(getClazz());
            for (Field f:fields){
                if(document.get(f.getName()) == null){
                    continue;
                }
                Object value = document.get(f.getName());
                if(value == null){
                    continue;
                }
                if(f.getName().equals("mongoId")){
                    value = new ObjectId(String.valueOf(value));
                }
                setFieldValue(t,f,value);
            }
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 给字段设置值
     * @param t
     * @param field
     * @param value
     */
    private void setFieldValue(T t,Field field,Object value){
        try {
            field.setAccessible(true);//设置可见
            field.set(t,value);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccess异常 [{}],[{}]",t.getClass(),field.getName());
            e.printStackTrace();
        }
    }

    /**
     * 获取更新后的document
     * @param entity
     * @return
     */
    private Document getUpdateSet(T entity){
        Document set = null;
        try {
            set  = getDocumentByEntity(entity);
            set.remove("mongoId");
            set = new Document("$set",set);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return set;
    }

    /**
     * 通过id获取document
     * @param mongoId
     * @return
     */
    private Document getIdQuery(String mongoId){
        return getIdQuery(new ObjectId(mongoId));
    }
    private Document getIdQuery(Object id){
        Document query = new Document();
        query.append("mongoId",id);
        return query;
    }

}
