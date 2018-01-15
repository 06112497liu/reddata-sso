/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.test.common.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.bbd.bdsso.common.service.facade.SsoVisitHistoryFacade;
import com.bbd.bdsso.test.BasicTest;

/**
 * 访问历史记录服务接口测试用例
 * 
 * @author byron
 * @version $Id: SsoVisitHistoryFacadeTest.java, v 0.1 Oct 9, 2017 1:40:45 PM byron Exp $
 */
public class SsoVisitHistoryFacadeTest extends BasicTest {

    private static final String   UID   = "1";

    private static final String   TOKEN = "33a90dcf0ef4845e1e57b26033e8f0cf";

    @Autowired
    private SsoVisitHistoryFacade ssoVisitHistoryFacade;

    @Test
    private void fuzzyQueryForPage() {
        ssoVisitHistoryFacade.fuzzyQueryForPage(UID, TOKEN, 0, 1, "2017");
    }

}
