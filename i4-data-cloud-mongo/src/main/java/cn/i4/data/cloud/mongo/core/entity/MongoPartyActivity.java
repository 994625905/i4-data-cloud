package cn.i4.data.cloud.mongo.core.entity;

import cn.i4.data.cloud.mongo.base.entity.MongoBaseEntity;

/**
 * 活动的富文本内容
 * @author wangjc
 * @title: MongoPartyActivity
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/14-16:22
 */
public class MongoPartyActivity extends MongoBaseEntity {

    private static final long serialVersionUID = 1638690488182362959L;

    /**
     * 内容，作用富文本编辑器
     */
    private String content;

    /**
     * md内容，作用Markdown编辑器
     */
    private String mdContent;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMdContent() {
        return mdContent;
    }

    public void setMdContent(String mdContent) {
        this.mdContent = mdContent;
    }
}
