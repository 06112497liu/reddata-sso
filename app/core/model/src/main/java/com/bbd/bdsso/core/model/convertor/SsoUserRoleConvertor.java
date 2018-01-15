/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.model.convertor;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.bbd.bdsso.common.dal.dataobject.SsoUserRoleDO;
import com.bbd.bdsso.common.service.facade.vo.SsoUserRoleVO;

/**
 * SSO用户角色转换器
 * 
 * @UserRoleor byron
 * @version $Id: SsoUserRoleConvertor.java, v 0.1 Sep 26, 2017 4:12:31 PM byron Exp $
 */
public class SsoUserRoleConvertor {

    /**
     * Do2Vo
     * 
     * @param ssoUserRoleDO
     * @return
     */
    public static SsoUserRoleVO convertDo2Vo(SsoUserRoleDO ssoUserRoleDO) {
        if (null == ssoUserRoleDO) {
            return null;
        }

        SsoUserRoleVO ssoUserRoleVO = new SsoUserRoleVO();
        ssoUserRoleVO.setId(ssoUserRoleDO.getId().toString());
        ssoUserRoleVO.setRoleId(ssoUserRoleDO.getRoleId().toString());
        ssoUserRoleVO.setUserId(ssoUserRoleDO.getUserId().toString());
        ssoUserRoleVO.setDescription(ssoUserRoleDO.getDescription());
        ssoUserRoleVO.setLastModifier(ssoUserRoleDO.getLastModifier());
        ssoUserRoleVO.setGmtCreate(ssoUserRoleDO.getGmtCreate());
        ssoUserRoleVO.setGmtModified(ssoUserRoleDO.getGmtModified());

        return ssoUserRoleVO;
    }

    /**
     * Vo2Do
     * 
     * @param ssoUserRoleVO
     * @return
     */
    public static SsoUserRoleDO convertVo2Do(SsoUserRoleVO ssoUserRoleVO) {
        if (null == ssoUserRoleVO) {
            return null;
        }
        SsoUserRoleDO ssoUserRoleDO = new SsoUserRoleDO();
        ssoUserRoleDO.setUserId(Integer.parseInt(ssoUserRoleVO.getUserId()));
        ssoUserRoleDO.setRoleId(Integer.parseInt(ssoUserRoleVO.getRoleId()));
        ssoUserRoleDO.setDescription(ssoUserRoleVO.getDescription());
        ssoUserRoleDO.setLastModifier(ssoUserRoleVO.getLastModifier());
        ssoUserRoleDO.setGmtCreate(ssoUserRoleVO.getGmtCreate());
        ssoUserRoleDO.setGmtModified(ssoUserRoleVO.getGmtModified());
        ssoUserRoleDO.setId(StringUtils.isBlank(ssoUserRoleVO.getId()) ? 0 : Integer.parseInt(ssoUserRoleVO.getId()));

        return ssoUserRoleDO;
    }

    /**
     * Dos2Vos
     * 
     * @param list
     * @return
     */
    public static ArrayList<SsoUserRoleVO> convertDos2Vos(ArrayList<SsoUserRoleDO> list) {
        if (null == list) {
            return null;
        }

        ArrayList<SsoUserRoleVO> result = new ArrayList<SsoUserRoleVO>();
        for (SsoUserRoleDO ssoUserRoleDO : list) {
            result.add(convertDo2Vo(ssoUserRoleDO));
        }

        return result;
    }

}
