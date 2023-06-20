package com.mxx910.blog.service;

import com.mxx910.blog.model.DTO.MailDTO;

/**
 * 邮件服务接口
 * @author mxx910
 */
public interface EmailService {

    /**
     * 发送简单邮件
     *
     * @param mailDTO 邮件信息
     */
    void sendSimpleMail(MailDTO mailDTO);

    /**
     * 发送HTML邮件
     *
     * @param mailDTO 邮件信息
     */
    void sendHtmlMail(MailDTO mailDTO);
}
