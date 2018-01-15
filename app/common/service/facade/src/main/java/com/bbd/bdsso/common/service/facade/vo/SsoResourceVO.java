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
 * 单点登陆资源VO
 * 
 * @author byron
 * @version $Id: SsoResourceVO.java, v 0.1 Sep 13, 2017 2:17:08 PM byron Exp $
 */
public class SsoResourceVO implements Serializable {

    /** 序列化id */
    private static final long serialVersionUID = -7313284340091249074L;

    /** 序号 */
    private String            id;

    /** 权限类型 */
    private int               type;

    /** 资源名称 */
    private String            resourceName;

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
     * Getter method for property <tt>type</tt>.
     * 
     * @return property value of type
     */
    public int getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     * 
     * @param type value to be assigned to property type
     */
    public void setType(int type) {
        this.type = type;
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
