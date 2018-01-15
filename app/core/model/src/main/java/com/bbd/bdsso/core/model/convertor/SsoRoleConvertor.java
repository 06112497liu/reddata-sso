/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.model.convertor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.bbd.bdsso.common.dal.dataobject.SsoRoleDO;
import com.bbd.bdsso.common.service.facade.vo.SsoRoleVO;

/**
 * SSO角色模型转换器
 * 
 * @Roleor byron
 * @version $Id: SsoRoleConvertor.java, v 0.1 Sep 26, 2017 4:12:31 PM byron Exp $
 */
public class SsoRoleConvertor {

    /**
     * Do2Vo
     * 
     * @param ssoRoleDO
     * @return
     */
    public static SsoRoleVO convertDo2Vo(SsoRoleDO ssoRoleDO) {
        if (null == ssoRoleDO) {
            return null;
        }

        SsoRoleVO ssoRoleVO = new SsoRoleVO();
        ssoRoleVO.setId(ssoRoleDO.getId().toString());
        ssoRoleVO.setRoleName(ssoRoleDO.getRoleName());
        ssoRoleVO.setIsEnable(ssoRoleDO.getIsEnable());
        ssoRoleVO.setDescription(ssoRoleDO.getDescription());
        ssoRoleVO.setLastModifier(ssoRoleDO.getLastModifier());
        ssoRoleVO.setGmtCreate(ssoRoleDO.getGmtCreate());
        ssoRoleVO.setGmtModified(ssoRoleDO.getGmtModified());

        return ssoRoleVO;
    }

    /**
     * Vo2Do
     * 
     * @param ssoRoleVO
     * @return
     */
    public static SsoRoleDO convertVo2Do(SsoRoleVO ssoRoleVO) {
        if (null == ssoRoleVO) {
            return null;
        }
        SsoRoleDO ssoRoleDO = new SsoRoleDO();
        ssoRoleDO.setRoleName(ssoRoleVO.getRoleName());
        ssoRoleDO.setIsEnable(ssoRoleVO.getIsEnable());
        ssoRoleDO.setDescription(ssoRoleVO.getDescription());
        ssoRoleDO.setLastModifier(ssoRoleVO.getLastModifier());
        ssoRoleDO.setGmtCreate(ssoRoleVO.getGmtCreate());
        ssoRoleDO.setGmtModified(ssoRoleVO.getGmtModified());
        ssoRoleDO.setId(StringUtils.isBlank(ssoRoleVO.getId()) ? 0 : Integer.parseInt(ssoRoleVO.getId()));

        return ssoRoleDO;
    }

    /**
     * Dos2Vos
     * 
     * @param list
     * @return
     */
    public static ArrayList<SsoRoleVO> convertDos2Vos(List<SsoRoleDO> list) {
        if (null == list) {
            return null;
        }

        ArrayList<SsoRoleVO> result = new ArrayList<SsoRoleVO>();
        for (SsoRoleDO ssoRoleDO : list) {
            result.add(convertDo2Vo(ssoRoleDO));
        }

        return result;
    }

}
