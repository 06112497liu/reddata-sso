/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.model.convertor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.bbd.bdsso.common.dal.dataobject.SsoUserInfoDO;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.lang.StringUtils;

import com.bbd.bdsso.common.dal.dataobject.SsoUserDO;
import com.bbd.bdsso.common.service.facade.vo.SsoUserVO;

/**
 * SSO用户访问模型转换器
 * 
 * @author byron
 * @version $Id: SsoUserConvertor.java, v 0.1 Sep 26, 2017 4:12:31 PM byron Exp $
 */
public class SsoUserConvertor {
    /**
     * Do2Vo
     *
     * @param ssoUserInfoDO
     * @return
     */
    public static SsoUserVO convertDo2Vo(SsoUserInfoDO ssoUserInfoDO) {
        if (null == ssoUserInfoDO) {
            return null;
        }
        SsoUserVO ssoUserVO = new SsoUserVO();

        ssoUserVO.setId(ssoUserInfoDO.getUserId()+"");
        ssoUserVO.setInfoId(ssoUserInfoDO.getId().toString());
        ssoUserVO.setName(ssoUserInfoDO.getName());
        ssoUserVO.setPersId(ssoUserInfoDO.getPersId());
        ssoUserVO.setDeptName(ssoUserInfoDO.getDeptName());
        ssoUserVO.setRegion(ssoUserInfoDO.getRegion());
        ssoUserVO.setLawNum(ssoUserInfoDO.getLawNum());
        ssoUserVO.setDuty(ssoUserInfoDO.getDuty());
        ssoUserVO.setCardId(ssoUserInfoDO.getCardId());
        ssoUserVO.setPhone(ssoUserInfoDO.getPhone());
        return ssoUserVO;
    }
    /**
     * Do2Vo
     * 
     * @param ssoUserDO
     * @return
     */
    public static SsoUserVO convertDo2Vo(SsoUserDO ssoUserDO,SsoUserInfoDO ssoUserInfoDO) {
        if (null == ssoUserDO) {
            return null;
        }
        SsoUserVO ssoUserVO = new SsoUserVO();

        ssoUserVO.setId(ssoUserDO.getId().toString());
        ssoUserVO.setUserName(ssoUserDO.getUserName());
        ssoUserVO.setEmail(ssoUserDO.getEmail());
        ssoUserVO.setUserPassword(ssoUserDO.getUserPassword());
        ssoUserVO.setIsEnable(ssoUserDO.getIsEnable());
        ssoUserVO.setDescription(ssoUserDO.getDescription());
        ssoUserVO.setLastModifier(ssoUserDO.getLastModifier());
        ssoUserVO.setGmtCreate(ssoUserDO.getGmtCreate());
        ssoUserVO.setGmtModified(ssoUserDO.getGmtModified());

        ssoUserVO.setInfoId(ssoUserInfoDO.getId().toString());
        ssoUserVO.setName(ssoUserInfoDO.getName());
        ssoUserVO.setPersId(ssoUserInfoDO.getPersId());
        ssoUserVO.setDeptName(ssoUserInfoDO.getDeptName());
        ssoUserVO.setRegion(ssoUserInfoDO.getRegion());
        ssoUserVO.setLawNum(ssoUserInfoDO.getLawNum());
        ssoUserVO.setDuty(ssoUserInfoDO.getDuty());
        ssoUserVO.setCardId(ssoUserInfoDO.getCardId());
        ssoUserVO.setPhone(ssoUserInfoDO.getPhone());
        return ssoUserVO;
    }

    /**
     * convertVo2UserDo
     * 
     * @param ssoUserVO
     * @return
     */
    public static SsoUserDO convertVo2UserDo(SsoUserVO ssoUserVO) {
        if (null == ssoUserVO) {
            return null;
        }
        SsoUserDO ssoUserDO = new SsoUserDO();
        ssoUserDO.setUserName(ssoUserVO.getUserName());
        ssoUserDO.setUserPassword(ssoUserVO.getUserPassword());
        ssoUserDO.setEmail(ssoUserVO.getEmail());
        ssoUserDO.setIsEnable(ssoUserVO.getIsEnable());
        ssoUserDO.setDescription(ssoUserVO.getDescription());
        ssoUserDO.setLastModifier(ssoUserVO.getLastModifier());
        ssoUserDO.setGmtCreate(ssoUserVO.getGmtCreate());
        ssoUserDO.setGmtModified(ssoUserVO.getGmtModified());
        ssoUserDO.setId(StringUtils.isBlank(ssoUserVO.getId()) ? 0 : Integer.parseInt(ssoUserVO.getId()));

        return ssoUserDO;
    }

    /**
     * convertVo2UserInfoDo
     *
     * @param ssoUserVO
     * @return
     */
    public static SsoUserInfoDO convertVo2UserInfoDo(SsoUserVO ssoUserVO) {
        if (null == ssoUserVO) {
            return null;
        }
        SsoUserInfoDO ssoUserInfoDO = new SsoUserInfoDO();
        ssoUserInfoDO.setName(ssoUserVO.getName());
        ssoUserInfoDO.setPersId(ssoUserVO.getPersId());
        ssoUserInfoDO.setDeptName(ssoUserVO.getDeptName());
        ssoUserInfoDO.setRegion(ssoUserVO.getRegion());
        ssoUserInfoDO.setLawNum(ssoUserVO.getLawNum());
        ssoUserInfoDO.setDuty(ssoUserVO.getDuty());
        ssoUserInfoDO.setCardId(ssoUserVO.getCardId());
        ssoUserInfoDO.setPhone(ssoUserVO.getPhone());
        ssoUserInfoDO.setLastModifier(ssoUserVO.getLastModifier());
        ssoUserInfoDO.setGmtCreate(ssoUserVO.getGmtCreate());
        ssoUserInfoDO.setGmtModified(ssoUserVO.getGmtModified());
        ssoUserInfoDO.setId(StringUtils.isBlank(ssoUserVO.getId()) ? 0 : Integer.parseInt(ssoUserVO.getId()));

        return ssoUserInfoDO;
    }


    /**
     * Dos2Vos
     * 
     * @param SsoUserDOs
     * @return
     */
    public static ArrayList<SsoUserVO> convertDos2Vos(List<SsoUserDO> SsoUserDOs,List<SsoUserInfoDO> ssoUserInfoDOs) {
        if (null == SsoUserDOs && null == ssoUserInfoDOs) {
            return null;
        }

        ssoUserInfoDOs = ssoUserInfoDOs.stream().sorted(Comparator.comparing(SsoUserInfoDO::getUserId)).collect(Collectors.toList());
        SsoUserDOs = SsoUserDOs.stream().sorted(Comparator.comparing(SsoUserDO::getId)).collect(Collectors.toList());
        ArrayList<SsoUserVO> result = new ArrayList<SsoUserVO>();
        for (int i = 0 ; i < SsoUserDOs.size() ; i++) {
            result.add(convertDo2Vo(SsoUserDOs.get(i),ssoUserInfoDOs.get(i)));
        }

        return result;
    }

    /**
     * Dos2Vos
     *
     * @param ssoUserInfoDOs
     * @return
     */
    public static ArrayList<SsoUserVO> convertDos2Vos(List<SsoUserInfoDO> ssoUserInfoDOs) {
        if (null == ssoUserInfoDOs) {
            return null;
        }

        ArrayList<SsoUserVO> result = new ArrayList<SsoUserVO>();
        for (int i = 0 ; i < ssoUserInfoDOs.size() ; i++) {
            result.add(convertDo2Vo(ssoUserInfoDOs.get(i)));
        }

        return result;
    }
}
