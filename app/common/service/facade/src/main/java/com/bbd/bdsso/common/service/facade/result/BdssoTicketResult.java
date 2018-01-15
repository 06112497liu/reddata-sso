/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.result;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * ticket表识结果
 * 
 * @author byron
 * @version $Id: BdssoTicketResult.java, v 0.1 Oct 26, 2017 8:47:19 PM byron Exp $
 */
public class BdssoTicketResult extends BdssoBaseResult {

    /** 序列化id */
    private static final long serialVersionUID = -6108504317052544203L;

    /** ticket表识 */
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
