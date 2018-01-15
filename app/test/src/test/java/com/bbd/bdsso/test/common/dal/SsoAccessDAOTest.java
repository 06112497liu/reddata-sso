/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.test.common.dal;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.bbd.bdsso.common.dal.daointerface.SsoAccessDAO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoAccessDAO;
import com.bbd.bdsso.common.dal.manual.dataobject.ExSsoAccessDO;
import com.bbd.bdsso.test.BasicTest;
import com.bbd.commons.lang.util.page.PageList;

/**
 * SSO访问Token测试用例
 * 
 * @author byron
 * @version $Id: SsoAccessDAOTest.java, v 0.1 Sep 19, 2017 2:39:40 PM byron Exp $
 */
public class SsoAccessDAOTest extends BasicTest {

    /** SSO访问TokenDAO*/
    @Autowired
    private SsoAccessDAO      ssoAccessDAO;

    /** SSO访问TokenDAO*/
    @Autowired
    private ExtraSsoAccessDAO extraSsoAccessDAO;

    @Test
    private void deleteTest() {
        ssoAccessDAO.delete(999999);
    }

    @Test
    private void deleteByUserIdTest() {
        ssoAccessDAO.deleteByUserId(999999);
    }

    @Test
    private void queryTest() {
        ssoAccessDAO.query(999999);
    }

    @Test
    private void queryByUserIdTest() {
        ssoAccessDAO.queryByUserId(999999);
    }

    @Test
    private void deleteByUserIdAndTokenTest() {
        ssoAccessDAO.deleteByUserIdAndToken(999999, "ff82f3863250c8ceafb92eb4f06b55c6");
    }

    @Test
    private void queryByTokenTest() {
        ssoAccessDAO.queryByToken("ff82f3863250c8ceafb92eb4f06b55c6");
    }

    @Test
    private void fuzzyQueryForPageTest() {
        PageList list = extraSsoAccessDAO.fuzzyQueryForPage(null, null, 15, 2);

        System.out.println(list.getPaginator().toString());

        ArrayList<ExSsoAccessDO> listCopy = (ArrayList<ExSsoAccessDO>) list.clone();

        for (int i = 0; i < listCopy.size(); i++) {
            System.out.println(listCopy.get(i).toString());
        }

    }

    @Test
    private void checkValidTest() {
        ssoAccessDAO.checkValid("", "");
    }
    //    @BeforeMethod
    //    private void beforeProcess() {
    //        ssoAccessDAO.deleteByDesc("byronzoz");
    //        SsoAccessDO ssoAccessDO = new SsoAccessDO();
    //        ssoAccessDO.setDescription("byronzoz");
    //        ssoAccessDO.setToken("ff82f3863250c8ceafb92eb4f06b55c6");
    //        ssoAccessDO.setUserId(999999);
    //        ssoAccessDO.setValidDate(new Date());
    //        ssoAccessDAO.insert(ssoAccessDO);
    //    }
    //
    //    @AfterMethod
    //    private void postProcess() {
    //        ssoAccessDAO.deleteByDesc("byronzoz");
    //    }

}
