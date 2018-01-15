/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.util.enums;

import com.bbd.commons.lang.enums.EnumInterface;

/**
 * 单点登陆结果枚举
 * 
 * @author byron
 * @version $Id: BdssoResultEnum.java, v 0.1 Sep 12, 2017 4:49:23 PM byron Exp $
 */
public enum BdssoResultEnum implements EnumInterface {

    /** 成功 */
    SUCCESS("SUCCESS", "成功"),

    /** 数据库异常 */
    DATABASE_EXCEPTION("DATABASE_EXCEPTION", "数据库异常"),

    /** 未知异常 */
    UNKNOWN_EXCEPTION("UNKNOWN_EXCEPTION", "未知异常"),

    /** 系统异常 */
    SYSTEM_ERROR("SYSTEM_ERROR", "系统异常"),

    /** 空值对象 */
    NULL_OBJECT("NULL_OBJECT", "对象为NULL"),

    /** 空对象 */
    EMPTY_OBJECT("EMPTY_OBJECT", "空对象"),

    /** 该算法不存在 */
    NO_SUCH_ALGORITHM("NO_SUCH_ALGORITHM", "该算法不存在"),

    /** 无效的token */
    INVAID_TOKEN("INVAID_TOKEN", "无效的token"),

    /** uid或者token错误 */
    ERROR_UID_OR_TOKEN("ERROR_UID_OR_TOKEN", "uid或者token错误"),

    /** 用户不存在 */
    USER_NOT_EXIST("USER_NOT_EXIST", "用户不存在或密码错误"),

    /** 基础数据不存在 */
    DATA_DICTIONARY_NOT_EXIST("DATA_DICTIONARY_NOT_EXIST", "基础数据不存在"),

    /** 旧密码错误 */
    WRONG_OLD_PASSWORD("WRONG_OLD_PASSWORD", "旧密码错误"),

    /** 发送邮件失败 */
    SEND_EMAIL_FAIL("SEND_EMAIL_FAIL", "发送邮件失败"),

    /** 用户被禁止访问 */
    FORBIDDEN_USER("FORBIDDEN_USER", "用户被禁止访问"),

    /** 应用名已存在 */
    APP_NAME_ALREADY_EXIST("APP_NAME_ALREADY_EXIST", "应用名已存在"),

    /** 应用不存在 */
    APP_NOT_EXIST("APP_NOT_EXIST", "应用不存在"),

    /** 角色名已存在 */
    ROLE_NAME_ALREADY_EXIST("ROLE_NAME_ALREADY_EXIST", "角色名已存在"),

    /** 权限名已存在 */
    AUTH_NAME_ALREADY_EXIST("AUTH_NAME_ALREADY_EXIST", "权限名已存在"),

    /** 权限码已存在 */
    AUTH_CODE_ALREADY_EXIST("AUTH_CODE_ALREADY_EXIST", "权限码已存在"),

    /** 资源名已存在 */
    RESOURCE_NAME_ALREADY_EXIST("RESOURCE_NAME_ALREADY_EXIST", "资源名已存在"),

    /** 文件上传失败 */
    ERROR_UPLOAD_FILE("ERROR_UPLOAD_FILE", "文件上传失败"),

    /** 字符串错误 */
    ERROR_STRING("ERROR_STRING", "字符串错误"),

    /** 用户没有权限 */
    AUTH_CODE_ERROR("AUTH_CODE_ERROR", "用户没有权限"),

    /** 邮件格式错误 */
    ERROR_EMAIL_REGULAR_EXPRES("ERROR_EMAIL_REGULAR_EXPRES", "邮件格式错误"),

    /** 查询结果为空 */
    EMPTY_RESULT("EMPTY_RESULT", "查询结果为空"),

    /** 邮箱后缀不支持 */
    ERROR_EMAIL_SUFFIX("ERROR_EMAIL_SUFFIX", "邮箱后缀不支持"),

    /** 邮箱已存在 */
    EMAIL_ALREADY_EXIST("EMAIL_ALREADY_EXIST", "邮箱已存在"),

    /** 用户名已存在 */
    USERNAME_ALREADY_EXIST("USERNAME_ALREADY_EXIST", "用户名已存在"),

    /** 邮箱不存在 */
    EMAIL_NOT_EXIST("EMAIL_NOT_EXIST", "邮箱不存在"),

    /** 同步帐号不能忘记密码，请到数据铁笼修改密码 */
    USER_NOT_FORGET_EXIST("USER_NOT_FORGET_EXIST", "同步帐号不能忘记密码，请到数据铁笼找回密码"),

    /** 不允许删除 */
    NOT_ALLOWED("NOT_ALLOWED", "不允许删除"),

    /** 用户角色对应关系不存在 */
    USER_ROLE_NOT_EXIST("USER_ROLE_NOT_EXIST", "用户角色对应关系不存在"),

    /** 角色权限对应关系不存在 */
    ROLE_AUTH_NOT_EXIST("ROLE_AUTH_NOT_EXIST", "角色权限对应关系不存在"),

    /** 权限资源对应关系不存在 */
    AUTH_RESOURCE_NOT_EXIST("AUTH_RESOURCE_NOT_EXIST", "权限资源对应关系不存在"),

    /** ticket失效 */
    INVAID_TICKET("INVAID_TICKET", "ticket失效"),;

    /** 枚举值 */
    private String code;

    /** 枚举描述 */
    private String desc;

    /**
     * 构造方法
     *
     * @param code
     * @param desc
     */
    private BdssoResultEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    /**
     * 获取日志信息
     *
     * @return
     */
    public String getLogMessage() {
        StringBuffer sb = new StringBuffer();
        sb.append(toString());
        sb.append("[");
        sb.append(code);
        sb.append(":");
        sb.append(desc);
        sb.append("]");
        return sb.toString();
    }
}
