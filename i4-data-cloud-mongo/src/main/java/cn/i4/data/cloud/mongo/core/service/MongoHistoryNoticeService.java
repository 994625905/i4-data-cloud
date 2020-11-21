package cn.i4.data.cloud.mongo.core.service;

import cn.i4.data.cloud.mongo.base.service.MongoBaseService;
import cn.i4.data.cloud.mongo.core.MongoDBCS;
import cn.i4.data.cloud.mongo.core.entity.MongoHistoryNotice;
import org.springframework.stereotype.Service;

/**
 * 历史公告的富文本内容对应的服务
 * @author wangjc
 * @title: MongoHistoryMoticeService
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/21-15:36
 */
@Service
public class MongoHistoryNoticeService extends MongoBaseService<MongoHistoryNotice> {

    @Override
    public String getDbName() {
        return MongoDBCS.DBS.SYSTEM;
    }

    @Override
    public String getTableName() {
        return MongoDBCS.CS_SYSTEM.HISTORY_NOTICE;
    }

    @Override
    public Class<MongoHistoryNotice> getClazz() {
        return MongoHistoryNotice.class;
    }
}
