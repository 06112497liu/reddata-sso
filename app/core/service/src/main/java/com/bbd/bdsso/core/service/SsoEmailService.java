/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.service;

/**
 * 发送邮件服务
 * 
 * @author byron
 * @version $Id: SsoEmailService.java, v 0.1 Sep 26, 2017 3:10:43 PM byron Exp $
 */
public interface SsoEmailService {

    /**
     * 发送重置密码邮件
     * 
     * @param email     邮箱
     */
    public void sendResetPwdEmail(String email);

    /**
     * 发送注册验证码
     * 
     * @param email                 邮箱
     */
    public void sendVerifyCodeEmail(String email);

}
