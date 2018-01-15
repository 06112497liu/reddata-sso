/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * BDSSO扩展AccessVO
 * 
 * @author byron
 * @version $Id: SsoExAccessVO.java, v 0.1 Nov 13, 2017 3:01:47 PM byron Exp $
 */
public class SsoExAccessVO implements Serializable {

    /** 序列化id */
    private static final long serialVersionUID = 6660451599359626544L;

    /** 用户名 */
    private String            userName;

    /** 应用名 */
    private String            appName;

    /** 角色名 */
    private String            roleName;

    /** 权限码 */
    private String            authCode;

    /** 资源名 */
    private String            resourceName;

    /** 有效期 */
    private Date              validDate;

    /**
     * Getter method for property <tt>userName</tt>.
     * 
     * @return property value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter method for property <tt>userName</tt>.
     * 
     * @param userName value to be assigned to property userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * Getter method for property <tt>roleName</tt>.
     * 
     * @return property value of roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Setter method for property <tt>roleName</tt>.
     * 
     * @param roleName value to be assigned to property roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Getter method for property <tt>authCode</tt>.
     * 
     * @return property value of authCode
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * Setter method for property <tt>authCode</tt>.
     * 
     * @param authCode value to be assigned to property authCode
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    /**
     * Getter method for property <tt>resourceName</tt>.
     * 
     * @return property value of resourceName
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * Setter method for property <tt>resourceName</tt>.
     * 
     * @param resourceName value to be assigned to property resourceName
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * Getter method for property <tt>validDate</tt>.
     * 
     * @return property value of validDate
     */
    public Date getValidDate() {
        return validDate;
    }

    /**
     * Setter method for property <tt>validDate</tt>.
     * 
     * @param validDate value to be assigned to property validDate
     */
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
