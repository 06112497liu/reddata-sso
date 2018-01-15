/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.test.common.facade;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bbd.bdsso.common.dal.daointerface.SsoAuthDAO;
import com.bbd.bdsso.common.service.facade.SsoAuthFacade;
import com.bbd.bdsso.common.service.facade.vo.SsoAuthVO;
import com.bbd.bdsso.test.BasicTest;

/**
 * 用户权限服务接口测试用例
 * 
 * @author byron
 * @version $Id: SsoAuthFacadeTest.java, v 0.1 Oct 9, 2017 10:59:56 AM byron Exp $
 */
public class SsoAuthFacadeTest extends BasicTest {

    private static final String UID   = "1";

    private static final String TOKEN = "33a90dcf0ef4845e1e57b26033e8f0cf";

    private static final String ID    = "1";

    @Autowired
    private SsoAuthFacade       ssoAuthFacade;

    @Autowired
    private SsoAuthDAO          ssoAuthDAO;

    @BeforeClass
    private void beforeProcess() {
        ssoAuthDAO.deleteByDesc("byronzoz");
    }

    @AfterClass
    private void postProcess() {
        ssoAuthDAO.deleteByDesc("byronzoz");
    }

    @Test
    private void addTest() {
        ssoAuthFacade.add(UID, TOKEN, constr());
    }

    @Test
    private void deleteTest() {
        ssoAuthFacade.delete(UID, TOKEN, ID);
    }

    @Test
    private void updateTest() {
        ssoAuthFacade.update(UID, TOKEN, constr());
    }

    @Test
    private void queryById() {
        ssoAuthFacade.queryById(UID, TOKEN, ID);
    }

    @Test
    private void fuzzyQueryForPage() {
        ssoAuthFacade.fuzzyQueryForPage(UID, TOKEN, 0, 1, "2017");
    }

    private SsoAuthVO constr() {
        SsoAuthVO ssoAuthVO = new SsoAuthVO();
        ssoAuthVO.setAppId("1");
        ssoAuthVO.setAuthCode("code");
        ssoAuthVO.setAuthName("name");
        ssoAuthVO.setDescription("byronzoz");
        ssoAuthVO.setGmtCreate(new Date());
        ssoAuthVO.setGmtModified(new Date());
        ssoAuthVO.setIsEnable(1);
        ssoAuthVO.setLastModifier("admin");
        return ssoAuthVO;
    }
}
