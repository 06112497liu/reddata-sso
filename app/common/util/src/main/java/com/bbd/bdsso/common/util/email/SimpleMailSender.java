/**  
 * BBD Service Inc
 * All Rights Reserved @2016
 *
 */
package com.bbd.bdsso.common.util.email;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.bbd.bdsso.common.util.enums.BdssoResultEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;

/**
 * 邮件发送器
 * 
 * @author byron
 * @version $Id: SimpleMailSender.java, v 0.1 Nov 28, 2016 10:37:32 AM byron Exp $
 */
public class SimpleMailSender {

    /**
     * 发送文本邮件
     * 
     * @param mailInfo
     * @return
     */
    public boolean sendTextMail(MailSenderInfo mailInfo) {
        // 判断是否需要身份认证   
        MyAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        if (mailInfo.isValidate()) {
            // 如果需要身份认证，则创建一个密码验证器   
            authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session   
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try {
            // 根据session创建一个邮件消息   
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址   
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者   
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中   
            Address[] tos = getTos(mailInfo);
            mailMessage.addRecipients(Message.RecipientType.TO, tos);
            // 设置邮件消息的主题   
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间   
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容   
            String mailContent = mailInfo.getContent();
            mailMessage.setText(mailContent);
            // 发送邮件   
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException ex) {
            throw new BdssoBaseException(BdssoResultEnum.SEND_EMAIL_FAIL, ex.getMessage());
        }
    }

    /**
     * 以HTML格式发送邮件
     * 
     * @param mailInfo 待发送的邮件信息
     * @return
     */
    public boolean sendHtmlMail(MailSenderInfo mailInfo) {
        // 判断是否需要身份认证   
        MyAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        //如果需要身份认证，则创建一个密码验证器    
        if (mailInfo.isValidate()) {
            authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session   
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try {
            // 根据session创建一个邮件消息   
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址   
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者   
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中   
            Address[] tos = getTos(mailInfo);
            mailMessage.addRecipients(Message.RecipientType.TO, tos);
            // 设置邮件消息的主题   
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间   
            mailMessage.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象   
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart   
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容   
            html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容   
            mailMessage.setContent(mainPart);
            // 发送邮件   
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException ex) {
            throw new BdssoBaseException(BdssoResultEnum.SEND_EMAIL_FAIL, ex.getMessage());
        }
    }

    private Address[] getTos(MailSenderInfo mailInfo) throws AddressException {
        List<String> emails = mailInfo.getToAddresses();
        int size = emails.size();
        Address[] tos = new InternetAddress[size];
        for (int i = 0; i < size; i++) {
            tos[i] = new InternetAddress(emails.get(i));
        }
        return tos;
    }
}