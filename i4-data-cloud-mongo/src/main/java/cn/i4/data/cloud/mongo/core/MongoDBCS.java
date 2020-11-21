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

        /** 数据中心系统 */
        String SYSTEM = "i4_data_cloud_system";
    }

    /**
     * 数据中心系统库下面的集合（表名）
     */
    public interface CS_SYSTEM{

        /**
         * 系统常量的集合
         */
        String SYSTEM_CONSTANT = "system_constant";

        /** 富文本草稿集合 */
        String RICH_TEXT = "rich_text";

        /** 周报的集合 */
        String WEEK_REPORT = "week_report";

        /** 活动的集合 */
        String PARTY_ACTIVITY = "party_activity";

        /** 历史公告的集合 */
        String HISTORY_NOTICE = "history_notice";

    }

}
