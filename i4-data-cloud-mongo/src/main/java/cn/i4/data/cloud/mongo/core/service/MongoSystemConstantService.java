package cn.i4.data.cloud.mongo.core.service;

import cn.i4.data.cloud.mongo.base.service.MongoBaseService;
import cn.i4.data.cloud.mongo.core.MongoDBCS;
import cn.i4.data.cloud.mongo.core.entity.MongoSystemConstant;
import org.springframework.stereotype.Service;

/**
 * 系统常量集合的service
 * @author wangjc
 * @title: MongoSystemConstantService
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/5-16:38
 */
@Service
public class MongoSystemConstantService extends MongoBaseService<MongoSystemConstant> {

    @Override
    public String getDbName() {
        return MongoDBCS.DBS.SYSTEM;
    }

    @Override
    public String getTableName() {
        return MongoDBCS.CS_SYSTEM.SYSTEM_CONSTANT;
    }

    @Override
    public Class<MongoSystemConstant> getClazz() {
        return MongoSystemConstant.class;
    }
}
