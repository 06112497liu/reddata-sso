/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.test.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.bbd.bdsso.common.util.AESUtils;
import com.bbd.bdsso.test.BasicTest;

/**
 * AES加解密算法测试用例
 * 
 * @author byron
 * @version $Id: AESUtilsTest.java, v 0.1 Sep 19, 2017 11:37:25 AM byron Exp $
 */
public class AESUtilsTest extends BasicTest {

    /** 日志 */
    private final Logger        logger = LoggerFactory.getLogger(AESUtilsTest.class);

    /** 加密前的明文 */
    private static final String STR    = "byronzozd";

    /** 密钥 */
    private static final String KEY    = "0F8G6X5C6V8B9F0S";

    /** 加密后的密文 */
    private static final String S_STR  = "pwEOmU/Su1RssuN3Ui+v/w==";

    @Test
    private void encryptTest() {
        logger.info("加密前的明文：" + STR);
        logger.info("加密后的密文：" + AESUtils.encrypt(STR, KEY));
    }

    @Test
    private void decryptTest() {
        logger.info("加密后的密文：" + S_STR);
        logger.info("解密后的明文：" + AESUtils.decrypt(S_STR, KEY));
    }

}
