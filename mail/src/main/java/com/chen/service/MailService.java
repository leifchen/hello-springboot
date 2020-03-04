package com.chen.service;

/**
 * 邮件服务接口
 * <p>
 * @Author LeifChen
 * @Date 2020-03-04
 */
public interface MailService {

    /**
     * 发送邮件
     * @param to        收件人
     * @param subject   主题
     * @param content   内容
     * @param filePath  附件
     * @param imagePath 图片路径
     * @param imageId   图片id
     */
    void sendMail(String to, String subject, String content, String filePath, String imagePath, String imageId);
}
