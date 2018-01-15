/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.test.common.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.bbd.bdsso.common.dal.daointerface.SsoResourceDAO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoResourceDAO;
import com.bbd.bdsso.common.dal.manual.dataobject.ExSsoResourceDO;
import com.bbd.bdsso.test.BasicTest;
import com.bbd.commons.lang.util.page.PageList;

/**
 * SSO权限测试用例
 * 
 * @author byron
 * @version $Id: SsoResourceDAOTest.java, v 0.1 Sep 19, 2017 4:20:55 PM byron Exp $
 */
public class SsoResourceDAOTest extends BasicTest {

    /** SSO资源DAO */
    @Autowired
    private SsoResourceDAO      ssoResourceDAO;

    /** SSO手工资源DAO */
    @Autowired
    private ExtraSsoResourceDAO extraSsoResourceDAO;

    @Test
    private void deleteTest() {
        ssoResourceDAO.delete(999999);
    }

    @Test
    private void queryTest() {
        ssoResourceDAO.query(999999);
    }

    @Test
    private void queryByResourceNameTest() {
        ssoResourceDAO.queryByResourceName("FASFASDG23T24G");
    }

    @Test
    private void fuzzyQueryForPageTest() {
        PageList list = extraSsoResourceDAO.fuzzyQueryForPage("a", 15, 0);
        System.out.println(list.toString());
    }

    @Test
    private void queryAllByParamsTest() {
        List<ExSsoResourceDO> list = extraSsoResourceDAO.queryAllByParams();
        for (ExSsoResourceDO exSsoResourceDO : list) {
            System.out.println(exSsoResourceDO.toString());
        }
    }

    //    @BeforeMethod
    //    private void beforeProcess() {
    //        ssoResourceDAO.deleteByDesc("byronzoz");
    //        SsoResourceDO ssoResourceDO = new SsoResourceDO();
    //        ssoResourceDO.setType(1);
    //        ssoResourceDO.setResourceName("rs");
    //        ssoResourceDO.setDescription("byronzoz");
    //        ssoResourceDO.setGmtCreate(new Date());
    //        ssoResourceDO.setGmtModified(new Date());
    //        ssoResourceDO.setIsEnable(1);
    //        ssoResourceDO.setLastModifier("admin");
    //        ssoResourceDAO.insert(ssoResourceDO);
    //    }
    //
    //    @AfterMethod
    //    private void postProcess() {
    //        ssoResourceDAO.deleteByDesc("byronzoz");
    //    }

}
