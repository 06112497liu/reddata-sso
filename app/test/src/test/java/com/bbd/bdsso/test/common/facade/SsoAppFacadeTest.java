/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.test.common.facade;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bbd.bdsso.common.dal.daointerface.SsoAppDAO;
import com.bbd.bdsso.common.service.facade.SsoAppFacade;
import com.bbd.bdsso.common.service.facade.result.BdssoUserResult;
import com.bbd.bdsso.common.service.facade.vo.SsoAppVO;
import com.bbd.bdsso.common.service.facade.vo.SsoFileVO;
import com.bbd.bdsso.test.BasicTest;

/**
 * 应用管理服务接口测试用例
 * 
 * @author byron
 * @version $Id: SsoAppFacadeTest.java, v 0.1 Oct 9, 2017 10:48:32 AM byron Exp $
 */
public class SsoAppFacadeTest extends BasicTest {

    private static final String UID   = "1";

    private static final String ID    = "1";

    private static final String TOKEN = "33a90dcf0ef4845e1e57b26033e8f0cf";

    @Autowired
    private SsoAppFacade        ssoAppFacade;

    @Autowired
    private SsoAppDAO           ssoAppDAO;

    @BeforeClass
    private void beforeProcess() {
        ssoAppDAO.deleteByDesc("byronzoz");
    }

    @AfterClass
    private void postProcess() {
        ssoAppDAO.deleteByDesc("byronzoz");
    }

    @Test
    private void addTest() {
        ssoAppFacade.add(UID, TOKEN, constr());
    }

    @Test
    private void deleteTest() {
        ssoAppFacade.delete(UID, TOKEN, ID);
    }

    @Test
    private void updateTest() {
        ssoAppFacade.update(UID, TOKEN, constr());
    }

    @Test
    private void queryForIdTest() {
        ssoAppFacade.queryById(UID, TOKEN, ID);
    }

    @Test
    private void fuzzyQueryForPage() {
        ssoAppFacade.fuzzyQueryForPage(UID, TOKEN, 0, 1, "2017");
    }

    @Test
    private void upload() throws IOException {

        String file = "/Users/byron/Desktop/1.png";
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = IOUtils.toByteArray(inputStream);

        SsoFileVO ssoFileVO = new SsoFileVO();
        ssoFileVO.setFileName("1.png");
        ssoFileVO.setFileData(bytes);
        ssoAppFacade.uploadFile(UID, TOKEN, ssoFileVO);
    }

    @Test
    private void parseTicketTest() {
        BdssoUserResult result = ssoAppFacade.parseTicket(ssoAppFacade.buildTicket(UID, TOKEN).getTicket());
        System.out.print(result);
    }

    private SsoAppVO constr() {
        SsoAppVO ssoAppVO = new SsoAppVO();
        ssoAppVO.setAppLogo("logo");
        ssoAppVO.setAppName("name");
        ssoAppVO.setAppPic("pic");
        ssoAppVO.setDescription("byronzoz");
        ssoAppVO.setGmtCreate(new Date());
        ssoAppVO.setGmtModified(new Date());
        ssoAppVO.setIsEnable(1);
        ssoAppVO.setLastModifier("admin");

        return ssoAppVO;
    }

}
