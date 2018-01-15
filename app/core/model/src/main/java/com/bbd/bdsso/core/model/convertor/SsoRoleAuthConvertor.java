/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.model.convertor;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.bbd.bdsso.common.dal.dataobject.SsoRoleAuthDO;
import com.bbd.bdsso.common.service.facade.vo.SsoRoleAuthVO;

/**
 * SSO角色权限转换器
 * 
 * @RoleAuthor byron
 * @version $Id: SsoRoleAuthConvertor.java, v 0.1 Sep 26, 2017 4:12:31 PM byron Exp $
 */
public class SsoRoleAuthConvertor {

    /**
     * Do2Vo
     * 
     * @param ssoRoleAuthDO
     * @return
     */
    public static SsoRoleAuthVO convertDo2Vo(SsoRoleAuthDO ssoRoleAuthDO) {
        if (null == ssoRoleAuthDO) {
            return null;
        }

        SsoRoleAuthVO ssoRoleAuthVO = new SsoRoleAuthVO();
        ssoRoleAuthVO.setId(ssoRoleAuthDO.getId().toString());
        ssoRoleAuthVO.setRoleId(ssoRoleAuthDO.getRoleId().toString());
        ssoRoleAuthVO.setAuthId(ssoRoleAuthDO.getAuthId().toString());
        ssoRoleAuthVO.setDescription(ssoRoleAuthDO.getDescription());
        ssoRoleAuthVO.setLastModifier(ssoRoleAuthDO.getLastModifier());
        ssoRoleAuthVO.setGmtCreate(ssoRoleAuthDO.getGmtCreate());
        ssoRoleAuthVO.setGmtModified(ssoRoleAuthDO.getGmtModified());

        return ssoRoleAuthVO;
    }

    /**
     * Vo2Do
     * 
     * @param ssoRoleAuthVO
     * @return
     */
    public static SsoRoleAuthDO convertVo2Do(SsoRoleAuthVO ssoRoleAuthVO) {
        if (null == ssoRoleAuthVO) {
            return null;
        }
        SsoRoleAuthDO ssoRoleAuthDO = new SsoRoleAuthDO();
        ssoRoleAuthDO.setAuthId(Integer.parseInt(ssoRoleAuthVO.getAuthId()));
        ssoRoleAuthDO.setRoleId(Integer.parseInt(ssoRoleAuthVO.getRoleId()));
        ssoRoleAuthDO.setDescription(ssoRoleAuthVO.getDescription());
        ssoRoleAuthDO.setLastModifier(ssoRoleAuthVO.getLastModifier());
        ssoRoleAuthDO.setGmtCreate(ssoRoleAuthVO.getGmtCreate());
        ssoRoleAuthDO.setGmtModified(ssoRoleAuthVO.getGmtModified());
        ssoRoleAuthDO.setId(StringUtils.isBlank(ssoRoleAuthVO.getId()) ? 0 : Integer.parseInt(ssoRoleAuthVO.getId()));

        return ssoRoleAuthDO;
    }

    /**
     * Dos2Vos
     * 
     * @param list
     * @return
     */
    public static ArrayList<SsoRoleAuthVO> convertDos2Vos(ArrayList<SsoRoleAuthDO> list) {
        if (null == list) {
            return null;
        }

        ArrayList<SsoRoleAuthVO> result = new ArrayList<SsoRoleAuthVO>();
        for (SsoRoleAuthDO ssoRoleAuthDO : list) {
            result.add(convertDo2Vo(ssoRoleAuthDO));
        }

        return result;
    }

}
