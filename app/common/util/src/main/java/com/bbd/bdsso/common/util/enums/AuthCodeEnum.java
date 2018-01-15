/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.util.enums;

import com.bbd.commons.lang.enums.EnumInterface;

/**
 *  权限编码枚举
 * 
 * @author byron
 * @version $Id: AuthCodeEnum.java, v 0.1 Nov 2, 2017 10:29:51 AM byron Exp $
 */
public enum AuthCodeEnum implements EnumInterface {

    /** BDSSO系统管理员 */
    BDSSO_ADMIN_A("BDSSO_ADMIN_A", "BDSSO系统管理员"),

    /** BDSSO普通用户 */
    BDSSO_COMMON_USER_A("BDSSO_COMMON_USER_A", "BDSSO普通用户"),;

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
    private AuthCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 通过code获取枚举
     * 
     * @param code          代码
     * @return              枚举
     */
    public static AuthCodeEnum getEnumByCode(String code) {
        for (AuthCodeEnum entry : AuthCodeEnum.values()) {
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
