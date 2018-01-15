/**  
 * BBD Service Inc
 * All Rights Reserved @2016
 *
 */
package com.bbd.bdsso.common.util.velocity;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbd.bdsso.common.util.SsoConstant;
import com.bbd.bdsso.common.util.email.MailSenderInfo;
import com.bbd.bdsso.common.util.email.SimpleMailSender;

/**
 * velocity模板类
 * 
 * @author weizhang@bbdservice.com
 * @version $Id: VelocityTemplate.java, v 0.1 2017年6月8日 下午4:43:18 weizhang@bbdservice.com Exp $
 */
public class VelocityTemplate {

    @Autowired
    private MailSenderInfo mailInfo;

    private Template getDefaultVM(String name) {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        ve.setProperty(RuntimeConstants.ENCODING_DEFAULT, "UTF-8");
        ve.setProperty(RuntimeConstants.INPUT_ENCODING, "UTF-8");
        ve.setProperty(RuntimeConstants.OUTPUT_ENCODING, "UTF-8");
        ve.init();
        return ve.getTemplate(SsoConstant.VELOCITY_BASE_PATH + name);
    }

    public String getTemplateString(String name, Map<String, Object> data) {
        // 设置变量
        VelocityContext ctx = new VelocityContext();
        for (Entry<String, Object> entry : data.entrySet()) {
            ctx.put(entry.getKey(), entry.getValue());
        }

        // 输出
        StringWriter sw = new StringWriter();
        Template template = getDefaultVM(name);
        template.merge(ctx, sw);
        return sw.toString();
    }

    /**
     * 发送邮件
     * 
     * @param sendTos         接收人列表
     * @param subject         邮件主题   
     * @param content         邮件内容
     * @return
     */
    public boolean sendEmail(String subject, List<String> emails, Map<String, Object> params, String vmTemplateName) {

        mailInfo.setToAddresses(emails);
        mailInfo.setSubject(subject);
        mailInfo.setContent(getTemplateString(vmTemplateName, params));

        SimpleMailSender sms = new SimpleMailSender();
        // 发送HTML格式
        return sms.sendHtmlMail(mailInfo);
    }

}