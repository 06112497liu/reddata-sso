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
 * 单点登陆权限VO
 * 
 * @author byron
 * @version $Id: SsoAuthVO.java, v 0.1 Sep 13, 2017 2:13:09 PM byron Exp $
 */
public class SsoAuthVO implements Serializable {

    /** 序列化id */
    private static final long serialVersionUID = -4499884696539594575L;

    /** 序号 */
    private String            id;

    /** 权限码 */
    private String            authCode;

    /** 权限名 */
    private String            authName;

    /** 应用id */
    private String            appId;

    /** 应用名 */
    private String            appName;

    /** 是否生效，可选1和0（1：生效，0：失效）*/
    private int               isEnable;

    /** 描述 */
    private String            description;

    /** 上一次修改者 */
    private String            lastModifier;

    /** 创建时间 */
    private Date              gmtCreate;

    /** 修改时间 */
    private Date              gmtModified;

    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(String id) {
        this.id = id;
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
     * Getter method for property <tt>authName</tt>.
     * 
     * @return property value of authName
     */
    public String getAuthName() {
        return authName;
    }

    /**
     * Setter method for property <tt>authName</tt>.
     * 
     * @param authName value to be assigned to property authName
     */
    public void setAuthName(String authName) {
        this.authName = authName;
    }

    /**
     * Getter method for property <tt>appId</tt>.
     * 
     * @return property value of appId
     */
    public String getAppId() {
        return appId;
    }

    /**
     * Setter method for property <tt>appId</tt>.
     * 
     * @param appId value to be assigned to property appId
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * Getter method for property <tt>isEnable</tt>.
     * 
     * @return property value of isEnable
     */
    public int getIsEnable() {
        return isEnable;
    }

    /**
     * Setter method for property <tt>isEnable</tt>.
     * 
     * @param isEnable value to be assigned to property isEnable
     */
    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * Getter method for property <tt>description</tt>.
     * 
     * @return property value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for property <tt>description</tt>.
     * 
     * @param description value to be assigned to property description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter method for property <tt>lastModifier</tt>.
     * 
     * @return property value of lastModifier
     */
    public String getLastModifier() {
        return lastModifier;
    }

    /**
     * Setter method for property <tt>lastModifier</tt>.
     * 
     * @param lastModifier value to be assigned to property lastModifier
     */
    public void setLastModifier(String lastModifier) {
        this.lastModifier = lastModifier;
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
     * Getter method for property <tt>gmtCreate</tt>.
     * 
     * @return property value of gmtCreate
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * Setter method for property <tt>gmtCreate</tt>.
     * 
     * @param gmtCreate value to be assigned to property gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * Getter method for property <tt>gmtModified</tt>.
     * 
     * @return property value of gmtModified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * Setter method for property <tt>gmtModified</tt>.
     * 
     * @param gmtModified value to be assigned to property gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
