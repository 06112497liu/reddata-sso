/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.result;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 单点登陆结果基类
 * 
 * @author byron
 * @version $Id: BdssoBaseResult.java, v 0.1 Sep 12, 2017 5:01:44 PM byron Exp $
 */
public class BdssoBaseResult implements Serializable {

    /** 序列化id */
    private static final long serialVersionUID = -7292085758036916063L;

    /** 调用是否成功 */
    private boolean           success          = false;

    /** 调用结果码 */
    private String            resultCode;

    /** 调用结果描述 */
    private String            resultDesc;

    /**
     * 默认构造器
     */
    public BdssoBaseResult() {
        super();
    }

    /**
     * 带参数的构造器
     * 
     * @param success
     * @param resultCode
     * @param resultDesc
     */
    public BdssoBaseResult(boolean success, String resultCode, String resultDesc) {
        super();
        this.success = success;
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
    }

    /**
     * Getter method for property <tt>success</tt>.
     * 
     * @return property value of success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Setter method for property <tt>success</tt>.
     * 
     * @param success value to be assigned to property success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Getter method for property <tt>resultCode</tt>.
     * 
     * @return property value of resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Setter method for property <tt>resultCode</tt>.
     * 
     * @param resultCode value to be assigned to property resultCode
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * Getter method for property <tt>resultDesc</tt>.
     * 
     * @return property value of resultDesc
     */
    public String getResultDesc() {
        return resultDesc;
    }

    /**
     * Setter method for property <tt>resultDesc</tt>.
     * 
     * @param resultDesc value to be assigned to property resultDesc
     */
    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
