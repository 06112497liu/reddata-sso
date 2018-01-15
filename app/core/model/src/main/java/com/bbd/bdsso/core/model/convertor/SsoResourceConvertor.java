/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.model.convertor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.bbd.bdsso.common.dal.dataobject.SsoResourceDO;
import com.bbd.bdsso.common.service.facade.vo.SsoResourceVO;

/**
 * SSO资源模型转换器
 * 
 * @author byron
 * @version $Id: SsoResourceConvertor.java, v 0.1 Sep 26, 2017 4:12:31 PM byron Exp $
 */
public class SsoResourceConvertor {

    /**
     * Do2Vo
     * 
     * @param ssoResourceDO
     * @return
     */
    public static SsoResourceVO convertDo2Vo(SsoResourceDO ssoResourceDO) {
        if (null == ssoResourceDO) {
            return null;
        }

        SsoResourceVO ssoResourceVO = new SsoResourceVO();
        ssoResourceVO.setId(ssoResourceDO.getId().toString());
        ssoResourceVO.setType(ssoResourceDO.getType());
        ssoResourceVO.setResourceName(ssoResourceDO.getResourceName());
        ssoResourceVO.setIsEnable(ssoResourceDO.getIsEnable());
        ssoResourceVO.setDescription(ssoResourceDO.getDescription());
        ssoResourceVO.setLastModifier(ssoResourceDO.getLastModifier());
        ssoResourceVO.setGmtCreate(ssoResourceDO.getGmtCreate());
        ssoResourceVO.setGmtModified(ssoResourceDO.getGmtModified());

        return ssoResourceVO;
    }

    /**
     * Vo2Do
     * 
     * @param ssoResourceVO
     * @return
     */
    public static SsoResourceDO convertVo2Do(SsoResourceVO ssoResourceVO) {
        if (null == ssoResourceVO) {
            return null;
        }
        SsoResourceDO ssoResourceDO = new SsoResourceDO();
        ssoResourceDO.setType(ssoResourceVO.getType());
        ssoResourceDO.setResourceName(ssoResourceVO.getResourceName());
        ssoResourceDO.setIsEnable(ssoResourceVO.getIsEnable());
        ssoResourceDO.setDescription(ssoResourceVO.getDescription());
        ssoResourceDO.setLastModifier(ssoResourceVO.getLastModifier());
        ssoResourceDO.setGmtCreate(ssoResourceVO.getGmtCreate());
        ssoResourceDO.setGmtModified(ssoResourceVO.getGmtModified());
        ssoResourceDO.setId(StringUtils.isBlank(ssoResourceVO.getId()) ? 0 : Integer.parseInt(ssoResourceVO.getId()));

        return ssoResourceDO;
    }

    /**
     * Dos2Vos
     * 
     * @param list
     * @return
     */
    public static ArrayList<SsoResourceVO> convertDos2Vos(List<SsoResourceDO> list) {
        if (null == list) {
            return null;
        }

        ArrayList<SsoResourceVO> result = new ArrayList<SsoResourceVO>();
        for (SsoResourceDO ssoResourceDO : list) {
            result.add(convertDo2Vo(ssoResourceDO));
        }

        return result;
    }

}
