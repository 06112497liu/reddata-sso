/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 单点登陆权限扩展VO
 * 
 * @author byron
 * @version $Id: SsoAuthAddParamVO.java, v 0.1 Oct 24, 2017 10:45:57 AM byron Exp $
 */
public class SsoAuthAddParamVO extends SsoAuthVO {

    /** 序列化id */
    private static final long serialVersionUID = -1030439576689581698L;

    /** 应用名 */
    private String            appName;

    /**
     * Getter method for property <tt>appName</tt>.
     * 
     * @return property value of appName
     */
    @Override
    public String getAppName() {
        return appName;
    }

    /**
     * Setter method for property <tt>appName</tt>.
     * 
     * @param appName value to be assigned to property appName
     */
    @Override
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
