package cn.i4.data.cloud.mongo.core.service;

import cn.i4.data.cloud.mongo.base.service.MongoBaseService;
import cn.i4.data.cloud.mongo.core.MongoDBCS;
import cn.i4.data.cloud.mongo.core.entity.MongoPartyActivity;
import org.springframework.stereotype.Service;

/**
 * 活动的富文本内容对应的服务
 * @author wangjc
 * @title: MongoPartyActivityService
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/14-16:23
 */
@Service
public class MongoPartyActivityService extends MongoBaseService<MongoPartyActivity> {

    @Override
    public String getDbName() {
        return MongoDBCS.DBS.SYSTEM;
    }

    @Override
    public String getTableName() {
        return MongoDBCS.CS_SYSTEM.PARTY_ACTIVITY;
    }

    @Override
    public Class<MongoPartyActivity> getClazz() {
        return MongoPartyActivity.class;
    }
}
