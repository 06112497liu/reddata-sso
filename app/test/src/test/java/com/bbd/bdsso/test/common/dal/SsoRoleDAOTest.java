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

import com.bbd.bdsso.common.dal.daointerface.SsoRoleDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoRoleDO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoRoleDAO;
import com.bbd.bdsso.test.BasicTest;

/**
 * SSO角色测试用例
 * 
 * @author byron
 * @version $Id: SsoRoleDAOTest.java, v 0.1 Sep 19, 2017 4:08:53 PM byron Exp $
 */
public class SsoRoleDAOTest extends BasicTest {

    /** SSO用户角色DAO */
    @Autowired
    private SsoRoleDAO      ssoRoleDAO;

    /** SSO手工用户角色DAO */
    @Autowired
    private ExtraSsoRoleDAO extraSsoRoleDAO;

    @Test
    private void deleteTest() {
        ssoRoleDAO.delete(999999);
    }

    @Test
    private void queryTest() {
        ssoRoleDAO.query(999999);
    }

    @Test
    private void queryByRoleNameTest() {
        ssoRoleDAO.queryByRoleName("宙斯盾管理员");
    }

    @Test
    private void fuzzyQueryForPageTest() {
        extraSsoRoleDAO.fuzzyQueryForPage("ROLE_NAME_7", 15, 1);
    }

    @BeforeMethod
    private void beforeProcess() {
        ssoRoleDAO.deleteByDesc("byronzoz");
        SsoRoleDO ssoRoleDO = new SsoRoleDO();
        ssoRoleDO.setDescription("byronzoz");
        ssoRoleDO.setGmtCreate(new Date());
        ssoRoleDO.setGmtModified(new Date());
        ssoRoleDO.setIsEnable(1);
        ssoRoleDO.setLastModifier("byron");
        ssoRoleDO.setRoleName("宙斯盾管理员");
        ssoRoleDAO.insert(ssoRoleDO);
    }

    @AfterMethod
    private void postProcess() {
        ssoRoleDAO.deleteByDesc("byronzoz");
    }

}
