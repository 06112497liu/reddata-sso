/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbd.bdsso.biz.service.impl.aop.AuthValidate;
import com.bbd.bdsso.biz.service.impl.template.BdssoCallBack;
import com.bbd.bdsso.biz.service.impl.template.BdssoTemplate;
import com.bbd.bdsso.common.dal.daointerface.SsoRoleDAO;
import com.bbd.bdsso.common.dal.daointerface.SsoUserRoleDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoRoleDO;
import com.bbd.bdsso.common.dal.dataobject.SsoUserRoleDO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoUserRoleDAO;
import com.bbd.bdsso.common.dal.manual.dataobject.ExSsoUserRoleDO;
import com.bbd.bdsso.common.service.facade.SsoUserRoleFacade;
import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;
import com.bbd.bdsso.common.service.facade.result.BdssoRoleResult;
import com.bbd.bdsso.common.service.facade.result.BdssoUserRoleResult;
import com.bbd.bdsso.common.service.facade.vo.SsoExListVO;
import com.bbd.bdsso.common.service.facade.vo.SsoExVO;
import com.bbd.bdsso.common.service.facade.vo.SsoRoleVO;
import com.bbd.bdsso.common.service.facade.vo.SsoUserRoleVO;
import com.bbd.bdsso.common.util.SsoConstant;
import com.bbd.bdsso.common.util.SsoStringUtils;
import com.bbd.bdsso.common.util.enums.AuthCodeEnum;
import com.bbd.bdsso.common.util.enums.AuthTypeEnum;
import com.bbd.bdsso.common.util.enums.BdssoResultEnum;
import com.bbd.bdsso.common.util.enums.RoleEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;
import com.bbd.bdsso.core.model.convertor.SsoRoleConvertor;
import com.bbd.bdsso.core.model.convertor.SsoUserRoleConvertor;
import com.bbd.commons.lang.util.AssertUtils;
import com.bbd.commons.lang.util.page.PageList;

/**
 * SSO用户角色服务实现
 * 
 * @author byron
 * @version $Id: SsoUserRoleFacadeImpl.java, v 0.1 Sep 28, 2017 10:41:53 PM byron Exp $
 */
public class SsoUserRoleFacadeImpl implements SsoUserRoleFacade {

    /** 日志 */
    private final Logger        logger = LoggerFactory.getLogger(SsoUserRoleFacadeImpl.class);

    /** 用户角色DAO */
    @Autowired
    private SsoUserRoleDAO      ssoUserRoleDAO;

    /** 角色DAO */
    @Autowired
    private SsoRoleDAO          ssoRoleDAO;

    /** 手工用户角色DAO */
    @Autowired
    private ExtraSsoUserRoleDAO extraSsoUserRoleDAO;

    /** 事务模板 */
    @Autowired
    private BdssoTemplate       bdssoTransactionTemplate;

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserRoleFacade#add(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoUserRoleVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult add(String uid, String token, SsoUserRoleVO ssoUserRoleVO) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ssoUserRoleVO, "实体为空");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoUserRoleVO.getDescription()) <= SsoConstant.MAX_DESC_LENGH, "描述最长为64个字符");
            }

            @Override
            public void service() {
                ssoUserRoleDAO.insert(SsoUserRoleConvertor.convertVo2Do(ssoUserRoleVO));
                logger.info("新增用户角色，[ssoUserRoleVO={}]", ssoUserRoleVO.toString());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserRoleFacade#delete(java.lang.String, java.lang.String, java.lang.String)
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
                ssoUserRoleDAO.delete(Integer.parseInt(id));
                logger.info("删除用户角色，[角色id={}]", id);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserRoleFacade#update(java.lang.String, java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoUserRoleVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult update(String uid, String token, SsoUserRoleVO ssoUserRoleVO) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ssoUserRoleVO, "实体为空");
            }

            @Override
            public void service() {
                ssoUserRoleDAO.update(SsoUserRoleConvertor.convertVo2Do(ssoUserRoleVO));
                logger.info("更新用户角色，[ssoUserRoleVO={}]", ssoUserRoleVO.toString());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserRoleFacade#queryById(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoUserRoleResult queryById(String uid, String token, String id) {
        final BdssoUserRoleResult result = new BdssoUserRoleResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(id, "资源id为空");
            }

            @Override
            public void service() {
                ArrayList<SsoUserRoleDO> list = new ArrayList<SsoUserRoleDO>();

                // 查询
                SsoUserRoleDO ssoUserRoleDO = ssoUserRoleDAO.query(Integer.parseInt(id));
                list.add(ssoUserRoleDO);

                // 设置结果
                result.setResultList(SsoUserRoleConvertor.convertDos2Vos(list));
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserRoleFacade#fuzzyQueryForPage(java.lang.String, java.lang.String, int, int, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES, authCode = AuthCodeEnum.BDSSO_COMMON_USER_A)
    public SsoExListVO<SsoRoleVO> fuzzyQueryForPage(String uid, String token, int pageSize, int pageNum, String key) {
        final SsoExListVO<SsoRoleVO> result = new SsoExListVO<SsoRoleVO>();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertPositiveIntegerNumber(pageSize, "每页条数应大于0且为整数");
                AssertUtils.assertIntegerNumberGeZero(pageNum, "页码应大于等于0");
            }

            @Override
            public void service() {

                // 首先模糊查询
                PageList pageList = extraSsoUserRoleDAO.fuzzyQueryForPage(key, pageSize, pageNum);

                @SuppressWarnings("unchecked")
                ArrayList<ExSsoUserRoleDO> listCopy = (ArrayList<ExSsoUserRoleDO>) pageList.clone();

                if (null == listCopy) {
                    throw new BdssoBaseException(BdssoResultEnum.EMPTY_OBJECT);
                }

                ArrayList<SsoExVO<SsoRoleVO>> list = new ArrayList<SsoExVO<SsoRoleVO>>();

                // 填充数据
                for (ExSsoUserRoleDO exSsoUserRoleDO : listCopy) {
                    SsoExVO<SsoRoleVO> ssoExVO = new SsoExVO<SsoRoleVO>();
                    ssoExVO.setId(exSsoUserRoleDO.getUserId().toString());
                    ssoExVO.setName(exSsoUserRoleDO.getUserName());
                    ssoExVO.setList(SsoRoleConvertor.convertDos2Vos(ssoRoleDAO.queryByUserName(exSsoUserRoleDO.getUserName())));
                    list.add(ssoExVO);
                }

                // 设置结果
                result.setResultList(list);
                result.setPageNum(pageList.getPaginator().getPage());
                result.setPageSize(pageList.getPaginator().getItemsPerPage());
                result.setTotal(pageList.getPaginator().getItems());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserRoleFacade#queryByUserId(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoRoleResult queryByUserId(String uid, String token, String userId) {
        final BdssoRoleResult result = new BdssoRoleResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(userId, "资源id为空");
            }

            @Override
            public void service() {

                // 查询
                ArrayList<SsoUserRoleDO> list = (ArrayList<SsoUserRoleDO>) ssoUserRoleDAO.queryByUserId(Integer.parseInt(userId));
                AssertUtils.assertNotNull(list, "查询结果为空");

                List<SsoRoleVO> resultList = new ArrayList<SsoRoleVO>();

                for (SsoUserRoleDO ssoUserRoleDO : list) {
                    SsoRoleDO ssoRoleDO = ssoRoleDAO.query(ssoUserRoleDO.getRoleId());
                    resultList.add(SsoRoleConvertor.convertDo2Vo(ssoRoleDO));
                }

                // 设置结果
                result.setResultList(resultList);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserRoleFacade#deleteByParams(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult deleteByParams(String uid, String token, String userId, String roleId) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(userId, SsoConstant.INVALID_TICKET);
                AssertUtils.assertStringNotBlank(roleId, SsoConstant.INVALID_TICKET);

                // 基础用户权限不允许删除
                if (StringUtils.equals(roleId, ssoRoleDAO.queryByRoleName(RoleEnum.BDSSO_COMMON_USER_R.getCode()).getId().toString())) {
                    throw new BdssoBaseException(BdssoResultEnum.NOT_ALLOWED);
                }
            }

            @Override
            public void service() {
                ssoUserRoleDAO.deleteByUserIdAndRoleId(Integer.parseInt(userId), Integer.parseInt(roleId));
                logger.info("删除用户角色配置，[userId={}, roleId ={}]", userId, roleId);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserRoleFacade#deleteByUserId(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult deleteByUserId(String uid, String token, String userId) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(userId, SsoConstant.INVALID_TICKET);
            }

            @Override
            public void service() {
                ssoUserRoleDAO.deleteByUserId(Integer.parseInt(userId));
                logger.info("删除角色配置，[userId={}]", userId);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoUserRoleFacade#queryByUserIdAndRoleId(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoUserRoleResult queryByUserIdAndRoleId(String uid, String token, String userId, String roleId) {
        final BdssoUserRoleResult result = new BdssoUserRoleResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(userId, SsoConstant.INVALID_TICKET);
                AssertUtils.assertStringNotBlank(roleId, SsoConstant.INVALID_TICKET);
            }

            @Override
            public void service() {
                ArrayList<SsoUserRoleDO> list = new ArrayList<SsoUserRoleDO>();

                // 查询
                SsoUserRoleDO ssoUserRoleDO = ssoUserRoleDAO.queryByUserIdAndRoleId(Integer.parseInt(userId), Integer.parseInt(roleId));
                list.add(ssoUserRoleDO);

                // 设置结果
                result.setResultList(SsoUserRoleConvertor.convertDos2Vos(list));
            }

        }, result);

        return result;
    }

}
