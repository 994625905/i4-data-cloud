package cn.i4.data.cloud.base.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import org.springframework.util.StringUtils;

/**
 * 富文本的工具类
 * @author wangjc
 * @title: RichTextUtil
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/5-11:27
 */
public class RichTextUtil {

    /** 自动获取的摘要长度 */
    private final static Integer ABSTRACT_LIMIT = 15;

    /**
     * 根据富文本获取纯文本信息
     * @param content
     * @return
     */
    public static String getTextContent(String content){
        String clearContent = HtmlUtil.cleanHtmlTag(StrUtil.trim(content));
        return StringUtils.trimAllWhitespace(clearContent);
    }

    /**
     * 根据富文本获取字数
     * @param content
     * @return
     */
    public static Integer getWordNumber(String content){
        String clearContent = HtmlUtil.cleanHtmlTag(StrUtil.trim(content));
        return StringUtils.trimAllWhitespace(clearContent).length();
    }

    /**
     * 根据富文本获取摘要信息
     * @param content
     * @return
     */
    public static String getExplain(String content){
        String clearContent = HtmlUtil.cleanHtmlTag(StrUtil.trim(content));
        clearContent = StringUtils.trimAllWhitespace(clearContent);
        return clearContent.substring(0,ABSTRACT_LIMIT);
    }

}
