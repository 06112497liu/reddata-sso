/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.test.common.dal;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bbd.bdsso.common.dal.daointerface.SsoRoleAuthDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoRoleAuthDO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoRoleAuthDAO;
import com.bbd.bdsso.test.BasicTest;

/**
 * SSO角色权限DAO
 * 
 * @author byron
 * @version $Id: SsoRoleAuthDAOTest.java, v 0.1 Sep 19, 2017 4:50:18 PM byron Exp $
 */
public class SsoRoleAuthDAOTest extends BasicTest {

    /** SSO用户权限DAO */
    @Autowired
    private SsoRoleAuthDAO      ssoRoleAuthDAO;

    /** SSO用户权限DAO */
    @Autowired
    private ExtraSsoRoleAuthDAO extraSsoRoleAuthDAO;

    @Test
    private void deleteTest() {
        ssoRoleAuthDAO.delete(999999);
    }

    @Test
    private void queryTest() {
        ssoRoleAuthDAO.query(999999);
    }

    @Test
    private void queryByRoleIdTest() {
        ssoRoleAuthDAO.queryByRoleId(999999);
    }

    @Test
    private void queryByAuthIdTest() {
        ssoRoleAuthDAO.queryByAuthId(999999);
    }

    @Test
    private void fuzzyQueryForPageTest() {
        extraSsoRoleAuthDAO.fuzzyQueryForPage("", 15, 0);
    }

    @BeforeMethod
    private void beforeProcess() {
        ssoRoleAuthDAO.deleteByDesc("byronzoz");
        SsoRoleAuthDO ssoRoleAuthDO = new SsoRoleAuthDO();
        ssoRoleAuthDO.setAuthId(999999);
        ssoRoleAuthDO.setDescription("byronzoz");
        ssoRoleAuthDO.setGmtCreate(new Date());
        ssoRoleAuthDO.setGmtModified(new Date());
        ssoRoleAuthDO.setLastModifier("byron");
        ssoRoleAuthDO.setRoleId(999999);
        ssoRoleAuthDAO.insert(ssoRoleAuthDO);
    }

    @AfterMethod
    private void postProcess() {
        ssoRoleAuthDAO.deleteByDesc("byronzoz");
    }

}
