/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.service.impl;

import java.util.HashMap;

import com.bbd.bdsso.common.util.AESUtils;
import com.bbd.bdsso.common.util.SsoConstant;
import com.bbd.bdsso.common.util.enums.BdssoResultEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;
import com.bbd.bdsso.core.service.SsoEncryptService;

/**
 * SSO加解密服务实现
 * 
 * @author byron
 * @version $Id: SsoEncryptServiceImpl.java, v 0.1 Oct 25, 2017 5:04:04 PM byron Exp $
 */
public class SsoEncryptServiceImpl implements SsoEncryptService {

    /** 
     * @see com.bbd.bdsso.core.service.SsoEncryptService#encrypt(java.lang.String, java.lang.String)
     */
    @Override
    public String encrypt(String s, String secret) {
        return AESUtils.encrypt(s, secret);
    }

    /** 
     * @see com.bbd.bdsso.core.service.SsoEncryptService#decrypt(java.lang.String)
     */
    @Override
    public HashMap<String, String> decrypt(String s) {

        String target;
        try {
            target = AESUtils.decrypt(s, AESUtils.SECRETE);
        } catch (Exception e) {
            throw new BdssoBaseException(BdssoResultEnum.INVAID_TICKET);
        }

        // 解析字符串
        if (!target.contains(SsoConstant.AND) || !target.contains(SsoConstant.EQUAL)) {
            throw new BdssoBaseException(BdssoResultEnum.INVAID_TICKET);
        }

        String[] strs = target.split(SsoConstant.AND);

        if (strs.length != 2) {
            throw new BdssoBaseException(BdssoResultEnum.INVAID_TICKET);
        }

        HashMap<String, String> result = new HashMap<String, String>();

        for (int i = 0; i < strs.length; i++) {
            String[] subStrs = strs[i].split(SsoConstant.EQUAL);
            for (int j = 0; j < subStrs.length; j++) {
                result.put(subStrs[0], subStrs[1]);
            }
        }

        return result;
    }

}
