/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.test.common.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.bbd.bdsso.common.dal.daointerface.SsoAuthDAO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoAuthDAO;
import com.bbd.bdsso.test.BasicTest;
import com.bbd.commons.lang.util.page.PageList;

/**
 * SSO权限测试用例
 * 
 * @author byron
 * @version $Id: SsoAuthDAOTest.java, v 0.1 Sep 19, 2017 4:20:55 PM byron Exp $
 */
public class SsoAuthDAOTest extends BasicTest {

    /** SSO权限DAO */
    @Autowired
    private SsoAuthDAO      ssoAuthDAO;

    /** SSO手工权限DAO */
    @Autowired
    private ExtraSsoAuthDAO extraSsoAuthDAO;

    @Test
    private void deleteTest() {
        ssoAuthDAO.delete(999999);
    }

    @Test
    private void queryTest() {
        ssoAuthDAO.query(999999);
    }

    @Test
    private void queryByAuthCodeTest() {
        ssoAuthDAO.queryByAuthCode("FASFASDG23T24G");
    }

    @Test
    private void fuzzyQueryForPageTest() {
        PageList list = extraSsoAuthDAO.fuzzyQueryForPage("a", 15, 0);
        System.out.println(list.toString());
    }

    //    @BeforeMethod
    //    private void beforeProcess() {
    //        ssoAuthDAO.deleteByDesc("byronzoz");
    //        SsoAuthDO ssoAuthDO = new SsoAuthDO();
    //        ssoAuthDO.setAuthCode("FASFASDG23T24G");
    //        ssoAuthDO.setAuthName("Light");
    //        ssoAuthDO.setDescription("byronzoz");
    //        ssoAuthDO.setGmtCreate(new Date());
    //        ssoAuthDO.setGmtModified(new Date());
    //        ssoAuthDO.setIsEnable(1);
    //        ssoAuthDO.setAppId(999999);
    //        ssoAuthDO.setLastModifier("admin");
    //        ssoAuthDAO.insert(ssoAuthDO);
    //    }
    //
    //    @AfterMethod
    //    private void postProcess() {
    //        ssoAuthDAO.deleteByDesc("byronzoz");
    //    }

}
