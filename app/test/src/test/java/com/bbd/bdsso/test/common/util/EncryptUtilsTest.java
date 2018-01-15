/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.test.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.bbd.bdsso.common.util.EncryptUtils;
import com.bbd.bdsso.test.BasicTest;

/**
 * 加解密测试
 * 
 * @author byron
 * @version $Id: EncryptUtilsTest.java, v 0.1 Sep 19, 2017 10:32:52 AM byron Exp $
 */
public class EncryptUtilsTest extends BasicTest {

    /** 日志 */
    private final Logger        logger   = LoggerFactory.getLogger(EncryptUtilsTest.class);

    private static final String PASSWORD = "1234567890";

    private static final String USERNAME = "byronzoz";

    @Test
    private void encryptPasswordTest() {
        logger.info(EncryptUtils.encryptPassword(PASSWORD));
    }

    @Test
    private void generateLoginTokenTest() {
        logger.info(EncryptUtils.generateLoginToken(USERNAME));
    }

}
