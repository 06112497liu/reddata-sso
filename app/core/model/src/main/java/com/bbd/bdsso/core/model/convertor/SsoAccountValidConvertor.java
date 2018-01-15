/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.model.convertor;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.bbd.bdsso.common.dal.dataobject.SsoAccountValidDO;
import com.bbd.bdsso.common.service.facade.vo.SsoAccountValidVO;

/**
 * SSO账户验证模型转换器
 * 
 * @author byron
 * @version $Id: SsoAccountValidConvertor.java, v 0.1 Sep 26, 2017 4:12:31 PM byron Exp $
 */
public class SsoAccountValidConvertor {

    /**
     * Do2Vo
     * 
     * @param ssoAccountValidDO
     * @return
     */
    public static SsoAccountValidVO convertDo2Vo(SsoAccountValidDO ssoAccountValidDO) {
        if (null == ssoAccountValidDO) {
            return null;
        }
        SsoAccountValidVO ssoAccountValidVO = new SsoAccountValidVO();
        ssoAccountValidVO.setEmail(ssoAccountValidDO.getEmail());
        ssoAccountValidVO.setGmtCreate(ssoAccountValidDO.getGmtCreate());
        ssoAccountValidVO.setGmtModified(ssoAccountValidDO.getGmtModified());
        ssoAccountValidVO.setId(ssoAccountValidDO.getId().toString());
        ssoAccountValidVO.setIsVerified(ssoAccountValidDO.getIsVerified());
        ssoAccountValidVO.setType(ssoAccountValidDO.getType());
        ssoAccountValidVO.setValidDate(ssoAccountValidDO.getValidDate());
        ssoAccountValidVO.setVerifyCode(ssoAccountValidDO.getVerifyCode());

        return ssoAccountValidVO;
    }

    /**
     * Vo2Do
     * 
     * @param ssoAccountValidVO
     * @return
     */
    public static SsoAccountValidDO convertVo2Do(SsoAccountValidVO ssoAccountValidVO) {
        if (null == ssoAccountValidVO) {
            return null;
        }
        SsoAccountValidDO ssoAccountValidDO = new SsoAccountValidDO();
        ssoAccountValidDO.setDescription(ssoAccountValidVO.getDescription());
        ssoAccountValidDO.setEmail(ssoAccountValidVO.getEmail());
        ssoAccountValidDO.setGmtModified(ssoAccountValidVO.getGmtModified());
        ssoAccountValidDO.setGmtCreate(ssoAccountValidVO.getGmtCreate());
        ssoAccountValidDO.setIsVerified(ssoAccountValidVO.getIsVerified());
        ssoAccountValidDO.setType(ssoAccountValidVO.getType());
        ssoAccountValidDO.setValidDate(ssoAccountValidVO.getValidDate());
        ssoAccountValidDO.setVerifyCode(ssoAccountValidVO.getVerifyCode());
        ssoAccountValidDO.setId(StringUtils.isBlank(ssoAccountValidVO.getId()) ? 0 : Integer.parseInt(ssoAccountValidVO.getId()));

        return ssoAccountValidDO;
    }

    /**
     * Dos2Vos
     * 
     * @param list
     * @return
     */
    public static ArrayList<SsoAccountValidVO> convertDos2Vos(ArrayList<SsoAccountValidDO> list) {
        if (null == list) {
            return null;
        }

        ArrayList<SsoAccountValidVO> result = new ArrayList<SsoAccountValidVO>();
        for (SsoAccountValidDO ssoAccountValidDO : list) {
            result.add(convertDo2Vo(ssoAccountValidDO));
        }

        return result;
    }

}
