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

import com.bbd.bdsso.common.dal.daointerface.SsoVisitHistoryDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoVisitHistoryDO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoVisitHistoryDAO;
import com.bbd.bdsso.test.BasicTest;

/**
 * SSO访问历史测试用例
 * 
 * @author byron
 * @version $Id: SsoVisitHistoryDAOTest.java, v 0.1 Sep 18, 2017 1:45:00 PM byron Exp $
 */
public class SsoVisitHistoryDAOTest extends BasicTest {

    /** SSO用户访问历史DAO */
    @Autowired
    private SsoVisitHistoryDAO      ssoVisitHistoryDAO;

    /** SSO手工用户访问历史DAO */
    @Autowired
    private ExtraSsoVisitHistoryDAO extraSsoVisitHistoryDAO;

    @Test
    private void deleteTest() {
        ssoVisitHistoryDAO.delete(999999);
    }

    @Test
    private void queryTest() {
        ssoVisitHistoryDAO.query(999999);
    }

    @Test
    private void queryByUserIdTest() {
        ssoVisitHistoryDAO.query(999999);
    }

    @Test
    private void fuzzyQueryForPageTest() {
        extraSsoVisitHistoryDAO.fuzzyQueryForPage("2017", 4, 0);
    }

    @BeforeMethod
    private void beforeProcess() {
        ssoVisitHistoryDAO.deleteByDesc("byronzoz");
        SsoVisitHistoryDO ssoVisitHistoryDO = new SsoVisitHistoryDO();
        ssoVisitHistoryDO.setGmtCreate(new Date());
        ssoVisitHistoryDO.setGmtModified(new Date());
        ssoVisitHistoryDO.setUserId(999999);
        ssoVisitHistoryDO.setLastLoginIp("192.168.1.1");
        ssoVisitHistoryDO.setLastLoginDate(new Date());
        ssoVisitHistoryDO.setDescription("byronzoz");
        ssoVisitHistoryDAO.insert(ssoVisitHistoryDO);
    }

    @AfterMethod
    private void postProcess() {
        ssoVisitHistoryDAO.deleteByDesc("byronzoz");
    }

}
