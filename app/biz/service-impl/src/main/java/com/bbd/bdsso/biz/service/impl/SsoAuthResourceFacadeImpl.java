/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbd.bdsso.biz.service.impl.aop.AuthValidate;
import com.bbd.bdsso.biz.service.impl.template.BdssoCallBack;
import com.bbd.bdsso.biz.service.impl.template.BdssoTemplate;
import com.bbd.bdsso.common.dal.daointerface.SsoAuthResourceDAO;
import com.bbd.bdsso.common.dal.daointerface.SsoResourceDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoAuthResourceDO;
import com.bbd.bdsso.common.dal.dataobject.SsoResourceDO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoAuthResourceDAO;
import com.bbd.bdsso.common.dal.manual.dataobject.ExSsoAuthResourceDO;
import com.bbd.bdsso.common.service.facade.SsoAuthResourceFacade;
import com.bbd.bdsso.common.service.facade.result.BdssoAuthResourceResult;
import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;
import com.bbd.bdsso.common.service.facade.result.BdssoResourceResult;
import com.bbd.bdsso.common.service.facade.vo.SsoAuthResourceVO;
import com.bbd.bdsso.common.service.facade.vo.SsoExListVO;
import com.bbd.bdsso.common.service.facade.vo.SsoExVO;
import com.bbd.bdsso.common.service.facade.vo.SsoResourceVO;
import com.bbd.bdsso.common.util.SsoConstant;
import com.bbd.bdsso.common.util.SsoStringUtils;
import com.bbd.bdsso.common.util.enums.AuthCodeEnum;
import com.bbd.bdsso.common.util.enums.AuthTypeEnum;
import com.bbd.bdsso.common.util.enums.BdssoResultEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;
import com.bbd.bdsso.core.model.convertor.SsoAuthResourceConvertor;
import com.bbd.bdsso.core.model.convertor.SsoResourceConvertor;
import com.bbd.commons.lang.util.AssertUtils;
import com.bbd.commons.lang.util.page.PageList;

/**
 * 用户角色权限服务实现
 * 
 * @author byron
 * @version $Id: SsoAuthResourceFacadeImpl.java, v 0.1 Nov 16, 2017 10:01:04 AM byron Exp $
 */
public class SsoAuthResourceFacadeImpl implements SsoAuthResourceFacade {

    /** 日志 */
    private final Logger            logger = LoggerFactory.getLogger(SsoRoleAuthFacadeImpl.class);

    /** 事务模板 */
    @Autowired
    private BdssoTemplate           bdssoTransactionTemplate;

    /** 用户权限资源DAO */
    @Autowired
    private SsoAuthResourceDAO      ssoAuthResourceDAO;

    /** 手工权限资源DAO */
    @Autowired
    private ExtraSsoAuthResourceDAO extraSsoAuthResourceDAO;

    /** 资源DAO */
    @Autowired
    private SsoResourceDAO          ssoResourceDAO;

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAuthResourceFacade#add(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoAuthResourceVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult add(String uid, String token, SsoAuthResourceVO ssoAuthResourceVO) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ssoAuthResourceVO, "实体为空");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoAuthResourceVO.getDescription()) <= SsoConstant.MAX_DESC_LENGH, "描述最长为64个字符");
            }

            @Override
            public void service() {
                ssoAuthResourceDAO.insert(SsoAuthResourceConvertor.convertVo2Do(ssoAuthResourceVO));
                logger.info("新增权限资源，[ssoAuthResourceVO={}]", ssoAuthResourceVO.toString());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAuthResourceFacade#delete(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult delete(String uid, String token, String id) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(id, "权限资源id为空");
            }

            @Override
            public void service() {
                ssoAuthResourceDAO.delete(Integer.parseInt(id));
                logger.info("删除权限资源，[权限资源id={}]", id);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAuthResourceFacade#update(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoAuthResourceVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult update(String uid, String token, SsoAuthResourceVO ssoAuthResourceVO) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ssoAuthResourceVO, "实体为空");
            }

            @Override
            public void service() {
                ssoAuthResourceDAO.update(SsoAuthResourceConvertor.convertVo2Do(ssoAuthResourceVO));
                logger.info("更新权限资源，[ssoAuthResourceVO={}]", ssoAuthResourceVO.toString());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAuthResourceFacade#queryById(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoAuthResourceResult queryById(String uid, String token, String id) {
        final BdssoAuthResourceResult result = new BdssoAuthResourceResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(id, "资源id为空");
            }

            @Override
            public void service() {
                ArrayList<SsoAuthResourceDO> list = new ArrayList<SsoAuthResourceDO>();

                // 查询
                SsoAuthResourceDO ssoAuthResourceDO = ssoAuthResourceDAO.query(Integer.parseInt(id));
                list.add(ssoAuthResourceDO);

                // 设置结果
                result.setResultList(SsoAuthResourceConvertor.convertDos2Vos(list));
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAuthResourceFacade#fuzzyQueryForPage(java.lang.String, java.lang.String, int, int, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES, authCode = AuthCodeEnum.BDSSO_COMMON_USER_A)
    public SsoExListVO<SsoResourceVO> fuzzyQueryForPage(String uid, String token, int pageSize, int pageNum, String key) {
        final SsoExListVO<SsoResourceVO> result = new SsoExListVO<SsoResourceVO>();

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
                PageList pageList = extraSsoAuthResourceDAO.fuzzyQueryForPage(key, pageSize, pageNum);

                @SuppressWarnings("unchecked")
                ArrayList<ExSsoAuthResourceDO> listCopy = (ArrayList<ExSsoAuthResourceDO>) pageList.clone();

                if (null == listCopy) {
                    throw new BdssoBaseException(BdssoResultEnum.EMPTY_OBJECT);
                }

                ArrayList<SsoExVO<SsoResourceVO>> list = new ArrayList<SsoExVO<SsoResourceVO>>();

                // 填充数据
                for (ExSsoAuthResourceDO exSsoAuthResourceDO : listCopy) {
                    SsoExVO<SsoResourceVO> ssoExVO = new SsoExVO<SsoResourceVO>();
                    ssoExVO.setId(exSsoAuthResourceDO.getAuthId().toString());
                    ssoExVO.setName(exSsoAuthResourceDO.getAuthName());
                    ssoExVO.setList(SsoResourceConvertor.convertDos2Vos(ssoResourceDAO.queryByAuthName(exSsoAuthResourceDO.getAuthName())));
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
     * @see com.bbd.bdsso.common.service.facade.SsoAuthResourceFacade#queryByRoleId(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoResourceResult queryByRoleId(String uid, String token, String authId) {
        final BdssoResourceResult result = new BdssoResourceResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(authId, "权限id为空");
            }

            @Override
            public void service() {

                // 查询
                ArrayList<SsoAuthResourceDO> list = (ArrayList<SsoAuthResourceDO>) ssoAuthResourceDAO.queryByAuthId(Integer.parseInt(authId));
                AssertUtils.assertNotNull(list, "查询结果为空");

                List<SsoResourceVO> resultList = new ArrayList<SsoResourceVO>();

                for (SsoAuthResourceDO ssoAuthResourceDO : list) {
                    SsoResourceDO ssoResourceDO = ssoResourceDAO.query(ssoAuthResourceDO.getAuthId());
                    resultList.add(SsoResourceConvertor.convertDo2Vo(ssoResourceDO));
                }

                // 设置结果
                result.setResultList(resultList);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAuthResourceFacade#queryByRoleIdAndAuthId(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoAuthResourceResult queryByRoleIdAndAuthId(String uid, String token, String authId, String resourceId) {
        final BdssoAuthResourceResult result = new BdssoAuthResourceResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(authId, "权限id为空");
                AssertUtils.assertStringNotBlank(resourceId, "资源id为空");
            }

            @Override
            public void service() {
                ArrayList<SsoAuthResourceDO> list = new ArrayList<SsoAuthResourceDO>();

                // 查询
                SsoAuthResourceDO ssoAuthResourceDO = ssoAuthResourceDAO.queryByAuthIdAndResourceId(Integer.parseInt(authId), Integer.parseInt(resourceId));
                list.add(ssoAuthResourceDO);

                // 设置结果
                result.setResultList(SsoAuthResourceConvertor.convertDos2Vos(list));
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAuthResourceFacade#deleteByParams(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult deleteByParams(String uid, String token, String authId, String resourceId) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(authId, "权限id为空");
                AssertUtils.assertStringNotBlank(resourceId, "资源id为空");
            }

            @Override
            public void service() {
                ssoAuthResourceDAO.deleteByAuthIdAndResourceId(Integer.parseInt(authId), Integer.parseInt(resourceId));
                logger.info("删除角色权限配置，[authId={}, resourceId={}]", authId, resourceId);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAuthResourceFacade#deleteByAuthId(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult deleteByAuthId(String uid, String token, String authId) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(authId, "权限id为空");
            }

            @Override
            public void service() {
                ssoAuthResourceDAO.deleteByAuthId(Integer.parseInt(authId));
                logger.info("通过authId删除权限资源配置，[authId={}]", authId);
            }

        }, result);

        return result;
    }

}
