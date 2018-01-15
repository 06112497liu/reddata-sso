/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.result;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bbd.bdsso.common.service.facade.vo.SsoExAccessVO;

/**
 * BDSSO扩展Access结果列表
 * 
 * @author byron
 * @version $Id: BdssoExAccessResult.java, v 0.1 Nov 13, 2017 3:06:03 PM byron Exp $
 */
public class BdssoExAccessResult extends BdssoBasePageResult {

    /** 序列化id */
    private static final long                        serialVersionUID = -2769215222479520045L;

    /** BDSSO扩展AccessVO */
    private ArrayList<SsoExAccessVO>                 resultList;

    /** 在线人数 */
    private int                                      online;

    /** 总人数 */
    private int                                      totalUser;

    /** 应用对于人数 */
    private HashMap<String, HashMap<String, String>> appResult;

    /**
     * Getter method for property <tt>resultList</tt>.
     * 
     * @return property value of resultList
     */
    public ArrayList<SsoExAccessVO> getResultList() {
        return resultList;
    }

    /**
     * Setter method for property <tt>resultList</tt>.
     * 
     * @param resultList value to be assigned to property resultList
     */
    public void setResultList(ArrayList<SsoExAccessVO> resultList) {
        this.resultList = resultList;
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
     * Getter method for property <tt>appResult</tt>.
     * 
     * @return property value of appResult
     */
    public HashMap<String, HashMap<String, String>> getAppResult() {
        return appResult;
    }

    /**
     * Setter method for property <tt>appResult</tt>.
     * 
     * @param appResult value to be assigned to property appResult
     */
    public void setAppResult(HashMap<String, HashMap<String, String>> appResult) {
        this.appResult = appResult;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
