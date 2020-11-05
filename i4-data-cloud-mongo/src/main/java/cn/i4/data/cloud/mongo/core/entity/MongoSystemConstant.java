package cn.i4.data.cloud.mongo.core.entity;

import cn.i4.data.cloud.mongo.base.entity.MongoBaseEntity;

/**
 * 系统常量的MongoDB集合
 * @author wangjc
 * @title: MongoSystemConstant
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/5-16:37
 */
public class MongoSystemConstant extends MongoBaseEntity {

    private static final long serialVersionUID = 8838601276536431610L;

    /**
     * 内容，作用富文本编辑器
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
