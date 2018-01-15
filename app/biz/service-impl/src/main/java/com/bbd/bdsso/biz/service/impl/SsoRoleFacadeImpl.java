/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.biz.service.impl;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbd.bdsso.biz.service.impl.aop.AuthValidate;
import com.bbd.bdsso.biz.service.impl.template.BdssoCallBack;
import com.bbd.bdsso.biz.service.impl.template.BdssoTemplate;
import com.bbd.bdsso.common.dal.daointerface.SsoRoleDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoRoleDO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoRoleDAO;
import com.bbd.bdsso.common.service.facade.SsoRoleFacade;
import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;
import com.bbd.bdsso.common.service.facade.result.BdssoForPageResult;
import com.bbd.bdsso.common.service.facade.result.BdssoRoleResult;
import com.bbd.bdsso.common.service.facade.vo.SsoRoleVO;
import com.bbd.bdsso.common.util.SsoConstant;
import com.bbd.bdsso.common.util.SsoStringUtils;
import com.bbd.bdsso.common.util.enums.AuthCodeEnum;
import com.bbd.bdsso.common.util.enums.AuthTypeEnum;
import com.bbd.bdsso.common.util.enums.BdssoResultEnum;
import com.bbd.bdsso.common.util.enums.RoleEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;
import com.bbd.bdsso.core.model.convertor.SsoRoleConvertor;
import com.bbd.commons.lang.util.AssertUtils;
import com.bbd.commons.lang.util.page.PageList;

/**
 * SSO角色服务实现
 * 
 * @author byron
 * @version $Id: SsoRoleFacadeImpl.java, v 0.1 Sep 28, 2017 5:32:38 PM byron Exp $
 */
public class SsoRoleFacadeImpl implements SsoRoleFacade {

    /** 日志 */
    private final Logger    logger = LoggerFactory.getLogger(SsoRoleFacadeImpl.class);

    /** 用户角色DAO */
    @Autowired
    private SsoRoleDAO      ssoRoleDAO;

    /** 手工用户角色DAO */
    @Autowired
    private ExtraSsoRoleDAO extraSsoRoleDAO;

    /** 事务模板 */
    @Autowired
    private BdssoTemplate   bdssoTransactionTemplate;

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoRoleFacade#add(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoRoleVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult add(String uid, String token, SsoRoleVO ssoRoleVO) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ssoRoleVO, "实体为空");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoRoleVO.getRoleName()) <= SsoConstant.MAX_NAME_LENGH, "名称字符最长为32");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoRoleVO.getDescription()) <= SsoConstant.MAX_DESC_LENGH, "描述字符最长为64");
            }

            @Override
            public void service() {
                ssoRoleDAO.insert(SsoRoleConvertor.convertVo2Do(ssoRoleVO));
                logger.info("新增角色，[ssoRoleVO={}]", ssoRoleVO.toString());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoRoleFacade#delete(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult delete(String uid, String token, String id) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(id, "资源id为空");
            }

            @Override
            public void service() {

                // bdsso角色不允许删除
                SsoRoleDO ssoRoleDO = ssoRoleDAO.query(Integer.parseInt(id));
                if (null != ssoRoleDO
                    && (StringUtils.equals(ssoRoleDO.getRoleName(), RoleEnum.BDSSO_ADMIN_R.getCode()) || StringUtils.equals(ssoRoleDO.getRoleName(), RoleEnum.BDSSO_COMMON_USER_R.getCode()))) {
                    throw new BdssoBaseException(BdssoResultEnum.NOT_ALLOWED);
                }

                ssoRoleDAO.delete(Integer.parseInt(id));
                logger.info("删除角色，[角色id={}]", id);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoRoleFacade#update(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoRoleVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult update(String uid, String token, SsoRoleVO ssoRoleVO) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ssoRoleVO, "实体为空");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoRoleVO.getRoleName()) <= SsoConstant.MAX_NAME_LENGH, "名称字符最长为32");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoRoleVO.getDescription()) <= SsoConstant.MAX_DESC_LENGH, "描述字符最长为64");
            }

            @Override
            public void service() {
                ssoRoleDAO.update(SsoRoleConvertor.convertVo2Do(ssoRoleVO));
                logger.info("更新角色，[ssoRoleVO={}]", ssoRoleVO.toString());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoRoleFacade#queryById(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoRoleResult queryById(String uid, String token, String id) {
        final BdssoRoleResult result = new BdssoRoleResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(id, "资源id为空");
            }

            @Override
            public void service() {
                ArrayList<SsoRoleDO> list = new ArrayList<SsoRoleDO>();

                // 查询
                SsoRoleDO ssoRoleDO = ssoRoleDAO.query(Integer.parseInt(id));
                list.add(ssoRoleDO);

                // 设置结果
                result.setResultList(SsoRoleConvertor.convertDos2Vos(list));
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoRoleFacade#fuzzyQueryForPage(java.lang.String, java.lang.String, int, int, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES, authCode = AuthCodeEnum.BDSSO_COMMON_USER_A)
    public BdssoForPageResult<SsoRoleVO> fuzzyQueryForPage(String uid, String token, int pageSize, int pageNum, String key) {
        final BdssoForPageResult<SsoRoleVO> result = new BdssoForPageResult<SsoRoleVO>();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertPositiveIntegerNumber(pageSize, "每页条数应大于0且为整数");
                AssertUtils.assertIntegerNumberGeZero(pageNum, "页码应大于等于0");
            }

            @Override
            public void service() {

                PageList pageList = extraSsoRoleDAO.fuzzyQueryForPage(key, pageSize, pageNum);

                @SuppressWarnings("unchecked")
                ArrayList<SsoRoleDO> listCopy = (ArrayList<SsoRoleDO>) pageList.clone();

                // 设置结果
                result.setResultList(SsoRoleConvertor.convertDos2Vos(listCopy));
                result.setPageNum(pageList.getPaginator().getPage());
                result.setPageSize(pageList.getPaginator().getItemsPerPage());
                result.setTotal(pageList.getPaginator().getItems());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoRoleFacade#queryByName(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult queryByName(String uid, String token, String roleName) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(roleName, "角色名为空");
            }

            @Override
            public void service() {

                if (null != ssoRoleDAO.queryByRoleName(roleName)) {
                    throw new BdssoBaseException(BdssoResultEnum.ROLE_NAME_ALREADY_EXIST);
                }
            }

        }, result);

        return result;
    }
}
