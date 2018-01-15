/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.result;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * SSO分页查询结果
 * 
 * @author byron
 * @version $Id: BdssoForPageResult.java, v 0.1 Sep 26, 2017 4:57:28 PM byron Exp $
 */
public class BdssoForPageResult<E> extends BdssoBasePageResult {

    /** 序列化id */
    private static final long serialVersionUID = -6755772387157295560L;

    /** 结果列表 */
    private List<E>           resultList       = new ArrayList<E>();

    /**
     * Getter method for property <tt>resultList</tt>.
     * 
     * @return property value of resultList
     */
    public List<E> getResultList() {
        return resultList;
    }

    /**
     * Setter method for property <tt>resultList</tt>.
     * 
     * @param resultList value to be assigned to property resultList
     */
    public void setResultList(List<E> resultList) {
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
