/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.test.common.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.bbd.bdsso.common.service.facade.SsoAccountValidFacade;
import com.bbd.bdsso.test.BasicTest;

/**
 * 用户账户验证服务接口测试用例
 * 
 * @author byron
 * @version $Id: SsoAccountValidFacadeTest.java, v 0.1 Oct 9, 2017 10:42:04 AM byron Exp $
 */
public class SsoAccountValidFacadeTest extends BasicTest {

    private static final String   UID   = "1";

    private static final String   TOKEN = "33a90dcf0ef4845e1e57b26033e8f0cf";

    @Autowired
    private SsoAccountValidFacade ssoAccountValidFacade;

    @Test
    private void queryByIdTest() {
        ssoAccountValidFacade.queryById(UID, TOKEN, "999999");
    }

    @Test
    private void fuzzyQueryForPage() {
        ssoAccountValidFacade.fuzzyQueryForPage(UID, TOKEN, 0, 1, "2017");
    }

}
