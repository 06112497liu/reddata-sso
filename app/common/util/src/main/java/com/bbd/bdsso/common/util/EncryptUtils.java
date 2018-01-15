/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 *
 */
package com.bbd.bdsso.common.util;

/**
 * 加密工具类
 * 
 * @author byron
 * @version $Id: EncryptUtils.java, v 0.1 Sep 19, 2017 10:02:55 AM byron Exp $
 */
public class EncryptUtils {

    /**
     * 加密用户密码，先用AES加密再用MD5加密
     * 
     * @param password  密码
     * @return
     */
    public static String encryptPassword(String password) {
        return MD5Utils.textToMD5L32(AESUtils.encrypt(password, AESUtils.SECRETE));
    }

    /**
     * 生成登录Token
     * 
     * @param username  用户名
     * @return
     */
    public static String generateLoginToken(String username) {
        return MD5Utils.textToMD5L32(username + System.currentTimeMillis());
    }
}