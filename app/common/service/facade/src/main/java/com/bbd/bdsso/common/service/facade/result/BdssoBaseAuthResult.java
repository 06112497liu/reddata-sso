/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.result;

import java.util.HashMap;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * SSO基础认证
 * 
 * @author byron
 * @version $Id: BdssoBaseAuthResult.java, v 0.1 Oct 11, 2017 10:02:28 AM byron Exp $
 */
public class BdssoBaseAuthResult extends BdssoBaseResult {

    /** 序列化id */
    private static final long        serialVersionUID = -4005537022770815014L;

    /** 验证结果 */
    private HashMap<String, Boolean> authResult       = new HashMap<String, Boolean>();

    /** 用户id */
    private String                   uid;

    /** 用户名 */
    private String                   userName;

    /**
     * Getter method for property <tt>uid</tt>.
     * 
     * @return property value of uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * Setter method for property <tt>uid</tt>.
     * 
     * @param uid value to be assigned to property uid
     */
    public void setUid(String uid) {
        this.uid = uid;
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
     * Getter method for property <tt>authResult</tt>.
     * 
     * @return property value of authResult
     */
    public HashMap<String, Boolean> getAuthResult() {
        return authResult;
    }

    /**
     * Setter method for property <tt>authResult</tt>.
     * 
     * @param authResult value to be assigned to property authResult
     */
    public void setAuthResult(HashMap<String, Boolean> authResult) {
        this.authResult = authResult;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
