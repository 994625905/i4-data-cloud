package cn.i4.data.cloud.mongo.core.service;

import cn.i4.data.cloud.mongo.base.service.MongoBaseService;
import cn.i4.data.cloud.mongo.core.MongoDBCS;
import cn.i4.data.cloud.mongo.core.entity.MongoRichText;
import org.springframework.stereotype.Service;

/**
 * @author wangjc
 * @title: MongoRichTextService
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/4-14:48
 */
@Service
public class MongoRichTextService extends MongoBaseService<MongoRichText> {

    @Override
    public String getDbName() {
        return MongoDBCS.DBS.SYSTEM;
    }

    @Override
    public String getTableName() {
        return MongoDBCS.CS_SYSTEM.RICH_TEXT;
    }

    @Override
    public Class<MongoRichText> getClazz() {
        return MongoRichText.class;
    }
}
