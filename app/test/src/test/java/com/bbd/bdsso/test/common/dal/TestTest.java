/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.test.common.dal;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.bbd.bdsso.common.dal.daointerface.SsoRoleDAO;
import com.bbd.bdsso.common.dal.daointerface.SsoUserDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoRoleDO;
import com.bbd.bdsso.common.dal.dataobject.SsoUserDO;
import com.bbd.bdsso.common.dal.dataobject.SsoUserRoleDO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoUserRoleDAO;
import com.bbd.bdsso.common.service.facade.vo.SsoExListVO;
import com.bbd.bdsso.common.service.facade.vo.SsoExVO;
import com.bbd.bdsso.common.service.facade.vo.SsoRoleVO;
import com.bbd.bdsso.core.model.convertor.SsoRoleConvertor;
import com.bbd.bdsso.test.BasicTest;
import com.bbd.commons.lang.util.page.PageList;

/**
 * 
 * @author byron
 * @version $Id: TestTest.java, v 0.1 Nov 7, 2017 4:28:50 PM byron Exp $
 */
public class TestTest extends BasicTest {

    /** 角色DAO */
    @Autowired
    private SsoRoleDAO          ssoRoleDAO;

    /** 用户DAO */
    @Autowired
    private SsoUserDAO          ssoUserDAO;

    /** 手工用户角色DAO */
    @Autowired
    private ExtraSsoUserRoleDAO extraSsoUserRoleDAO;

    @org.testng.annotations.Test
    public void Test() {

        SsoExListVO<SsoRoleVO> result = new SsoExListVO<SsoRoleVO>();

        // 首先模糊查询
        PageList pageList = extraSsoUserRoleDAO.fuzzyQueryForPage("", 15, 0);

        @SuppressWarnings("unchecked")
        ArrayList<SsoUserRoleDO> listCopy = (ArrayList<SsoUserRoleDO>) pageList.clone();

        // 结果列表
        HashMap<String, ArrayList<SsoRoleVO>> resultList = new HashMap<String, ArrayList<SsoRoleVO>>();

        for (SsoUserRoleDO ssoUserRoleDO : listCopy) {

            // 填充用户数据
            SsoUserDO ssoUserDO = ssoUserDAO.query(ssoUserRoleDO.getUserId());
            if (resultList.containsKey(ssoUserDO.getUserName())) {
                resultList.get(ssoUserDO.getUserName()).add(SsoRoleConvertor.convertDo2Vo(ssoRoleDAO.query(ssoUserRoleDO.getRoleId())));
                continue;
            }

            ArrayList<SsoRoleVO> ssoRoleList = new ArrayList<SsoRoleVO>();
            SsoRoleDO ssoRoleDO = ssoRoleDAO.query(ssoUserRoleDO.getRoleId());
            ssoRoleList.add(SsoRoleConvertor.convertDo2Vo(ssoRoleDO));

            // 填充结果数据
            resultList.put(ssoUserDO.getUserName(), ssoRoleList);
        }

        // 填充返回数据
        ArrayList<SsoExVO<SsoRoleVO>> list = new ArrayList<SsoExVO<SsoRoleVO>>();

        for (String s : resultList.keySet()) {
            SsoExVO<SsoRoleVO> ssoExVO = new SsoExVO<SsoRoleVO>();
            ssoExVO.setId(ssoUserDAO.queryByName(s).getId().toString());
            ssoExVO.setName(s);
            ssoExVO.setList(resultList.get(s));
            list.add(ssoExVO);
        }

        // 设置结果
        result.setResultList(list);
        result.setPageNum(15);
        result.setPageSize(0);
        result.setTotal(list.size());

        System.out.println(result.toString());
    }

}
