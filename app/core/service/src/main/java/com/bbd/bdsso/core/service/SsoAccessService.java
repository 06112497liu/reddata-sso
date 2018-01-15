/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.service;

import java.util.HashMap;

import com.bbd.bdsso.common.util.enums.VerifyCodeTypeEnum;

/**
 * SSO访问Token验证服务接口
 * 
 * @author byron
 * @version $Id: SsoAccessService.java, v 0.1 Sep 25, 2017 11:51:00 AM byron Exp $
 */
public interface SsoAccessService {

    /**
     * 创建新的token
     * 
     * @param userId    用户id
     * @param userName  用户名
     * @param email     邮箱
     * @param type      类型
     * @return
     */
    public String generateToken(String userId, String userName, String email, VerifyCodeTypeEnum type);

    /**
     * 删除token
     * 
     * @param userId
     * @return
     */
    public boolean deleteToken(String userId);

    /**
     * 更新token
     * 
     * @param userId
     * @return
     */
    public boolean updateToken(String userId);

    /**
     * 检查userId和token是否合法
     * 
     * @param userId
     * @param token
     * @return
     */
    public boolean checkValid(String userId, String token);

    /**
     * 通过uid和appName来获取authCode列表
     * 
     * @param userId
     * @param appName
     * @return
     */
    public HashMap<String, Boolean> getAuthCodeList(String userId, String appName);

    /**
     * 通过uid和appName来获取authCode列表
     *
     * @param userId
     * @param appName
     * @return
     */
    public HashMap<String, Boolean> getResourceCodeList(String userId, String appName);

    /**
     * 通过uid、appName和authCode来验证权限
     * 
     * @param userId
     * @param appName
     * @param authCode
     * @return
     */
    public HashMap<String, Boolean> getAuthCodeList(String userId, String appName, String authCode);

}
