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

import com.bbd.bdsso.common.dal.daointerface.SsoUserRoleDAO;
import com.bbd.bdsso.common.service.facade.SsoUserRoleFacade;
import com.bbd.bdsso.common.service.facade.vo.SsoUserRoleVO;
import com.bbd.bdsso.test.BasicTest;

/**
 * 用户角色服务接口测试用例
 * 
 * @author byron
 * @version $Id: SsoUserRoleFacadeTest.java, v 0.1 Oct 9, 2017 11:52:20 AM byron Exp $
 */
public class SsoUserRoleFacadeTest extends BasicTest {

    private static final String UID   = "1";

    private static final String ID    = "1";

    private static final String TOKEN = "33a90dcf0ef4845e1e57b26033e8f0cf";

    @Autowired
    private SsoUserRoleFacade   ssoUserRoleFacade;

    @Autowired
    private SsoUserRoleDAO      ssoUserRoleDAO;

    @BeforeClass
    private void beforeProcess() {
        ssoUserRoleDAO.deleteByDesc("byronzoz");
    }

    @AfterClass
    private void postProcess() {
        ssoUserRoleDAO.deleteByDesc("byronzoz");
    }

    @Test
    private void addTest() {
        ssoUserRoleFacade.add(UID, TOKEN, constr());
    }

    @Test
    private void deleteTest() {
        ssoUserRoleFacade.delete(UID, TOKEN, ID);
    }

    @Test
    private void updateTest() {
        ssoUserRoleFacade.update(UID, TOKEN, constr());
    }

    @Test
    private void queryById() {
        ssoUserRoleFacade.queryById(UID, TOKEN, ID);
    }

    @Test
    private void fuzzyQueryForPage() {
        ssoUserRoleFacade.fuzzyQueryForPage(UID, TOKEN, 0, 1, "2017");
    }

    private SsoUserRoleVO constr() {
        SsoUserRoleVO ssoUserRoleVO = new SsoUserRoleVO();
        ssoUserRoleVO.setDescription("byronzoz");
        ssoUserRoleVO.setGmtCreate(new Date());
        ssoUserRoleVO.setGmtModified(new Date());
        ssoUserRoleVO.setLastModifier("admin");
        ssoUserRoleVO.setRoleId("1");
        ssoUserRoleVO.setUserId("1");

        return ssoUserRoleVO;
    }

}
