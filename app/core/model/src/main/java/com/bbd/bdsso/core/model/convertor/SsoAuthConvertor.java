/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.model.convertor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.bbd.bdsso.common.dal.dataobject.SsoAuthDO;
import com.bbd.bdsso.common.dal.manual.dataobject.ExSsoAuthDO;
import com.bbd.bdsso.common.service.facade.vo.SsoAuthAddParamVO;
import com.bbd.bdsso.common.service.facade.vo.SsoAuthVO;

/**
 * SSO用户访问模型转换器
 * 
 * @author byron
 * @version $Id: SsoAuthConvertor.java, v 0.1 Sep 26, 2017 4:12:31 PM byron Exp $
 */
public class SsoAuthConvertor {

    /**
     * ExDo2Vo
     * 
     * @param ssoAuthDO
     * @return
     */
    public static SsoAuthVO convertDo2Vo(SsoAuthDO ssoAuthDO) {
        if (null == ssoAuthDO) {
            return null;
        }

        SsoAuthVO ssoAuthVO = new SsoAuthVO();
        ssoAuthVO.setId(ssoAuthDO.getId().toString());
        ssoAuthVO.setAuthCode(ssoAuthDO.getAuthCode());
        ssoAuthVO.setAuthName(ssoAuthDO.getAuthName());
        ssoAuthVO.setAppId(ssoAuthDO.getAppId().toString());
        ssoAuthVO.setIsEnable(ssoAuthDO.getIsEnable());
        ssoAuthVO.setDescription(ssoAuthDO.getDescription());
        ssoAuthVO.setLastModifier(ssoAuthDO.getLastModifier());
        ssoAuthVO.setGmtCreate(ssoAuthDO.getGmtCreate());
        ssoAuthVO.setGmtModified(ssoAuthDO.getGmtModified());

        return ssoAuthVO;
    }

    /**
     * Vo2AddParamVo
     * 
     * @param ssoAuthDO
     * @return
     */
    public static SsoAuthAddParamVO convertVo2AddParamVo(ExSsoAuthDO ssoAuthDO) {
        if (null == ssoAuthDO) {
            return null;
        }

        SsoAuthAddParamVO ssoAuthAddParamVO = new SsoAuthAddParamVO();
        ssoAuthAddParamVO.setId(ssoAuthDO.getId().toString());
        ssoAuthAddParamVO.setAuthCode(ssoAuthDO.getAuthCode());
        ssoAuthAddParamVO.setAuthName(ssoAuthDO.getAuthName());
        ssoAuthAddParamVO.setAppId(ssoAuthDO.getAppId().toString());
        ssoAuthAddParamVO.setIsEnable(ssoAuthDO.getIsEnable());
        ssoAuthAddParamVO.setDescription(ssoAuthDO.getDescription());
        ssoAuthAddParamVO.setLastModifier(ssoAuthDO.getLastModifier());
        ssoAuthAddParamVO.setGmtCreate(ssoAuthDO.getGmtCreate());
        ssoAuthAddParamVO.setGmtModified(ssoAuthDO.getGmtModified());
        ssoAuthAddParamVO.setAppName(ssoAuthDO.getAppName());

        return ssoAuthAddParamVO;
    }

    /**
     * Vo2Do
     * 
     * @param ssoAuthVO
     * @return
     */
    public static SsoAuthDO convertVo2Do(SsoAuthVO ssoAuthVO) {
        if (null == ssoAuthVO) {
            return null;
        }
        SsoAuthDO ssoAuthDO = new SsoAuthDO();
        ssoAuthDO.setAuthName(ssoAuthVO.getAuthName());
        ssoAuthDO.setAuthCode(ssoAuthVO.getAuthCode());
        ssoAuthDO.setAppId(Integer.parseInt(ssoAuthVO.getAppId()));
        ssoAuthDO.setIsEnable(ssoAuthVO.getIsEnable());
        ssoAuthDO.setDescription(ssoAuthVO.getDescription());
        ssoAuthDO.setLastModifier(ssoAuthVO.getLastModifier());
        ssoAuthDO.setGmtCreate(ssoAuthVO.getGmtCreate());
        ssoAuthDO.setGmtModified(ssoAuthVO.getGmtModified());
        ssoAuthDO.setId(StringUtils.isBlank(ssoAuthVO.getId()) ? 0 : Integer.parseInt(ssoAuthVO.getId()));

        return ssoAuthDO;
    }

    /**
     * Dos2Vos
     * 
     * @param list
     * @return
     */
    public static ArrayList<SsoAuthVO> convertDos2Vos(List<SsoAuthDO> list) {
        if (null == list) {
            return null;
        }

        ArrayList<SsoAuthVO> result = new ArrayList<SsoAuthVO>();
        for (SsoAuthDO ssoAuthDO : list) {
            result.add(convertDo2Vo(ssoAuthDO));
        }

        return result;
    }

    /**
     * ExDos2Vos
     * 
     * @param list
     * @return
     */
    public static ArrayList<SsoAuthAddParamVO> convertDos2AddParamVos(ArrayList<ExSsoAuthDO> list) {
        if (null == list) {
            return null;
        }

        ArrayList<SsoAuthAddParamVO> result = new ArrayList<SsoAuthAddParamVO>();
        for (ExSsoAuthDO ssoAuthDO : list) {
            result.add(convertVo2AddParamVo(ssoAuthDO));
        }

        return result;
    }

}
