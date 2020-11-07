package cn.i4.data.cloud.mongo.core.entity;

import cn.i4.data.cloud.mongo.base.entity.MongoBaseEntity;

/**
 * 周报的MongoDB
 * @author wangjc
 * @title: MongoWeekReport
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/6-17:23
 */
public class MongoWeekReport extends MongoBaseEntity {

    private static final long serialVersionUID = -2408512808482336067L;

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
