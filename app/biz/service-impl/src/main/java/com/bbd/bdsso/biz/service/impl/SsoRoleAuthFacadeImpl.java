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
import com.bbd.bdsso.common.dal.daointerface.SsoAuthDAO;
import com.bbd.bdsso.common.dal.daointerface.SsoRoleAuthDAO;
import com.bbd.bdsso.common.dal.daointerface.SsoRoleDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoAuthDO;
import com.bbd.bdsso.common.dal.dataobject.SsoRoleAuthDO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoRoleAuthDAO;
import com.bbd.bdsso.common.dal.manual.dataobject.ExSsoRoleAuthDO;
import com.bbd.bdsso.common.service.facade.SsoRoleAuthFacade;
import com.bbd.bdsso.common.service.facade.result.BdssoAuthResult;
import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;
import com.bbd.bdsso.common.service.facade.result.BdssoRoleAuthResult;
import com.bbd.bdsso.common.service.facade.vo.SsoAuthVO;
import com.bbd.bdsso.common.service.facade.vo.SsoExListVO;
import com.bbd.bdsso.common.service.facade.vo.SsoExVO;
import com.bbd.bdsso.common.service.facade.vo.SsoRoleAuthVO;
import com.bbd.bdsso.common.util.SsoConstant;
import com.bbd.bdsso.common.util.SsoStringUtils;
import com.bbd.bdsso.common.util.enums.AuthCodeEnum;
import com.bbd.bdsso.common.util.enums.AuthEnum;
import com.bbd.bdsso.common.util.enums.AuthTypeEnum;
import com.bbd.bdsso.common.util.enums.BdssoResultEnum;
import com.bbd.bdsso.common.util.enums.RoleEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;
import com.bbd.bdsso.core.model.convertor.SsoAuthConvertor;
import com.bbd.bdsso.core.model.convertor.SsoRoleAuthConvertor;
import com.bbd.commons.lang.util.AssertUtils;
import com.bbd.commons.lang.util.page.PageList;

/**
 * SSO角色权限服务实现
 * 
 * @author byron
 * @version $Id: SsoRoleAuthFacadeImpl.java, v 0.1 Sep 28, 2017 10:29:56 PM byron Exp $
 */
public class SsoRoleAuthFacadeImpl implements SsoRoleAuthFacade {

    /** 日志 */
    private final Logger        logger = LoggerFactory.getLogger(SsoRoleAuthFacadeImpl.class);

    /** 用户角色权限DAO */
    @Autowired
    private SsoRoleAuthDAO      ssoRoleAuthDAO;

    /** 权限DAO */
    @Autowired
    private SsoAuthDAO          ssoAuthDAO;

    /** 角色DAO */
    @Autowired
    private SsoRoleDAO          ssoRoleDAO;

    /** 手工角色权限DAO */
    @Autowired
    private ExtraSsoRoleAuthDAO extraSsoRoleAuthDAO;

    /** 事务模板 */
    @Autowired
    private BdssoTemplate       bdssoTransactionTemplate;

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoRoleAuthFacade#add(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoRoleAuthVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult add(String uid, String token, SsoRoleAuthVO ssoRoleAuthVO) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ssoRoleAuthVO, "实体为空");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoRoleAuthVO.getDescription()) <= SsoConstant.MAX_DESC_LENGH, "描述最长为64个字符");
            }

            @Override
            public void service() {
                ssoRoleAuthDAO.insert(SsoRoleAuthConvertor.convertVo2Do(ssoRoleAuthVO));
                logger.info("新增角色权限，[ssoRoleAuthVO={}]", ssoRoleAuthVO.toString());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoRoleAuthFacade#delete(java.lang.String, java.lang.String, java.lang.String)
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
                ssoRoleAuthDAO.delete(Integer.parseInt(id));
                logger.info("删除角色权限，[角色权限id={}]", id);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoRoleAuthFacade#update(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoRoleAuthVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult update(String uid, String token, SsoRoleAuthVO ssoRoleAuthVO) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ssoRoleAuthVO, "实体为空");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoRoleAuthVO.getDescription()) <= SsoConstant.MAX_DESC_LENGH, "描述最长为64个字符");
            }

            @Override
            public void service() {
                ssoRoleAuthDAO.update(SsoRoleAuthConvertor.convertVo2Do(ssoRoleAuthVO));
                logger.info("更新角色权限，[ssoRoleAuthVO={}]", ssoRoleAuthVO.toString());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoRoleAuthFacade#queryById(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoRoleAuthResult queryById(String uid, String token, String id) {
        final BdssoRoleAuthResult result = new BdssoRoleAuthResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(id, "资源id为空");
            }

            @Override
            public void service() {
                ArrayList<SsoRoleAuthDO> list = new ArrayList<SsoRoleAuthDO>();

                // 查询
                SsoRoleAuthDO ssoRoleAuthDO = ssoRoleAuthDAO.query(Integer.parseInt(id));
                list.add(ssoRoleAuthDO);

                // 设置结果
                result.setResultList(SsoRoleAuthConvertor.convertDos2Vos(list));
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoRoleAuthFacade#fuzzyQueryForPage(java.lang.String, java.lang.String, int, int, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES, authCode = AuthCodeEnum.BDSSO_COMMON_USER_A)
    public SsoExListVO<SsoAuthVO> fuzzyQueryForPage(String uid, String token, int pageSize, int pageNum, String key) {
        final SsoExListVO<SsoAuthVO> result = new SsoExListVO<SsoAuthVO>();

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
                PageList pageList = extraSsoRoleAuthDAO.fuzzyQueryForPage(key, pageSize, pageNum);

                @SuppressWarnings("unchecked")
                ArrayList<ExSsoRoleAuthDO> listCopy = (ArrayList<ExSsoRoleAuthDO>) pageList.clone();

                if (null == listCopy) {
                    throw new BdssoBaseException(BdssoResultEnum.EMPTY_OBJECT);
                }

                ArrayList<SsoExVO<SsoAuthVO>> list = new ArrayList<SsoExVO<SsoAuthVO>>();

                // 填充数据
                for (ExSsoRoleAuthDO exSsoRoleAuthDO : listCopy) {
                    SsoExVO<SsoAuthVO> ssoExVO = new SsoExVO<SsoAuthVO>();
                    ssoExVO.setId(exSsoRoleAuthDO.getRoleId().toString());
                    ssoExVO.setName(exSsoRoleAuthDO.getRoleName());
                    ssoExVO.setList(SsoAuthConvertor.convertDos2Vos(ssoAuthDAO.queryByRoleName(exSsoRoleAuthDO.getRoleName())));
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
     * @see com.bbd.bdsso.common.service.facade.SsoRoleAuthFacade#queryByRoleId(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public BdssoAuthResult queryByRoleId(String uid, String token, String roleId) {
        final BdssoAuthResult result = new BdssoAuthResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(roleId, "角色id为空");
            }

            @Override
            public void service() {

                // 查询
                ArrayList<SsoRoleAuthDO> list = (ArrayList<SsoRoleAuthDO>) ssoRoleAuthDAO.queryByRoleId(Integer.parseInt(roleId));
                AssertUtils.assertNotNull(list, "查询结果为空");

                List<SsoAuthVO> resultList = new ArrayList<SsoAuthVO>();

                for (SsoRoleAuthDO ssoRoleAuthDO : list) {
                    SsoAuthDO ssoAuthDO = ssoAuthDAO.query(ssoRoleAuthDO.getAuthId());
                    resultList.add(SsoAuthConvertor.convertDo2Vo(ssoAuthDO));
                }

                // 设置结果
                result.setResultList(resultList);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoRoleAuthFacade#deleteByParams(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult deleteByParams(String uid, String token, String roleId, String authId) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(roleId, "角色id为空");
                AssertUtils.assertStringNotBlank(authId, "权限id为空");

                // 基础角色权限不允许删除
                if (StringUtils.equals(roleId, ssoRoleDAO.queryByRoleName(RoleEnum.BDSSO_COMMON_USER_R.getCode()).getId().toString())
                    && StringUtils.equals(authId, ssoAuthDAO.queryByAuthCode(AuthEnum.BDSSO_COMMON_USER_A.getCode()).getId().toString())) {
                    throw new BdssoBaseException(BdssoResultEnum.NOT_ALLOWED);
                }

                if (StringUtils.equals(roleId, ssoRoleDAO.queryByRoleName(RoleEnum.BDSSO_ADMIN_R.getCode()).getId().toString())
                    && StringUtils.equals(authId, ssoAuthDAO.queryByAuthCode(AuthEnum.BDSSO_ADMIN_A.getCode()).getId().toString())) {
                    throw new BdssoBaseException(BdssoResultEnum.NOT_ALLOWED);
                }
            }

            @Override
            public void service() {
                ssoRoleAuthDAO.deleteByRoleIdAndAuthId(Integer.parseInt(roleId), Integer.parseInt(authId));
                logger.info("删除角色权限配置，[roleId={}, authId={}]", roleId, authId);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoRoleAuthFacade#deleteByRoleId(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult deleteByRoleId(String uid, String token, String roleId) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(roleId, "角色id为空");

                // 基础角色权限不允许删除
                if (StringUtils.equals(roleId, ssoRoleDAO.queryByRoleName(RoleEnum.BDSSO_COMMON_USER_R.getCode()).getId().toString())
                    || StringUtils.equals(roleId, ssoRoleDAO.queryByRoleName(RoleEnum.BDSSO_ADMIN_R.getCode()).getId().toString())) {
                    throw new BdssoBaseException(BdssoResultEnum.NOT_ALLOWED);
                }
            }

            @Override
            public void service() {
                ssoRoleAuthDAO.deleteByRoleId(Integer.parseInt(roleId));
                logger.info("通过角色id删除权限配置，[roleId={}]", roleId);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoRoleAuthFacade#queryByRoleIdAndAuthId(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoRoleAuthResult queryByRoleIdAndAuthId(String uid, String token, String roleId, String authId) {
        final BdssoRoleAuthResult result = new BdssoRoleAuthResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(roleId, "角色id为空");
                AssertUtils.assertStringNotBlank(authId, "权限id为空");
            }

            @Override
            public void service() {
                ArrayList<SsoRoleAuthDO> list = new ArrayList<SsoRoleAuthDO>();

                // 查询
                SsoRoleAuthDO ssoRoleAuthDO = ssoRoleAuthDAO.queryByRoleIdAndAuthId(Integer.parseInt(roleId), Integer.parseInt(authId));
                list.add(ssoRoleAuthDO);

                // 设置结果
                result.setResultList(SsoRoleAuthConvertor.convertDos2Vos(list));
            }

        }, result);

        return result;
    }
}
