/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.test.common.facade;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bbd.bdsso.common.dal.daointerface.SsoRoleDAO;
import com.bbd.bdsso.common.service.facade.SsoRoleFacade;
import com.bbd.bdsso.common.service.facade.result.BdssoForPageResult;
import com.bbd.bdsso.common.service.facade.vo.SsoRoleVO;
import com.bbd.bdsso.test.BasicTest;

/**
 * 角色服务接口测试用例
 * 
 * @author byron
 * @version $Id: SsoRoleFacadeTest.java, v 0.1 Oct 9, 2017 11:47:56 AM byron Exp $
 */
public class SsoRoleFacadeTest extends BasicTest {

    private static final String UID   = "33";

    private static final String ID    = "1";

    private static final String TOKEN = "dfdd924fcf89b049e0ec13a98ec36b67";

    @Autowired
    private SsoRoleFacade       ssoRoleFacade;

    @Autowired
    private SsoRoleDAO          ssoRoleDAO;

    @BeforeClass
    private void beforeProcess() {
        ssoRoleDAO.deleteByDesc("byronzoz");
    }

    @AfterClass
    private void postProcess() {
        ssoRoleDAO.deleteByDesc("byronzoz");
    }

    @Test
    private void addTest() {
        ssoRoleFacade.add(UID, TOKEN, constr());
    }

    @Test
    private void deleteTest() {
        ssoRoleFacade.delete(UID, TOKEN, ID);
    }

    @Test
    private void updateTest() {
        ssoRoleFacade.update(UID, TOKEN, constr());
    }

    @Test
    private void queryById() {
        ssoRoleFacade.queryById(UID, TOKEN, ID);
    }

    @Test
    private void fuzzyQueryForPage() {
        BdssoForPageResult<SsoRoleVO> result = ssoRoleFacade.fuzzyQueryForPage(UID, TOKEN, 10, 0, "test");
        System.out.println(result);
    }

    private SsoRoleVO constr() {
        SsoRoleVO ssoRoleVO = new SsoRoleVO();
        ssoRoleVO.setDescription("byronzoz");
        ssoRoleVO.setGmtCreate(new Date());
        ssoRoleVO.setGmtModified(new Date());
        ssoRoleVO.setIsEnable(1);
        ssoRoleVO.setLastModifier("admin");
        ssoRoleVO.setRoleName("test");
        return ssoRoleVO;
    }

}
