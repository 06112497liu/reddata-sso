/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.model.convertor;

import com.bbd.bdsso.common.dal.dataobject.SsoDataDictionaryDO;
import com.bbd.bdsso.common.service.facade.vo.SsoDataDictionaryVO;

import java.util.ArrayList;
import java.util.List;

/**
 * SSO数据字典模型转换器
 * 
 * @author hyjun
 * @version $Id: SsoDataDictionaryConvertor.java, v 0.1 Sep 26, 2017 4:12:31 PM hyjun Exp $
 */
public class SsoDataDictionaryConvertor {

    /**
     * ExDo2ExVo
     * 
     * @param ssoDataDictionaryDO
     * @return
     */
    public static SsoDataDictionaryVO convertExDo2ExVo(SsoDataDictionaryDO ssoDataDictionaryDO) {
        if (null == ssoDataDictionaryDO) {
            return null;
        }
        SsoDataDictionaryVO ssoDataDictionaryVO = new SsoDataDictionaryVO();
        ssoDataDictionaryVO.setDataCode(ssoDataDictionaryDO.getDataCode());
        ssoDataDictionaryVO.setDataName(ssoDataDictionaryDO.getDataName());
        ssoDataDictionaryVO.setSeqNum(ssoDataDictionaryDO.getSeqNum());
        return ssoDataDictionaryVO;
    }


    /**
     * Dos2Vos
     * 
     * @param list
     * @return
     */
    public static ArrayList<SsoDataDictionaryVO> convertDos2Vos(List<SsoDataDictionaryDO> list) {
        if (null == list) {
            return null;
        }

        ArrayList<SsoDataDictionaryVO> result = new ArrayList<SsoDataDictionaryVO>();
        for (SsoDataDictionaryDO ssoDataDictionaryDO : list) {
            result.add(convertExDo2ExVo(ssoDataDictionaryDO));
        }

        return result;
    }


}
