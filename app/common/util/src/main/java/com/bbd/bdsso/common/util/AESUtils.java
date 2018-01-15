/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 *
 */
package com.bbd.bdsso.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.bbd.bdsso.common.util.exception.BdssoBaseException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * AES加解密算法
 * 加密用的Key 可以用26个字母和数字组成，最好不要用保留字符
 * 此处使用AES-128-CBC加密模式，key需要为16位。
 * 
 * @author byron
 * @version $Id: AESUtils.java, v 0.1 Sep 19, 2017 9:55:43 AM byron Exp $
 */
public class AESUtils {

    /** 密钥 */
    public static final String  SECRETE   = "1234567890123456";

    /** 算法/模式/补码方式 */
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    /** 算法 */
    private static final String AES       = "AES";

    /** 编码类型 */
    private static final String CHARCODE  = "ASCII";

    /** 使用CBC模式，需要一个向量iv，可增加加密算法的强度 */
    private static final String MODEL     = "0102030405060708";

    /**
     * 加密
     *
     * @param src 需要加密的内容
     * @param key 加密秘钥
     * @return
     */
    public static String encrypt(String src, String key) {
        if (key == null) {
            return null;
        }
        // 判断Key是否为16位
        if (key.length() != 16) {

            return null;
        }
        String afterCode = null;
        try {
            byte[] raw = key.getBytes();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
            IvParameterSpec iv = new IvParameterSpec(MODEL.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(src.getBytes());
            //此处使用BASE64做转码功能，同时能起到2次加密的作用
            afterCode = new BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            throw new BdssoBaseException(e.getMessage());
        }
        return afterCode;
    }

    /**
     * 解密
     * 
     * @param src 密文
     * @param key 密钥
     * @return
     */
    public static String decrypt(String src, String key) {
        String originalString = null;
        try {
            // 判断Key密钥是否正确
            if (key == null) {
                return null;
            }
            // 判断Key是否为16位
            if (key.length() != 16) {
                return null;
            }
            byte[] raw = key.getBytes(CHARCODE);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(MODEL.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            //先用base64解密
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(src);
            byte[] original = cipher.doFinal(encrypted1);
            originalString = new String(original);
        } catch (Exception e) {
            throw new BdssoBaseException(e.getMessage());
        }
        return originalString;
    }
}