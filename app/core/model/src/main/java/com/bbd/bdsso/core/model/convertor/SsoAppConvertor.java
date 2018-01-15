/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.model.convertor;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.bbd.bdsso.common.dal.dataobject.SsoAppDO;
import com.bbd.bdsso.common.service.facade.vo.SsoAppVO;

/**
 * SSO用户访问模型转换器
 * 
 * @author byron
 * @version $Id: SsoAppConvertor.java, v 0.1 Sep 26, 2017 4:12:31 PM byron Exp $
 */
public class SsoAppConvertor {

    /**
     * Do2Vo
     * 
     * @param ssoAppDO
     * @return
     */
    public static SsoAppVO convertDo2Vo(SsoAppDO ssoAppDO) {
        if (null == ssoAppDO) {
            return null;
        }
        SsoAppVO ssoAppVO = new SsoAppVO();
        ssoAppVO.setId(ssoAppDO.getId().toString());
        ssoAppVO.setAppLogo(ssoAppDO.getAppLogo());
        ssoAppVO.setAppName(ssoAppDO.getAppName());
        ssoAppVO.setAppPic(ssoAppDO.getAppPic());
        ssoAppVO.setIsEnable(ssoAppDO.getIsEnable());
        ssoAppVO.setDescription(ssoAppDO.getDescription());
        ssoAppVO.setLastModifier(ssoAppDO.getLastModifier());
        ssoAppVO.setGmtCreate(ssoAppDO.getGmtCreate());
        ssoAppVO.setGmtModified(ssoAppDO.getGmtModified());

        return ssoAppVO;
    }

    /**
     * Vo2Do
     * 
     * @param ssoAppVO
     * @return
     */
    public static SsoAppDO convertVo2Do(SsoAppVO ssoAppVO) {
        if (null == ssoAppVO) {
            return null;
        }
        SsoAppDO ssoAppDO = new SsoAppDO();
        ssoAppDO.setId(StringUtils.isBlank(ssoAppVO.getId()) ? 0 : Integer.parseInt(ssoAppVO.getId()));
        ssoAppDO.setAppName(ssoAppVO.getAppName());
        ssoAppDO.setAppLogo(ssoAppVO.getAppLogo());
        ssoAppDO.setAppPic(ssoAppVO.getAppPic());
        ssoAppDO.setIsEnable(ssoAppVO.getIsEnable());
        ssoAppDO.setDescription(ssoAppVO.getDescription());
        ssoAppDO.setLastModifier(ssoAppVO.getLastModifier());
        ssoAppDO.setGmtCreate(ssoAppVO.getGmtCreate());
        ssoAppDO.setGmtModified(ssoAppVO.getGmtModified());

        return ssoAppDO;
    }

    /**
     * Dos2Vos
     * 
     * @param list
     * @return
     */
    public static ArrayList<SsoAppVO> convertDos2Vos(ArrayList<SsoAppDO> list) {
        if (null == list) {
            return null;
        }

        ArrayList<SsoAppVO> result = new ArrayList<SsoAppVO>();
        for (SsoAppDO ssoAppDO : list) {
            result.add(convertDo2Vo(ssoAppDO));
        }

        return result;
    }

}
