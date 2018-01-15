/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.vo;

import java.util.ArrayList;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bbd.bdsso.common.service.facade.result.BdssoBasePageResult;

/**
 * SSO扩展列表接口
 * 
 * @author byron
 * @version $Id: SsoExListVo.java, v 0.1 Oct 19, 2017 4:41:41 PM byron Exp $
 */
public class SsoExListVO<E> extends BdssoBasePageResult {

    /** 序列化id */
    private static final long     serialVersionUID = -210295556366129563L;

    /** 结果列表 */
    private ArrayList<SsoExVO<E>> resultList;

    /**
     * Getter method for property <tt>resultList</tt>.
     * 
     * @return property value of resultList
     */
    public ArrayList<SsoExVO<E>> getResultList() {
        return resultList;
    }

    /**
     * Setter method for property <tt>resultList</tt>.
     * 
     * @param resultList value to be assigned to property resultList
     */
    public void setResultList(ArrayList<SsoExVO<E>> resultList) {
        this.resultList = resultList;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
