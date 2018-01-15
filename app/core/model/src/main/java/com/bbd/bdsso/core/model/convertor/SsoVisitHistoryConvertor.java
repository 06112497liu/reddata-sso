/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.model.convertor;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.bbd.bdsso.common.dal.dataobject.SsoVisitHistoryDO;
import com.bbd.bdsso.common.dal.manual.dataobject.ExSsoVisitHistoryDO;
import com.bbd.bdsso.common.service.facade.vo.SsoVisitHistoryVO;

/**
 * SSO用户访问历史记录模型转换器
 * 
 * @author byron
 * @version $Id: SsoVisitHistoryConvertor.java, v 0.1 Sep 26, 2017 4:12:31 PM byron Exp $
 */
public class SsoVisitHistoryConvertor {

    /**
     * ExDo2Vo
     * 
     * @param ssoVisitHistoryDO
     * @return
     */
    public static SsoVisitHistoryVO convertExDo2Vo(ExSsoVisitHistoryDO exSsoVisitHistoryDO) {
        if (null == exSsoVisitHistoryDO) {
            return null;
        }
        SsoVisitHistoryVO ssoVisitHistoryVO = new SsoVisitHistoryVO();
        ssoVisitHistoryVO.setId(exSsoVisitHistoryDO.getId().toString());
        ssoVisitHistoryVO.setUserId(exSsoVisitHistoryDO.getUserId().toString());
        ssoVisitHistoryVO.setLastLoginDate(exSsoVisitHistoryDO.getLastLoginDate());
        ssoVisitHistoryVO.setLastLoginIp(exSsoVisitHistoryDO.getLastLoginIp());
        ssoVisitHistoryVO.setDescription(exSsoVisitHistoryDO.getDescription());
        ssoVisitHistoryVO.setGmtCreate(exSsoVisitHistoryDO.getGmtCreate());
        ssoVisitHistoryVO.setGmtModified(exSsoVisitHistoryDO.getGmtModified());
        ssoVisitHistoryVO.setUserName(exSsoVisitHistoryDO.getUserName());

        return ssoVisitHistoryVO;
    }

    /**
     * Vo2Do
     * 
     * @param ssoVisitHistoryVO
     * @return
     */
    public static SsoVisitHistoryDO convertVo2Do(SsoVisitHistoryVO ssoVisitHistoryVO) {
        if (null == ssoVisitHistoryVO) {
            return null;
        }
        SsoVisitHistoryDO ssoVisitHistoryDO = new SsoVisitHistoryDO();
        ssoVisitHistoryDO.setDescription(ssoVisitHistoryVO.getDescription());
        ssoVisitHistoryDO.setLastLoginDate(ssoVisitHistoryVO.getLastLoginDate());
        ssoVisitHistoryDO.setLastLoginIp(ssoVisitHistoryVO.getLastLoginIp());
        ssoVisitHistoryDO.setUserId(Integer.parseInt(ssoVisitHistoryVO.getUserId()));
        ssoVisitHistoryDO.setGmtCreate(ssoVisitHistoryVO.getGmtCreate());
        ssoVisitHistoryDO.setGmtModified(ssoVisitHistoryVO.getGmtModified());
        ssoVisitHistoryDO.setId(StringUtils.isBlank(ssoVisitHistoryVO.getId()) ? 0 : Integer.parseInt(ssoVisitHistoryVO.getId()));

        return ssoVisitHistoryDO;
    }

    /**
     * ExDos2Vos
     * 
     * @param list
     * @return
     */
    public static ArrayList<SsoVisitHistoryVO> convertExDos2Vos(ArrayList<ExSsoVisitHistoryDO> list) {
        if (null == list) {
            return null;
        }

        ArrayList<SsoVisitHistoryVO> result = new ArrayList<SsoVisitHistoryVO>();
        for (ExSsoVisitHistoryDO exSsoVisitHistoryDO : list) {
            result.add(convertExDo2Vo(exSsoVisitHistoryDO));
        }

        return result;
    }

}
