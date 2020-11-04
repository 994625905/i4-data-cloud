package cn.i4.data.cloud.mongo.core;

/**
 * mongoDB的数据结构类，数据库--集合
 * @author wangjc
 * @title: MongoDBCS
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/4-14:39
 */
public class MongoDBCS {

    /**
     * 数据库名称，可能会有多个库
     */
    public interface DBS{

        /** 数据云系统 */
        String SYSTEM = "i4_data_cloud_system";
    }

    /**
     * 数据云系统库下面的集合（表名）
     */
    public interface CS_SYSTEM{

        /** 富文本草稿库 */
        String RICH_TEXT = "rich_text";
    }

}
