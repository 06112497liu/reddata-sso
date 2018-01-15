/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.service;

/**
 * SSO用户管理服务
 * 
 * @author byron
 * @version $Id: SsoUserService.java, v 0.1 Sep 21, 2017 2:29:44 PM byron Exp $
 */
public interface SsoUserService {

    /**
     * 通过用户名来登陆
     * 
     * @param userName      用户名
     * @param userPassword  密码
     * @return
     */
    public String loginByUserName(String userName, String userPassword);

    /**
     * 通过邮箱来登陆
     * 
     * @param email         邮箱
     * @param userPassword  密码
     * @return
     */
    public String loginByEmail(String email, String userPassword);

    /**
     * 发送验证码
     * 
     * @param email                 邮箱
     * @return
     */
    public void sendVerifyCode(String email);

    /**
     * 用户注册
     * 
     * @param userName      用户名
     * @param email         邮箱
     * @param password      密码
     * @param verifyCode    验证码
     * @return  userId      用户id
     */
    public String regist(String userName, String email, String password, String verifyCode);

    /**
     * 重置密码
     * 
     * @param email         用户邮箱
     * @param password      密码
     * @param verifyCode    验证码
     * @return
     */
    public String resetPsw(String email, String password, String verifyCode);

    /**
     * 校验验证码是否过期
     * 
     * @param userId        用户id
     * @return
     */
    public boolean checkVerifyCode(String userId);

}
