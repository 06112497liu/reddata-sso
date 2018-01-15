/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.result;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 单点登陆用户服务结果
 * 
 * @author byron
 * @version $Id: BdssoUserResult.java, v 0.1 Sep 13, 2017 3:44:44 PM byron Exp $
 */
public class BdssoUserResult extends BdssoBaseResult {

    /** 序列化id */
    private static final long serialVersionUID = -7027908853639864869L;

    /** 用户id */
    private String            uid;

    /** 用户访问令牌 */
    private String            token;

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
     * Getter method for property <tt>token</tt>.
     * 
     * @return property value of token
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter method for property <tt>token</tt>.
     * 
     * @param token value to be assigned to property token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
