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
 * 用户账号验证VO
 * 
 * @author byron
 * @version $Id: SsoAccountValidVO.java, v 0.1 Sep 13, 2017 1:35:21 PM byron Exp $
 */
public class SsoAccountValidVO implements Serializable {

    /** 序列化id */
    private static final long serialVersionUID = -7344025018761041627L;

    /** 序号 */
    private String            id;

    /** 用户邮箱 */
    private String            email;

    /** 验证码 */
    private String            verifyCode;

    /** 是否被验证过（可选0或1），0:未被验证，1:已被验证 */
    private int               isVerified;

    /** 验证码类型（可选0或1），0:用户注册，1:找回密码 */
    private int               type;

    /** 验证码有效期 */
    private Date              validDate;

    /** 描述 */
    private String            description;

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
     * Getter method for property <tt>email</tt>.
     * 
     * @return property value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for property <tt>email</tt>.
     * 
     * @param email value to be assigned to property email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method for property <tt>verifyCode</tt>.
     * 
     * @return property value of verifyCode
     */
    public String getVerifyCode() {
        return verifyCode;
    }

    /**
     * Setter method for property <tt>verifyCode</tt>.
     * 
     * @param verifyCode value to be assigned to property verifyCode
     */
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    /**
     * Getter method for property <tt>isVerified</tt>.
     * 
     * @return property value of isVerified
     */
    public int getIsVerified() {
        return isVerified;
    }

    /**
     * Setter method for property <tt>isVerified</tt>.
     * 
     * @param isVerified value to be assigned to property isVerified
     */
    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
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
