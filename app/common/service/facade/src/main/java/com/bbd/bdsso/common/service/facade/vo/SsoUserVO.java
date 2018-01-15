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
 * 单点登陆用户VO
 * 
 * @author byron
 * @version $Id: SsoUserVO.java, v 0.1 Sep 13, 2017 2:29:12 PM byron Exp $
 */
public class SsoUserVO implements Serializable {

    /** 序列化id */
    private static final long serialVersionUID = -4890513523682162129L;

    /** 序号 */
    private String            id;

    /** 用户名 */
    private String            userName;

    /** 密码，存放的是加密后的密文 */
    private String            userPassword;

    /** 邮件 */
    private String            email;

    /** 是否生效，可选1和0（1：生效，0：失效） */
    private int               isEnable;

    /** 描述 */
    private String            description;

    /** 上一次修改者 */
    private String            lastModifier;

    /** 创建时间 */
    private Date              gmtCreate;

    /** 修改时间 */
    private Date              gmtModified;

    /** 人员信息ID */
    private String           infoId;

    /** 人员编号 */
    private String            persId;

    /** 姓名 */
    private String            name;


    /** 部门 */
    private String            deptName;


    /** 区域 */
    private String            region;


    /** 执法证号 */
    private String            lawNum;


    /** 职务 */
    private String            duty;


    /** 身份证号 */
    private String            cardId;


    /** 手机号 */
    private String            phone;

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
     * Getter method for property <tt>userPassword</tt>.
     * 
     * @return property value of userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Setter method for property <tt>userPassword</tt>.
     * 
     * @param userPassword value to be assigned to property userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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


    public String getPersId() {
        return persId;
    }

    public void setPersId(String persId) {
        this.persId = persId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLawNum() {
        return lawNum;
    }

    public void setLawNum(String lawNum) {
        this.lawNum = lawNum;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
