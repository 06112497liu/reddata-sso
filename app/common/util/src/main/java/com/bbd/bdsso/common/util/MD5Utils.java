/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 *
 */
package com.bbd.bdsso.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.bbd.bdsso.common.util.enums.BdssoResultEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;

/**
 *
 * 用于数据加密工具类
 *
 * @author weizhang@bbdservice.com
 * @version $Id: AssertUtils.java, v 0.1 2017年4月11日 下午1:39:52 weizhang@bbdservice.com Exp $
 */
public class MD5Utils {

    /**
     * 对文本进行32位小写MD5加密
     *
     * @param text
     * @return 加密后的内容
     */
    public static String textToMD5L32(String text) {
        try {
            MessageDigest md5Encoder = MessageDigest.getInstance("MD5");
            byte[] textBytes = text.getBytes();
            md5Encoder.update(textBytes);
            byte[] encodedBytes = md5Encoder.digest();
            StringBuilder resultBuffer = new StringBuilder();
            for (byte perByte : encodedBytes) {
                int bt = perByte & 0xff;
                if (bt < 16) {
                    resultBuffer.append(0);
                }
                resultBuffer.append(Integer.toHexString(bt));
            }
            return resultBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new BdssoBaseException(BdssoResultEnum.NO_SUCH_ALGORITHM);
        }
    }
}
