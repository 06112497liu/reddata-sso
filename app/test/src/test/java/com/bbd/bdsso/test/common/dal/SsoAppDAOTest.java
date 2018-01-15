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

import com.bbd.bdsso.common.dal.daointerface.SsoAppDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoAppDO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoAppDAO;
import com.bbd.bdsso.test.BasicTest;

/**
 * SSO应用测试用例
 * 
 * @author byron
 * @version $Id: SsoAppDAOTest.java, v 0.1 Sep 18, 2017 3:33:59 PM byron Exp $
 */
public class SsoAppDAOTest extends BasicTest {

    /** SSO应用DAO */
    @Autowired
    private SsoAppDAO      ssoAppDAO;

    /** SSO手工应用DAO */
    @Autowired
    private ExtraSsoAppDAO extraSsoAppDAO;

    @Test
    private void deleteTest() {
        ssoAppDAO.delete(999999);
    }

    @Test
    private void queryTest() {
        ssoAppDAO.query(999999);
    }

    @Test
    private void fuzzyQueryForPageTest() {
        extraSsoAppDAO.fuzzyQueryForPage("2017", 4, 0);
    }

    @BeforeMethod
    private void beforeProcess() {
        ssoAppDAO.deleteByDesc("byronzoz");
        SsoAppDO ssoAppDO = new SsoAppDO();
        ssoAppDO.setAppLogo("/data1/logo.png");
        ssoAppDO.setAppPic("/data1/pic.png");
        ssoAppDO.setAppName("app_1");
        ssoAppDO.setDescription("byronzoz");
        ssoAppDO.setGmtCreate(new Date());
        ssoAppDO.setGmtModified(new Date());
        ssoAppDO.setIsEnable(1);
        ssoAppDO.setLastModifier("byron");
        ssoAppDAO.insert(ssoAppDO);
    }

    @AfterMethod
    private void postProcess() {
        ssoAppDAO.deleteByDesc("byronzoz");
    }

}
