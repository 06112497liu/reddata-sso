/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.util;

/**
 * SSO常量
 * 
 * @author byron
 * @version $Id: SsoConstant.java, v 0.1 Sep 21, 2017 4:28:27 PM byron Exp $
 */
public class SsoConstant {

    /** token有效期，单位：毫秒 */
    public static final int    TOKEN_VALID_DURATION                     = 30 * 60 * 1000;

    /** 验证码有效期，单位：毫秒 */
    public static final int    VERIFY_CODE_VALID_DURATION               = 30 * 60 * 1000;

    /** 本地ip */
    public static final String LOCAL_ADDRESS                            = "127.0.0.1";

    /** localhost */
    public static final String LOCALHOST                                = "localhost";

    /** 有效 */
    public static final int    VALID                                    = 1;

    /** 失效 */
    public static final int    NOT_VALID                                = 0;

    /** 邮箱最大长度 */
    public static final int    MAX_EMAIL_LENGH                          = 64;

    /** 名称最大长度 */
    public static final int    MAX_NAME_LENGH                           = 32;

    /** 描述最大长度 */
    public static final int    MAX_DESC_LENGH                           = 64;

    /** 密码最大长度 */
    public static final int    MAX_PSW_LENGH                            = 64;

    /** 邮箱符号 */
    public static final String AT                                       = "@";

    /** &符号 */
    public static final String AND                                      = "&";

    /** =符号 */
    public static final String EQUAL                                    = "=";

    /** ,符号 */
    public static final String COMMA                                    = ",";

    /** ticket */
    public static final String TICKET                                   = "ticket";

    /** token */
    public static final String TOKEN                                    = "token";

    /** uid */
    public static final String UID                                      = "uid";

    /** bdsso */
    public static final String BDSSO                                    = "bdsso";

    /** 发送注册验证码模板 */
    public static final String SEND_REGIST_VERIFY_CODE_VM               = "sendRegistVerifyCode.vm";

    /** 重置密码模板 */
    public static final String RESET_PASSWORD_VM                        = "resetPassword.vm";

    /** velocity模板的基础路径 */
    public static final String VELOCITY_BASE_PATH                       = "template/";

    /** 注册 */
    public static final String REGIST                                   = "账户注册";

    /** 重置密码 */
    public static final String RESET_PASSWORD                           = "重置密码";

    /** 注册验证码 */
    public static final String SEND_REGIST_VERIFY_CODE_SUBJECT          = "注册验证码";

    /** 找回密码验证码 */
    public static final String FIND_PASSWORD_REGIST_VERIFY_CODE_SUBJECT = "找回密码验证码";

    /** 验证码类型错误 */
    public static final String ERROR_VERIFY_CODE_TYPE                   = "验证码类型错误";

    /** 验证码错误 */
    public static final String ERROR_VERIFY_CODE                        = "验证码错误";

    /** 验证码失效 */
    public static final String INVALID_VERIFY_CODE                      = "验证码失效";

    /** 用户注册 */
    public static final String USER_REGIST                              = "用户注册";

    /** 初始化 */
    public static final String INIT                                     = "初始化";

    /** 无效的token */
    public static final String INVALID_TOKEN                            = "无效的token";

    /** 无效的uid */
    public static final String INVALID_UID                              = "无效的uid";

    /** 无效的ticket */
    public static final String INVALID_TICKET                           = "无效ticket";

    /** 无效的uid和token */
    public static final String INVALID_UID_OR_TOKEN                     = "无效的uid或token";

    /** 请求错误 */
    public static final String ERROR_REQUEST                            = "请求错误";

    /** 手动添加用户 */
    public static final String MANUAL_ADD_USER                          = "手动添加用户";

    /** 未配置 */
    public static final String UN_CONFIG                                = "未配置";

}
