package cn.i4.data.cloud.mongo.base.entity;

import org.bson.types.ObjectId;

import java.io.Serializable;

/**
 * MongoDB对应实体的基类
 * @author wangjc
 * @title: MongoBaseEntity
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/3018:32
 */
public class MongoBaseEntity implements Serializable {

    private static final long serialVersionUID = 807001319777357164L;

    /**
     * 在MongoDB中，存储于集合中的每一个文档都需要一个唯一的 _id 字段作为 primary_key。如果一个插入文档操作遗漏了``_id``
     * 字段，MongoDB驱动会自动为``_id``字段生成一个 ObjectId，
     */
    private ObjectId mongoId;

    public ObjectId getMongoId() {
        return mongoId;
    }

    public void setMongoId(ObjectId mongoId) {
        this.mongoId = mongoId;
    }
}
