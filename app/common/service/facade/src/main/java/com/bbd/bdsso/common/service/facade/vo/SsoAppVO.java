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
 * 单点登陆接入的应用
 * 
 * @author byron
 * @version $Id: SsoAppVO.java, v 0.1 Sep 13, 2017 2:02:56 PM byron Exp $
 */
public class SsoAppVO implements Serializable {

    /** 序列化id */
    private static final long serialVersionUID = -2984406420766838781L;

    /** 序号 */
    private String            id;

    /** 应用名 */
    private String            appName;

    /** 应用logo */
    private String            appLogo;

    /** sso首页展示的图片 */
    private String            appPic;

    /** 是否生效，可选1和0（1：生效，0：失效） */
    private int               isEnable;

    /** 描述 */
    private String            description;

    /** 最近一次修改者 */
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
     * Getter method for property <tt>appLogo</tt>.
     * 
     * @return property value of appLogo
     */
    public String getAppLogo() {
        return appLogo;
    }

    /**
     * Setter method for property <tt>appLogo</tt>.
     * 
     * @param appLogo value to be assigned to property appLogo
     */
    public void setAppLogo(String appLogo) {
        this.appLogo = appLogo;
    }

    /**
     * Getter method for property <tt>appPic</tt>.
     * 
     * @return property value of appPic
     */
    public String getAppPic() {
        return appPic;
    }

    /**
     * Setter method for property <tt>appPic</tt>.
     * 
     * @param appPic value to be assigned to property appPic
     */
    public void setAppPic(String appPic) {
        this.appPic = appPic;
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
