package cn.i4.data.cloud.mongo.core.entity;

import cn.i4.data.cloud.mongo.base.entity.MongoBaseEntity;

/**
 * 富文本草稿
 * @author wangjc
 * @title: MongoRichText
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/4-14:44
 */
public class MongoRichText extends MongoBaseEntity {

    private static final long serialVersionUID = -262263187751267271L;

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
