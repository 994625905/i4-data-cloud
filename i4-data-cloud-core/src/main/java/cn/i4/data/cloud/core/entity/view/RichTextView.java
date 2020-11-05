package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.RichTextModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-04 15:55:45
 */
public class RichTextView extends BaseView<RichTextView> {

	private static final long serialVersionUID = 1604476546096L;

	public RichTextView(RichTextModel model) {
		this.id = model.getId();
		this.userId = model.getUserId();
		this.title = model.getTitle();
		this.cover = model.getCover();
		this.wordNumber = model.getWordNumber();
		this.explainNote = model.getExplainNote();
		this.createTime = model.getCreateTime();
		this.updateTime = model.getUpdateTime();
		this.mongoId = model.getMongoId();
	}

	public RichTextView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 
	 */
	@TableField("user_id")
	private Integer userId;

	/**
	 * 标题
	 */
	@TableField("title")
	private String title;

	/**
	 * 封面图片
	 */
	@TableField("cover")
	private String cover;

	/**
	 * 文本数字
	 */
	@TableField("word_number")
	private Integer wordNumber;

	/**
	 * 注释说明（默认取前15个字符）
	 */
	@TableField("explain_note")
	private String explainNote;

	/**
	 * 
	 */
	@TableField("create_time")
	private Long createTime;

	/**
	 * 
	 */
	@TableField("update_time")
	private Long updateTime;

	/**
	 * 文本存储的MongoId
	 */
	@TableField("mongo_id")
	private String mongoId;

	/** 补充字段 */
	private String createTimeStr;
	private String updateTimeStr;
	private String realName;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getCover() {
		return this.cover;
	}

	public void setWordNumber(Integer wordNumber) {
		this.wordNumber = wordNumber;
	}

	public Integer getWordNumber() {
		return this.wordNumber;
	}

	public void setExplainNote(String explainNote) {
		this.explainNote = explainNote;
	}

	public String getExplainNote() {
		return this.explainNote;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateTime() {
		return this.updateTime;
	}

	public void setMongoId(String mongoId) {
		this.mongoId = mongoId;
	}

	public String getMongoId() {
		return this.mongoId;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
}
