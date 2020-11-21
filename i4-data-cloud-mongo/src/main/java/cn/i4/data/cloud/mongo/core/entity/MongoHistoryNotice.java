package cn.i4.data.cloud.mongo.core.entity;

import cn.i4.data.cloud.mongo.base.entity.MongoBaseEntity;

/**
 * 历史公告的mongo存储
 * @author wangjc
 * @title: MongoHistoryNotice
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/21-15:33
 */
public class MongoHistoryNotice extends MongoBaseEntity {

    private static final long serialVersionUID = 5483797289285558674L;

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
