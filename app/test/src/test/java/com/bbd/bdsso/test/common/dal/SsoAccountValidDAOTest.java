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

import com.bbd.bdsso.common.dal.daointerface.SsoAccountValidDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoAccountValidDO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoAccountValidDAO;
import com.bbd.bdsso.common.util.DateUtils;
import com.bbd.bdsso.common.util.SsoConstant;
import com.bbd.bdsso.test.BasicTest;

/**
 * SSO账户验证测试用例
 * 
 * @author byron
 * @version $Id: SsoAccountValidDAOTest.java, v 0.1 Sep 19, 2017 1:42:42 PM byron Exp $
 */
public class SsoAccountValidDAOTest extends BasicTest {

    /** SSO账户验证DAO */
    @Autowired
    private SsoAccountValidDAO      ssoAccountValidDAO;

    /** SSO手工账户验证DAO */
    @Autowired
    private ExtraSsoAccountValidDAO extraSsoAccountValidDAO;

    @Test
    private void insertTest() {
        SsoAccountValidDO ssoAccountValidDO = new SsoAccountValidDO();
        ssoAccountValidDO.setEmail("zhangwei@bbdservice.com");
        ssoAccountValidDO.setGmtCreate(new Date());
        ssoAccountValidDO.setGmtModified(new Date());
        ssoAccountValidDO.setIsVerified(0);
        ssoAccountValidDO.setType(0);
        ssoAccountValidDO.setValidDate(DateUtils.plusDuration(SsoConstant.TOKEN_VALID_DURATION));
        ssoAccountValidDO.setVerifyCode("ff82f3863250c8ceafb92eb4f06b55c6");
        ssoAccountValidDO.setDescription("byronzoz");
        System.out.println(ssoAccountValidDAO.insert(ssoAccountValidDO));
    }

    @Test
    private void deleteTest() {
        ssoAccountValidDAO.delete(999999);
    }

    @Test
    private void queryTest() {
        ssoAccountValidDAO.query(999999);
    }

    @Test
    private void updateByEmailTest() {
        ssoAccountValidDAO.updateByEmailAndType(1, new Date(), "zhangwei@bbdservice.com", 1);
    }

    @Test
    private void queryByEmailTest() {
        ssoAccountValidDAO.queryByEmail("zhangwei@bbdservice.com");
    }

    @Test
    private void fuzzyQueryForPageTest() {
        extraSsoAccountValidDAO.fuzzyQueryForPage("2017", 4, 0);
    }

    @BeforeMethod
    private void beforeProcess() {
        ssoAccountValidDAO.deleteByDesc("byronzoz");
        SsoAccountValidDO ssoAccountValidDO = new SsoAccountValidDO();
        ssoAccountValidDO.setEmail("zhangwei@bbdservice.com");
        ssoAccountValidDO.setGmtCreate(new Date());
        ssoAccountValidDO.setGmtModified(new Date());
        ssoAccountValidDO.setIsVerified(0);
        ssoAccountValidDO.setType(0);
        ssoAccountValidDO.setValidDate(DateUtils.plusDuration(SsoConstant.TOKEN_VALID_DURATION));
        ssoAccountValidDO.setVerifyCode("ff82f3863250c8ceafb92eb4f06b55c6");
        ssoAccountValidDO.setDescription("byronzoz");
        ssoAccountValidDAO.insert(ssoAccountValidDO);
    }

    @AfterMethod
    private void postProcess() {
        ssoAccountValidDAO.deleteByDesc("byronzoz");
    }

}
