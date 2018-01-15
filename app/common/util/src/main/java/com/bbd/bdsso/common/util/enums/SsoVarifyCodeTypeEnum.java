/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.util.enums;

import com.bbd.commons.lang.enums.EnumInterface;

/**
 * 单点登陆验证码类型枚举
 * 
 * @author byron
 * @version $Id: SsoVarifyCodeTypeEnum.java, v 0.1 Sep 13, 2017 1:46:56 PM byron Exp $
 */
public enum SsoVarifyCodeTypeEnum implements EnumInterface {

    /** 用户注册 */
    ZERO("0", "用户注册"),

    /** 找回密码 */
    ONE("1", "找回密码");

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
    private SsoVarifyCodeTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 通过code获取枚举
     * 
     * @param code          代码
     * @return              枚举
     */
    public static SsoVarifyCodeTypeEnum getEnumByCode(String code) {
        for (SsoVarifyCodeTypeEnum entry : SsoVarifyCodeTypeEnum.values()) {
            if (entry.getCode().equals(code)) {
                return entry;
            }
        }
        return null;
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
