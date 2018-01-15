/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import com.bbd.bdsso.common.dal.daointerface.SsoAccountValidDAO;
import com.bbd.bdsso.common.dal.daointerface.SsoUserDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoAccountValidDO;
import com.bbd.bdsso.common.util.DateUtils;
import com.bbd.bdsso.common.util.EncryptUtils;
import com.bbd.bdsso.common.util.SsoConstant;
import com.bbd.bdsso.common.util.enums.IsVerifiedEnum;
import com.bbd.bdsso.common.util.enums.VerifyCodeTypeEnum;
import com.bbd.bdsso.common.util.velocity.VelocityTemplate;
import com.bbd.bdsso.core.service.SsoEmailService;

/**
 * SSO邮件服务
 * 
 * @author byron
 * @version $Id: SsoEmailServiceImpl.java, v 0.1 Sep 26, 2017 3:12:06 PM byron Exp $
 */
public class SsoEmailServiceImpl implements SsoEmailService {

    /** 异步发送线程池 */
    @Autowired
    private TaskExecutor       taskExecutor;

    /** velocity模板*/
    @Autowired
    private VelocityTemplate   velocityTemplate;

    /** SSO账户验证DAO */
    @Autowired
    private SsoAccountValidDAO ssoAccountValidDAO;

    /** SSO用户中心DAO */
    @Autowired
    private SsoUserDAO         ssoUserDAO;

    /** 
     * @see com.bbd.bdsso.core.service.SsoEmailService#sendResetPwdEmail(java.lang.String)
     */
    @Override
    public void sendResetPwdEmail(String email) {

        // 生成新的密码
        String newPassword = EncryptUtils.generateLoginToken(email).substring(0, 7).toUpperCase();

        // vm展示的数据
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("email", email);
        params.put("newPassword", newPassword);

        ArrayList<String> list = new ArrayList<String>();
        list.add(email);

        // 更新密码
        ssoUserDAO.updateByEmail(EncryptUtils.encryptPassword(newPassword), new Date(), email);

        // 异步发送邮件
        taskExecutor.execute(new Runnable() {

            @Override
            public void run() {
                velocityTemplate.sendEmail(SsoConstant.RESET_PASSWORD, list, params, SsoConstant.RESET_PASSWORD_VM);
            }
        });
    }

    /** 
     * @see com.bbd.bdsso.core.service.SsoEmailService#sendVerifyCodeEmail(java.lang.String)
     */
    @Override
    public void sendVerifyCodeEmail(String email) {

        // 生成新的验证码
        String verifyCode = EncryptUtils.generateLoginToken(email).substring(0, 5).toUpperCase();

        // vm展示的数据
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("email", email);
        params.put("verifyCode", verifyCode);
        params.put("duration", SsoConstant.VERIFY_CODE_VALID_DURATION / (1000 * 60));

        // 收件人
        ArrayList<String> receiveList = new ArrayList<String>();
        receiveList.add(email);

        // 异步发送邮件
        taskExecutor.execute(new Runnable() {

            @Override
            public void run() {

                velocityTemplate.sendEmail(SsoConstant.REGIST, receiveList, params, SsoConstant.SEND_REGIST_VERIFY_CODE_VM);

            }
        });

        // 更新email对应的token，将之前的记录设置为过期
        ssoAccountValidDAO.updateByEmailAndType(Integer.parseInt(IsVerifiedEnum.YES.getCode()), new Date(), email, Integer.parseInt(VerifyCodeTypeEnum.REGIST.getCode()));

        // 构造实体
        SsoAccountValidDO ssoAccountValidDO = new SsoAccountValidDO();
        ssoAccountValidDO.setEmail(email);
        ssoAccountValidDO.setVerifyCode(verifyCode);
        ssoAccountValidDO.setIsVerified(Integer.parseInt(IsVerifiedEnum.NO.getCode()));
        ssoAccountValidDO.setValidDate(DateUtils.plusDuration(SsoConstant.TOKEN_VALID_DURATION));
        ssoAccountValidDO.setGmtCreate(new Date());
        ssoAccountValidDO.setGmtModified(new Date());
        ssoAccountValidDO.setDescription(SsoConstant.REGIST);
        ssoAccountValidDO.setType(Integer.parseInt(VerifyCodeTypeEnum.REGIST.getCode()));
        ssoAccountValidDAO.insert(ssoAccountValidDO);
    }

}
