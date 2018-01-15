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

import com.bbd.bdsso.common.dal.daointerface.SsoCacheManagerDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoCacheManagerDO;
import com.bbd.bdsso.test.BasicTest;

/**
 * SSO本地缓存管理器测试用例
 * 
 * @author byron
 * @version $Id: SsoCacheManagerDAO.java, v 0.1 Sep 19, 2017 4:20:55 PM byron Exp $
 */
public class SsoCacheManagerTest extends BasicTest {

    /** SSO缓存管理DAO */
    @Autowired
    private SsoCacheManagerDAO ssoCacheManagerDAO;

    @Test
    public void insertTest() {
        ssoCacheManagerDAO.insert(constr());
    }

    @Test
    public void queryByCacheNameTest() {
        ssoCacheManagerDAO.queryByCacheName("test", "tset", "test");
    }

    @Test
    public void queryByStatusTest() {
        ssoCacheManagerDAO.queryByStatus("test", "test", "test");
    }

    @Test
    public void updateStatusByNameTest() {
        ssoCacheManagerDAO.updateStatusByName("test", "test");
    }

    @Test
    public void updateByIdTest() {
        ssoCacheManagerDAO.updateById("test", 1);
    }

    @AfterMethod
    private void postProcess() {
        ssoCacheManagerDAO.deleteByDesc("byronzoz");
    }

    @BeforeMethod
    private void beforeProcess() {
        ssoCacheManagerDAO.deleteByDesc("byronzoz");
    }

    public SsoCacheManagerDO constr() {
        SsoCacheManagerDO ssoCacheManagerDO = new SsoCacheManagerDO();
        ssoCacheManagerDO.setCacheName("test");
        ssoCacheManagerDO.setServerHost("test");
        ssoCacheManagerDO.setServerIp("test");
        ssoCacheManagerDO.setStatus("test");
        ssoCacheManagerDO.setDescription("byronzoz");
        ssoCacheManagerDO.setGmtCreate(new Date());
        ssoCacheManagerDO.setGmtModified(new Date());
        return ssoCacheManagerDO;
    }

}
