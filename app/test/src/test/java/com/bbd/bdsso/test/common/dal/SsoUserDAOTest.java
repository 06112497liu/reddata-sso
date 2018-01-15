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

import com.bbd.bdsso.common.dal.daointerface.SsoUserDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoUserDO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoUserDAO;
import com.bbd.bdsso.test.BasicTest;

/**
 * SSO用户管理测试用例
 * 
 * @author byron
 * @version $Id: SsoUserDAOTest.java, v 0.1 Sep 11, 2017 3:58:00 PM byron Exp $
 */
public class SsoUserDAOTest extends BasicTest {

    /** SSO用户中心DAO */
    @Autowired
    private SsoUserDAO      ssoUserDAO;

    /** SSO手工用户中心DAO */
    @Autowired
    private ExtraSsoUserDAO extraSsoUserDAO;

    @Test
    private void loginByEmailTest() {
        ssoUserDAO.loginByEmail("byron", "zhangwei@bbdservice.com");
    }

    @Test
    private void loginByUserNameTest() {
        ssoUserDAO.loginByUserName("byron", "byron");
    }

    @Test
    private void queryTest() {
        ssoUserDAO.query(999999);
    }

    @Test
    private void deleteTest() {
        ssoUserDAO.delete(999999);
    }

    @Test
    private void queryByNameTest() {
        ssoUserDAO.queryByName("byron");
    }

    @Test
    private void queryByEmailTest() {
        ssoUserDAO.queryByEmail("zhangwei@bbdservice.com");
    }

    @Test
    private void fuzzyQueryForPageTest() {
        extraSsoUserDAO.fuzzyQueryForPage("2017", 4, 0);
    }

    @Test
    private void resetPswTest() {
        ssoUserDAO.updateByPsw("new password", 1);
    }

    @BeforeMethod
    private void beforeProcess() {
        ssoUserDAO.deleteByDesc("byronzoz");
        // 初始化数据
        SsoUserDO ssoUserDO = new SsoUserDO();
        ssoUserDO.setDescription("byronzoz");
        ssoUserDO.setEmail("zhangwei@bbdservice.com");
        ssoUserDO.setGmtCreate(new Date());
        ssoUserDO.setGmtModified(new Date());
        ssoUserDO.setIsEnable(1);
        ssoUserDO.setLastModifier("admin");
        ssoUserDO.setUserName("byron");
        ssoUserDO.setUserPassword("abc123");
        //  新增数据
        ssoUserDAO.insert(ssoUserDO);
    }

    @AfterMethod
    private void postProcess() {
        ssoUserDAO.deleteByDesc("byronzoz");
    }

}
