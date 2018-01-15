/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.result;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * SSO扩展结果
 * 
 * @author byron
 * @version $Id: BdssoExtBaseResult.java, v 0.1 Oct 19, 2017 10:40:02 AM byron Exp $
 */
public class BdssoExtBaseResult<E> extends BdssoBaseResult {

    /** 序号id */
    private static final long             serialVersionUID = -2160563755659626893L;

    /** 消息类别 */
    private HashMap<String, ArrayList<E>> resultList;

    /**
     * Getter method for property <tt>resultList</tt>.
     * 
     * @return property value of resultList
     */
    public HashMap<String, ArrayList<E>> getResultList() {
        return resultList;
    }

    /**
     * Setter method for property <tt>resultList</tt>.
     * 
     * @param resultList value to be assigned to property resultList
     */
    public void setResultList(HashMap<String, ArrayList<E>> resultList) {
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
