/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.model.convertor;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.bbd.bdsso.common.dal.dataobject.SsoAccessDO;
import com.bbd.bdsso.common.dal.manual.dataobject.ExSsoAccessDO;
import com.bbd.bdsso.common.service.facade.vo.SsoAccessVO;
import com.bbd.bdsso.common.service.facade.vo.SsoExAccessVO;
import com.bbd.bdsso.common.util.SsoConstant;

/**
 * SSO用户访问模型转换器
 * 
 * @author byron
 * @version $Id: SsoAccessConvertor.java, v 0.1 Sep 26, 2017 4:12:31 PM byron Exp $
 */
public class SsoAccessConvertor {

    /**
     * ExDo2ExVo
     * 
     * @param exSsoAccessDO
     * @return
     */
    public static SsoExAccessVO convertExDo2ExVo(ExSsoAccessDO exSsoAccessDO) {
        if (null == exSsoAccessDO) {
            return null;
        }
        SsoExAccessVO ssoExAccessVO = new SsoExAccessVO();
        ssoExAccessVO.setAppName(exSsoAccessDO.getAppName());
        ssoExAccessVO.setAuthCode(exSsoAccessDO.getAuthCode());
        ssoExAccessVO.setResourceName(StringUtils.isBlank(exSsoAccessDO.getResourceName()) ? SsoConstant.UN_CONFIG : exSsoAccessDO.getResourceName());
        ssoExAccessVO.setRoleName(exSsoAccessDO.getRoleName());
        ssoExAccessVO.setUserName(exSsoAccessDO.getUserName());
        ssoExAccessVO.setValidDate(exSsoAccessDO.getValidDate());
        return ssoExAccessVO;
    }

    /**
     * Do2Vo
     * 
     * @param ssoAccessDO
     * @return
     */
    public static SsoAccessVO convertDo2Vo(SsoAccessDO ssoAccessDO) {
        if (null == ssoAccessDO) {
            return null;
        }
        SsoAccessVO ssoAccessVO = new SsoAccessVO();
        ssoAccessVO.setGmtCreate(ssoAccessDO.getGmtCreate());
        ssoAccessVO.setGmtModified(ssoAccessDO.getGmtModified());
        ssoAccessVO.setId(ssoAccessDO.getId().toString());
        ssoAccessVO.setToken(ssoAccessDO.getToken());
        ssoAccessVO.setUserId(ssoAccessDO.getUserId().toString());
        ssoAccessVO.setValidDate(ssoAccessDO.getValidDate());
        ssoAccessVO.setDescription(ssoAccessDO.getDescription());

        return ssoAccessVO;
    }

    /**
     * Vo2Do
     * 
     * @param ssoAccessVO
     * @return
     */
    public static SsoAccessDO convertVo2Do(SsoAccessVO ssoAccessVO) {
        if (null == ssoAccessVO) {
            return null;
        }
        SsoAccessDO ssoAccessDO = new SsoAccessDO();
        ssoAccessDO.setToken(ssoAccessVO.getToken());
        ssoAccessDO.setUserId(Integer.parseInt(ssoAccessVO.getUserId()));
        ssoAccessDO.setValidDate(new Date(new Date().getTime() + SsoConstant.TOKEN_VALID_DURATION));
        ssoAccessDO.setDescription(ssoAccessVO.getDescription());
        ssoAccessDO.setId(StringUtils.isBlank(ssoAccessVO.getId()) ? 0 : Integer.parseInt(ssoAccessVO.getId()));

        return ssoAccessDO;
    }

    /**
     * Dos2Vos
     * 
     * @param list
     * @return
     */
    public static ArrayList<SsoAccessVO> convertDos2Vos(ArrayList<SsoAccessDO> list) {
        if (null == list) {
            return null;
        }

        ArrayList<SsoAccessVO> result = new ArrayList<SsoAccessVO>();
        for (SsoAccessDO ssoAccessDO : list) {
            result.add(convertDo2Vo(ssoAccessDO));
        }

        return result;
    }

    /**
     * ExDos2ExVos
     * 
     * @param list
     * @return
     */
    public static ArrayList<SsoExAccessVO> convertExDos2ExVos(ArrayList<ExSsoAccessDO> list) {
        if (null == list) {
            return null;
        }

        ArrayList<SsoExAccessVO> result = new ArrayList<SsoExAccessVO>();
        for (ExSsoAccessDO exSsoAccessDO : list) {
            result.add(convertExDo2ExVo(exSsoAccessDO));
        }

        return result;
    }

}
