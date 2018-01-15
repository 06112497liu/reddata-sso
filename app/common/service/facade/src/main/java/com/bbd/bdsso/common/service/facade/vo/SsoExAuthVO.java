/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 单点登陆权限扩展VO
 * 
 * @author byron
 * @version $Id: SsoExAuthVO.java, v 0.1 Nov 13, 2017 6:32:32 PM byron Exp $
 */
public class SsoExAuthVO implements Serializable {

    /** 序列化id */
    private static final long serialVersionUID = 8727757937487843372L;

    /** 待校验待权限列表 */
    private ArrayList<String> authCodeList;

    /** 待校验待的用户名 */
    private String            appName;

    /**
     * Getter method for property <tt>authCodeList</tt>.
     * 
     * @return property value of authCodeList
     */
    public ArrayList<String> getAuthCodeList() {
        return authCodeList;
    }

    /**
     * Setter method for property <tt>authCodeList</tt>.
     * 
     * @param authCodeList value to be assigned to property authCodeList
     */
    public void setAuthCodeList(ArrayList<String> authCodeList) {
        this.authCodeList = authCodeList;
    }

    /**
     * Getter method for property <tt>appName</tt>.
     * 
     * @return property value of appName
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Setter method for property <tt>appName</tt>.
     * 
     * @param appName value to be assigned to property appName
     */
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
