/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;

/**
 * 生成ticket标识
 * 
 * @author byron
 * @version $Id: SsoTicketVO.java, v 0.1 Oct 26, 2017 8:45:28 PM byron Exp $
 */
public class SsoTicketVO extends BdssoBaseResult {

    /** 序列化id */
    private static final long serialVersionUID = -4373284975890295189L;

    /** 标识 */
    private String            ticket;

    /**
     * Getter method for property <tt>ticket</tt>.
     * 
     * @return property value of ticket
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * Setter method for property <tt>ticket</tt>.
     * 
     * @param ticket value to be assigned to property ticket
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
