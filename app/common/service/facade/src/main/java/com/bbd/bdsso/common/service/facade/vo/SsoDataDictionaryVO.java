/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * SsoDataDictionaryVO
 * 
 * @author hyjun
 * @version $Id: SsoDataDictionaryVO.java, v 0.1 Nov 13, 2017 3:01:47 PM hyjun Exp $
 */
public class SsoDataDictionaryVO implements Serializable {

    /** 序列化id */
    private static final long serialVersionUID = 6660451599359626544L;

    /** code */
    private String            dataCode;

    /** name */
    private String            dataName;

    /** 顺序号 */
    private int            seqNum;

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public int getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(int seqNum) {
        this.seqNum = seqNum;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
