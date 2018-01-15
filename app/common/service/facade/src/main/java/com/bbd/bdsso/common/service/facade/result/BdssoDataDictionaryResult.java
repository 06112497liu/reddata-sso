/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.result;

import com.bbd.bdsso.common.service.facade.vo.SsoAccessVO;
import com.bbd.bdsso.common.service.facade.vo.SsoDataDictionaryVO;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典服务结果
 * 
 * @author hyjun
 * @version $Id: BdssoDataDictionaryResult.java, v 0.1 Sep 26, 2017 10:15:35 AM hyjun Exp $
 */
public class BdssoDataDictionaryResult extends BdssoBaseResult {

    /** 序列化id */
    private static final long serialVersionUID = 7257891780545232683L;

    /** 结果列表 */
    private List<SsoDataDictionaryVO> resultList       = new ArrayList<SsoDataDictionaryVO>();

    /**
     * Getter method for property <tt>resultList</tt>.
     *
     * @return property value of resultList
     */
    public List<SsoDataDictionaryVO> getResultList() {
        return resultList;
    }

    /**
     * Setter method for property <tt>resultList</tt>.
     *
     * @param resultList value to be assigned to property resultList
     */
    public void setResultList(List<SsoDataDictionaryVO> resultList) {
        this.resultList = resultList;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
