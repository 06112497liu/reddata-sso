/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.dal.manual.dataobject;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 手工DAO
 * 
 * @author byron
 * @version $Id: ExSsoAuthResourceDO.java, v 0.1 Nov 7, 2017 7:14:31 PM byron Exp $
 */
public class ExSsoAuthResourceDO implements Serializable {

    /** 序列化id */
    private static final long serialVersionUID = -8765815300109351933L;

    /** 权限id */
    private Integer           authId;

    /** 权限编码 */
    private String            authName;

    /**
     * Getter method for property <tt>authId</tt>.
     * 
     * @return property value of authId
     */
    public Integer getAuthId() {
        return authId;
    }

    /**
     * Setter method for property <tt>authId</tt>.
     * 
     * @param authId value to be assigned to property authId
     */
    public void setAuthId(Integer authId) {
        this.authId = authId;
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
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
