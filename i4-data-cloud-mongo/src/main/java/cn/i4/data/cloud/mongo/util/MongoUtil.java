package cn.i4.data.cloud.mongo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MongoDB的专属util
 * @author wangjc
 * @title: MongoUtil
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/910:19
 */
public class MongoUtil {

    private static final Logger logger = LoggerFactory.getLogger(MongoUtil.class);

    /**
     * 线程安全的map
     */
    private static final Map<String, Field[]> FIELDS_CACHE = new ConcurrentHashMap<String, Field[]>();

    public static Field[] getFields(Class clazz){
        Field[] fields = FIELDS_CACHE.get(clazz.toString());
        if(null != fields){
            return fields;
        }else{
            fields = getFieldsDirectly(true,clazz);
            FIELDS_CACHE.put(clazz.toString(),fields);
            return fields;
        }
    }

    /**
     * 获取类的字段数组
     * @param withSuperClassFields：(是否直接带有父类)
     * @param claz
     * @return
     */
    public static Field[] getFieldsDirectly(boolean withSuperClassFields,Class<?> claz){
        Field[] allFields = null;
        Class<?> searchType = claz;
        Field[] declaredFields;
        while (searchType != null){
            declaredFields = searchType.getDeclaredFields();
            if(allFields == null){
                allFields = declaredFields;
            }else{
                allFields = append(allFields,declaredFields);
            }
            searchType = withSuperClassFields ? searchType.getSuperclass():null;
        }
        return allFields;
    }
    /**
     * 追加字段
     * @param buffer
     * @param newElements
     * @return
     */
    private static Field[] append(Field[] buffer,Field... newElements){
        if(isEmpty(buffer)){
            return newElements;
        }else{
            return insert(buffer,buffer.length,newElements);
        }
    }
    /**
     * 字段添加
     * @param array
     * @param index
     * @param newFieldlements
     * @return
     */
    private static Field[] insert(Field[] array,int index,Field... newFieldlements){
        if(isEmpty(newFieldlements)){
            return array;
        }
        if(isEmpty(array)){
            return newFieldlements;
        }
        final int len = length(array);
        if(index < 0){
            index = (index % len) + len;
        }

        Field[] result = newArray(array.getClass().getComponentType(),Math.max(len,index)+newFieldlements.length);
        System.arraycopy(array,0,result,0,Math.min(len,index));
        System.arraycopy(newFieldlements,0,result,index,newFieldlements.length);
        if(index < len){
            System.arraycopy(array,index,result,index+newFieldlements.length,len - index);
        }
        return result;
    }
    /**
     * 获取新的字段数组
     * @param componentType
     * @param newSize
     * @return
     */
    private static Field[] newArray(Class<?> componentType,int newSize){
        return (Field[]) Array.newInstance(componentType,newSize);
    }
    /**
     * 判断类的字段非空
     * @param array
     * @return
     */
    private static boolean isEmpty(Field... array){
        return array == null || array.length == 0;
    }
    /**
     * 获取指定数组类长度，私有
     * @param array
     * @return
     */
    private static int length(Object array) throws IllegalArgumentException{
        if(null == array){
            return 0;
        }else {
            return Array.getLength(array);
        }
    }

}
