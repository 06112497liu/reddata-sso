/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.test.common.dal;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.bbd.bdsso.common.dal.daointerface.SsoUserRoleDAO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoUserRoleDAO;
import com.bbd.bdsso.common.dal.manual.dataobject.ExSsoUserRoleDO;
import com.bbd.bdsso.test.BasicTest;
import com.bbd.commons.lang.util.page.PageList;

/**
 * SSO用户角色测试用例
 * 
 * @author byron
 * @version $Id: SsoUserRoleDAOTest.java, v 0.1 Sep 19, 2017 4:40:53 PM byron Exp $
 */
public class SsoUserRoleDAOTest extends BasicTest {

    /** SSO用户角色DAO */
    @Autowired
    private SsoUserRoleDAO      ssoUserRoleDAO;

    /** SSO手工用户角色DAO */
    @Autowired
    private ExtraSsoUserRoleDAO extraSsoUserRoleDAO;

    @Test
    private void deleteTest() {
        ssoUserRoleDAO.delete(999999);
    }

    @Test
    private void queryTest() {
        ssoUserRoleDAO.query(999999);
    }

    @Test
    private void queryByUserIdTest() {
        ssoUserRoleDAO.queryByUserId(999999);
    }

    @Test
    private void queryByRoleIdTest() {
        ssoUserRoleDAO.queryByRoleId(999999);
    }

    @Test
    private void fuzzyQueryForPageTest() {
        extraSsoUserRoleDAO.fuzzyQueryForPage("2017", 4, 0);
    }

    @Test
    private void selectForPageTest() {
        PageList pageList = extraSsoUserRoleDAO.fuzzyQueryForPage(null, 15, 2);
        ArrayList<ExSsoUserRoleDO> listCopy = (ArrayList<ExSsoUserRoleDO>) pageList.clone();
        for (int i = 0; i < listCopy.size(); i++) {
            System.out.println(listCopy.get(i).toString());
        }
    }

    //    @BeforeMethod
    //    private void beforeProcess() {
    //        ssoUserRoleDAO.deleteByDesc("byronzoz");
    //        SsoUserRoleDO ssoUserRoleDO = new SsoUserRoleDO();
    //        ssoUserRoleDO.setDescription("byronzoz");
    //        ssoUserRoleDO.setGmtCreate(new Date());
    //        ssoUserRoleDO.setGmtModified(new Date());
    //        ssoUserRoleDO.setLastModifier("byron");
    //        ssoUserRoleDO.setRoleId(999999);
    //        ssoUserRoleDO.setUserId(999999);
    //        ssoUserRoleDAO.insert(ssoUserRoleDO);
    //    }
    //
    //    @AfterMethod
    //    private void postProcess() {
    //        ssoUserRoleDAO.deleteByDesc("byronzoz");
    //    }

}
