/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.model.convertor;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.bbd.bdsso.common.dal.dataobject.SsoAuthResourceDO;
import com.bbd.bdsso.common.service.facade.vo.SsoAuthResourceVO;

/**
 * SSO权限资源转换器
 * 
 * @RoleAuthor byron
 * @version $Id: SsoAuthResourceConvertor.java, v 0.1 Sep 26, 2017 4:12:31 PM byron Exp $
 */
public class SsoAuthResourceConvertor {

    /**
     * Do2Vo
     * 
     * @param ssoAuthResourceDO
     * @return
     */
    public static SsoAuthResourceVO convertDo2Vo(SsoAuthResourceDO ssoAuthResourceDO) {
        if (null == ssoAuthResourceDO) {
            return null;
        }

        SsoAuthResourceVO ssoAuthResourceVO = new SsoAuthResourceVO();
        ssoAuthResourceVO.setId(ssoAuthResourceDO.getId().toString());
        ssoAuthResourceVO.setAuthId(ssoAuthResourceDO.getAuthId().toString());
        ssoAuthResourceVO.setResourceId(ssoAuthResourceDO.getResourceId().toString());
        ssoAuthResourceVO.setDescription(ssoAuthResourceDO.getDescription());
        ssoAuthResourceVO.setLastModifier(ssoAuthResourceDO.getLastModifier());
        ssoAuthResourceVO.setGmtCreate(ssoAuthResourceDO.getGmtCreate());
        ssoAuthResourceVO.setGmtModified(ssoAuthResourceDO.getGmtModified());

        return ssoAuthResourceVO;
    }

    /**
     * Vo2Do
     * 
     * @param ssoAuthResourceVO
     * @return
     */
    public static SsoAuthResourceDO convertVo2Do(SsoAuthResourceVO ssoAuthResourceVO) {
        if (null == ssoAuthResourceVO) {
            return null;
        }
        SsoAuthResourceDO ssoAuthResourceDO = new SsoAuthResourceDO();
        ssoAuthResourceDO.setAuthId(Integer.parseInt(ssoAuthResourceVO.getAuthId()));
        ssoAuthResourceDO.setResourceId(Integer.parseInt(ssoAuthResourceVO.getResourceId()));
        ssoAuthResourceDO.setDescription(ssoAuthResourceVO.getDescription());
        ssoAuthResourceDO.setLastModifier(ssoAuthResourceVO.getLastModifier());
        ssoAuthResourceDO.setGmtCreate(ssoAuthResourceVO.getGmtCreate());
        ssoAuthResourceDO.setGmtModified(ssoAuthResourceVO.getGmtModified());
        ssoAuthResourceDO.setId(StringUtils.isBlank(ssoAuthResourceVO.getId()) ? 0 : Integer.parseInt(ssoAuthResourceVO.getId()));

        return ssoAuthResourceDO;
    }

    /**
     * Dos2Vos
     * 
     * @param list
     * @return
     */
    public static ArrayList<SsoAuthResourceVO> convertDos2Vos(ArrayList<SsoAuthResourceDO> list) {
        if (null == list) {
            return null;
        }

        ArrayList<SsoAuthResourceVO> result = new ArrayList<SsoAuthResourceVO>();
        for (SsoAuthResourceDO ssoAuthResourceDO : list) {
            result.add(convertDo2Vo(ssoAuthResourceDO));
        }

        return result;
    }

}
