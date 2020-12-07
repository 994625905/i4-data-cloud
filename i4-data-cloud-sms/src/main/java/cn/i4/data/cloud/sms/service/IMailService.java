package cn.i4.data.cloud.sms.service;

/**
 * 封装一个发邮件的服务
 * @author wangjc
 * @title: IMailService
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/30-16:34
 */
public interface IMailService {

    /**
     * 发送文本邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送HTML邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    public void sendHtmlMail(String to, String subject, String content);



    /**
     * 发送带附件的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param filePath 附件
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath);

}
