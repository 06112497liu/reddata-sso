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

import com.bbd.bdsso.common.dal.daointerface.SsoRoleAuthDAO;
import com.bbd.bdsso.common.service.facade.SsoRoleAuthFacade;
import com.bbd.bdsso.common.service.facade.vo.SsoRoleAuthVO;
import com.bbd.bdsso.test.BasicTest;

/**
 * 用户角色权限服务接口测试用例
 * 
 * @author byron
 * @version $Id: SsoRoleAuthFacadeTest.java, v 0.1 Oct 9, 2017 11:25:11 AM byron Exp $
 */
public class SsoRoleAuthFacadeTest extends BasicTest {

    private static final String UID   = "1";

    private static final String ID    = "1";

    private static final String TOKEN = "33a90dcf0ef4845e1e57b26033e8f0cf";

    @Autowired
    private SsoRoleAuthFacade   ssoRoleAuthFacade;

    @Autowired
    private SsoRoleAuthDAO      ssoRoleAuthDAO;

    @BeforeClass
    private void beforeProcess() {
        ssoRoleAuthDAO.deleteByDesc("byronzoz");
    }

    @AfterClass
    private void postProcess() {
        ssoRoleAuthDAO.deleteByDesc("byronzoz");
    }

    @Test
    private void addTest() {
        ssoRoleAuthFacade.add(UID, TOKEN, constr());
    }

    @Test
    private void deleteTest() {
        ssoRoleAuthFacade.delete(UID, TOKEN, ID);
    }

    @Test
    private void updateTest() {
        ssoRoleAuthFacade.update(UID, TOKEN, constr());
    }

    @Test
    private void queryById() {
        ssoRoleAuthFacade.queryById(UID, TOKEN, ID);
    }

    @Test
    private void fuzzyQueryForPage() {
        ssoRoleAuthFacade.fuzzyQueryForPage(UID, TOKEN, 0, 1, "2017");
    }

    private SsoRoleAuthVO constr() {
        SsoRoleAuthVO ssoRoleAuthVO = new SsoRoleAuthVO();
        ssoRoleAuthVO.setAuthId("1");
        ssoRoleAuthVO.setDescription("byronzoz");
        ssoRoleAuthVO.setGmtCreate(new Date());
        ssoRoleAuthVO.setGmtModified(new Date());
        ssoRoleAuthVO.setLastModifier("admin");
        ssoRoleAuthVO.setRoleId("1");
        return ssoRoleAuthVO;
    }

}
