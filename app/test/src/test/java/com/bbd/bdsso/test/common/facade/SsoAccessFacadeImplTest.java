/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.test.common.facade;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.bbd.bdsso.common.service.facade.SsoAccessFacade;
import com.bbd.bdsso.common.service.facade.vo.SsoAccessVO;
import com.bbd.bdsso.test.BasicTest;

/**
 * SSO用户访问用例
 * 
 * @author byron
 * @version $Id: SsoAccessFacadeImplTest.java, v 0.1 Sep 29, 2017 4:07:49 PM byron Exp $
 */
public class SsoAccessFacadeImplTest extends BasicTest {

    private static final String UID   = "1";

    private static final String TOKEN = "33a90dcf0ef4845e1e57b26033e8f0cf";

    private static final String ID    = "1";

    @Autowired
    private SsoAccessFacade     ssoAccessFacade;

    @Test
    private void deleteTest() {
        ssoAccessFacade.delete(UID, TOKEN, ID);
    }

    @Test
    private void updateTest() {
        ssoAccessFacade.update(UID, TOKEN, constr());
    }

    @Test
    private void queryById() {
        ssoAccessFacade.queryById(UID, TOKEN, ID);
    }

    @Test
    private void fuzzyQueryForPage() {
        ssoAccessFacade.fuzzyQueryForPage(UID, TOKEN, 0, 1, "2017");
    }

    private SsoAccessVO constr() {
        SsoAccessVO ssoAccessVO = new SsoAccessVO();
        ssoAccessVO.setGmtCreate(new Date());
        ssoAccessVO.setGmtModified(new Date());
        ssoAccessVO.setId(ID);
        ssoAccessVO.setToken(TOKEN);
        ssoAccessVO.setValidDate(new Date());
        ssoAccessVO.setUserId(UID);
        return ssoAccessVO;
    }

}
