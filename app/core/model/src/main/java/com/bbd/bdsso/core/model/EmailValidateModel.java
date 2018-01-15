/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 邮箱验证模型
 * 
 * @author byron
 * @version $Id: EmailValidateModel.java, v 0.1 Nov 9, 2017 3:49:09 PM byron Exp $
 */
public class EmailValidateModel implements Serializable {

    /** 序列化id */
    private static final long serialVersionUID = -897008985683895977L;

    /** 邮箱正则表达式 */
    private String            emailEegularExp;

    /**
     * Getter method for property <tt>emailEegularExp</tt>.
     * 
     * @return property value of emailEegularExp
     */
    public String getEmailEegularExp() {
        return emailEegularExp;
    }

    /**
     * Setter method for property <tt>emailEegularExp</tt>.
     * 
     * @param emailEegularExp value to be assigned to property emailEegularExp
     */
    public void setEmailEegularExp(String emailEegularExp) {
        this.emailEegularExp = emailEegularExp;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
