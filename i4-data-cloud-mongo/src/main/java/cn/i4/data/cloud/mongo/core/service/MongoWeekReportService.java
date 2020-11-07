package cn.i4.data.cloud.mongo.core.service;

import cn.i4.data.cloud.mongo.base.service.MongoBaseService;
import cn.i4.data.cloud.mongo.core.MongoDBCS;
import cn.i4.data.cloud.mongo.core.entity.MongoRichText;
import cn.i4.data.cloud.mongo.core.entity.MongoWeekReport;
import org.springframework.stereotype.Service;

/**
 * @author wangjc
 * @title: MongoRichTextService
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/4-14:48
 */
@Service
public class MongoWeekReportService extends MongoBaseService<MongoWeekReport> {

    @Override
    public String getDbName() {
        return MongoDBCS.DBS.SYSTEM;
    }

    @Override
    public String getTableName() {
        return MongoDBCS.CS_SYSTEM.WEEK_REPORT;
    }

    @Override
    public Class<MongoWeekReport> getClazz() {
        return MongoWeekReport.class;
    }
}
