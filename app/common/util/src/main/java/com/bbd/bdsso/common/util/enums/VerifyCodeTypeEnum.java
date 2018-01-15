/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.util.enums;

import com.bbd.commons.lang.enums.EnumInterface;

/**
 * 注册类型枚举
 * 
 * @author byron
 * @version $Id: VerifyCodeTypeEnum.java, v 0.1 Sep 21, 2017 2:43:22 PM byron Exp $
 */
public enum VerifyCodeTypeEnum implements EnumInterface {

    /** 注册 */
    REGIST("0", "注册"),

    /** 找回密码 */
    FIND_PASSOWRD("1", "找回密码");

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
    private VerifyCodeTypeEnum(String code, String desc) {
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
     * 通过code获取枚举
     * 
     * @param code          代码
     * @return              枚举
     */
    public static VerifyCodeTypeEnum getEnumByCode(String code) {
        for (VerifyCodeTypeEnum entry : VerifyCodeTypeEnum.values()) {
            if (entry.getCode().equals(code)) {
                return entry;
            }
        }
        return null;
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
