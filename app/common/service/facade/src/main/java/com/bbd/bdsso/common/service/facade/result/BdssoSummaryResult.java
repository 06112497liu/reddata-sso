/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.result;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * BDSSO登陆后展示的概要信息
 * 
 * @author byron
 * @version $Id: BdssoSummaryResult.java, v 0.1 Nov 22, 2017 3:07:26 PM byron Exp $
 */
public class BdssoSummaryResult extends BdssoBaseResult {

    /** 序列化id */
    private static final long serialVersionUID = -6533579970302485986L;

    /** 总用户数 */
    private int               totalUser;

    /** 在线人数 */
    private int               online;

    /**
     * Getter method for property <tt>totalUser</tt>.
     * 
     * @return property value of totalUser
     */
    public int getTotalUser() {
        return totalUser;
    }

    /**
     * Setter method for property <tt>totalUser</tt>.
     * 
     * @param totalUser value to be assigned to property totalUser
     */
    public void setTotalUser(int totalUser) {
        this.totalUser = totalUser;
    }

    /**
     * Getter method for property <tt>online</tt>.
     * 
     * @return property value of online
     */
    public int getOnline() {
        return online;
    }

    /**
     * Setter method for property <tt>online</tt>.
     * 
     * @param online value to be assigned to property online
     */
    public void setOnline(int online) {
        this.online = online;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
