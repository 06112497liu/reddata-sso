/**  
 * BBD Service Inc
 * All Rights Reserved @2016
 *
 */
package com.bbd.bdsso.common.util.email;

import java.util.List;
import java.util.Properties;

/**
 * 邮件信息
 * 
 * @author byron
 * @version $Id: MailSenderInfo.java, v 0.1 Nov 28, 2016 10:34:23 AM byron Exp $
 */
public class MailSenderInfo {

    /** 发送邮件的服务器的IP */
    private String       mailServerHost;

    /** 发送邮件的服务器的端口 */
    private String       mailServerPort;

    /** 邮件发送者的地址 */
    private String       fromAddress;

    /** 邮件接收者的地址 */
    private List<String> toAddresses;

    /** 登陆邮件发送服务器的用户名 */
    private String       userName;

    /** 登陆邮件发送服务器的密码 */
    private String       password;

    /** 是否需要身份验证 */
    private boolean      validate = true;

    /** 邮件主题 */
    private String       subject;

    /** 邮件的文本内容 */
    private String       content;

    /** 邮件附件的文件名 */
    private String[]     attachFileNames;

    public MailSenderInfo() {
        super();
    }

    public MailSenderInfo(String mailServerHost, String mailServerPort, String fromAddress, String userName,String password) {
        this.mailServerHost = mailServerHost;
        this.mailServerPort = mailServerPort;
        this.fromAddress = fromAddress;
        this.userName = userName;
        this.password = password;
    }

    /**  
      * 获得邮件会话属性  
      */
    public Properties getProperties() {
        Properties p = new Properties();
        p.put("mail.smtp.host", getMailServerHost());
        p.put("mail.smtp.port", getMailServerPort());
        p.put("mail.smtp.auth", validate ? "true" : "false");
        return p;
    }

    /**
     * Getter method for property <tt>mailServerHost</tt>.
     * 
     * @return property value of mailServerHost
     */
    public String getMailServerHost() {
        return mailServerHost;
    }

    /**
     * Setter method for property <tt>mailServerHost</tt>.
     * 
     * @param mailServerHost value to be assigned to property mailServerHost
     */
    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    /**
     * Getter method for property <tt>mailServerPort</tt>.
     * 
     * @return property value of mailServerPort
     */
    public String getMailServerPort() {
        return mailServerPort;
    }

    /**
     * Setter method for property <tt>mailServerPort</tt>.
     * 
     * @param mailServerPort value to be assigned to property mailServerPort
     */
    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    /**
     * Getter method for property <tt>fromAddress</tt>.
     * 
     * @return property value of fromAddress
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * Setter method for property <tt>fromAddress</tt>.
     * 
     * @param fromAddress value to be assigned to property fromAddress
     */
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
     * Getter method for property <tt>toAddresses</tt>.
     * 
     * @return property value of toAddresses
     */
    public List<String> getToAddresses() {
        return toAddresses;
    }

    /**
     * Setter method for property <tt>toAddresses</tt>.
     * 
     * @param toAddresses value to be assigned to property toAddresses
     */
    public void setToAddresses(List<String> toAddresses) {
        this.toAddresses = toAddresses;
    }

    /**
     * Getter method for property <tt>userName</tt>.
     * 
     * @return property value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter method for property <tt>userName</tt>.
     * 
     * @param userName value to be assigned to property userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter method for property <tt>password</tt>.
     * 
     * @return property value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for property <tt>password</tt>.
     * 
     * @param password value to be assigned to property password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method for property <tt>validate</tt>.
     * 
     * @return property value of validate
     */
    public boolean isValidate() {
        return validate;
    }

    /**
     * Setter method for property <tt>validate</tt>.
     * 
     * @param validate value to be assigned to property validate
     */
    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    /**
     * Getter method for property <tt>subject</tt>.
     * 
     * @return property value of subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Setter method for property <tt>subject</tt>.
     * 
     * @param subject value to be assigned to property subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Getter method for property <tt>content</tt>.
     * 
     * @return property value of content
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter method for property <tt>content</tt>.
     * 
     * @param content value to be assigned to property content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Getter method for property <tt>attachFileNames</tt>.
     * 
     * @return property value of attachFileNames
     */
    public String[] getAttachFileNames() {
        return attachFileNames;
    }

    /**
     * Setter method for property <tt>attachFileNames</tt>.
     * 
     * @param attachFileNames value to be assigned to property attachFileNames
     */
    public void setAttachFileNames(String[] attachFileNames) {
        this.attachFileNames = attachFileNames;
    }

}