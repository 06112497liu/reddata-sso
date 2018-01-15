/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.result;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bbd.bdsso.common.service.facade.vo.SsoAuthResourceVO;

/**
 * 权限资源列表
 * 
 * @author byron
 * @version $Id: BdssoAuthResourceResult.java, v 0.1 Nov 16, 2017 9:50:57 AM byron Exp $
 */
public class BdssoAuthResourceResult extends BdssoBaseResult {

    /** 序列化id */
    private static final long       serialVersionUID = -4274463349878058739L;

    /** 结果列表 */
    private List<SsoAuthResourceVO> resultList       = new ArrayList<SsoAuthResourceVO>();

    /**
     * Getter method for property <tt>resultList</tt>.
     * 
     * @return property value of resultList
     */
    public List<SsoAuthResourceVO> getResultList() {
        return resultList;
    }

    /**
     * Setter method for property <tt>resultList</tt>.
     * 
     * @param resultList value to be assigned to property resultList
     */
    public void setResultList(List<SsoAuthResourceVO> resultList) {
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
