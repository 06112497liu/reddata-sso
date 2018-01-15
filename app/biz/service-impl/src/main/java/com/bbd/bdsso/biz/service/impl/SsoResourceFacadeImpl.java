/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.biz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbd.bdsso.biz.service.impl.aop.AuthValidate;
import com.bbd.bdsso.biz.service.impl.template.BdssoCallBack;
import com.bbd.bdsso.biz.service.impl.template.BdssoTemplate;
import com.bbd.bdsso.common.dal.daointerface.SsoResourceDAO;
import com.bbd.bdsso.common.dal.daointerface.SsoUserDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoResourceDO;
import com.bbd.bdsso.common.dal.dataobject.SsoUserDO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoResourceDAO;
import com.bbd.bdsso.common.service.facade.SsoResourceFacade;
import com.bbd.bdsso.common.service.facade.result.BdssoBaseAuthResult;
import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;
import com.bbd.bdsso.common.service.facade.result.BdssoForPageResult;
import com.bbd.bdsso.common.service.facade.result.BdssoResourceResult;
import com.bbd.bdsso.common.service.facade.vo.SsoResourceVO;
import com.bbd.bdsso.common.util.SsoConstant;
import com.bbd.bdsso.common.util.SsoStringUtils;
import com.bbd.bdsso.common.util.enums.AuthCodeEnum;
import com.bbd.bdsso.common.util.enums.AuthTypeEnum;
import com.bbd.bdsso.common.util.enums.BdssoResultEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;
import com.bbd.bdsso.core.model.convertor.SsoResourceConvertor;
import com.bbd.bdsso.core.service.SsoEncryptService;
import com.bbd.bdsso.core.service.SsoResourceService;
import com.bbd.commons.lang.util.AssertUtils;
import com.bbd.commons.lang.util.page.PageList;

/**
 * 用户资源管理服务实现
 * 
 * @author byron
 * @version $Id: SsoResourceFacadeImpl.java, v 0.1 Nov 15, 2017 6:25:39 PM byron Exp $
 */
public class SsoResourceFacadeImpl implements SsoResourceFacade {

    /** 日志 */
    private final Logger        logger = LoggerFactory.getLogger(SsoResourceFacadeImpl.class);

    /** 事务模板 */
    @Autowired
    private BdssoTemplate       bdssoTransactionTemplate;

    /** SSO资源管理DAO */
    @Autowired
    private SsoResourceDAO      ssoResourceDAO;

    /** SSO资源管理手工DAO */
    @Autowired
    private ExtraSsoResourceDAO extraSsoResourceDAO;

    /** 资源服务 */
    @Autowired
    private SsoResourceService  ssoResourceService;

    /** SSO加解密服务 */
    @Autowired
    private SsoEncryptService   ssoEncryptService;

    /** SSO用户管理DAO */
    @Autowired
    private SsoUserDAO          ssoUserDAO;

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoResourceFacade#add(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoResourceVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult add(String uid, String token, SsoResourceVO ssoResourceVO) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ssoResourceVO, "实体为空");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoResourceVO.getResourceName()) <= SsoConstant.MAX_NAME_LENGH, "资源名最长为32个字符");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoResourceVO.getDescription()) <= SsoConstant.MAX_DESC_LENGH, "描述最长为64个字符");
            }

            @Override
            public void service() {
                ssoResourceDAO.insert(SsoResourceConvertor.convertVo2Do(ssoResourceVO));
                logger.info("新增资源，[ssoResourceVO={}]", ssoResourceVO.toString());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoResourceFacade#delete(java.lang.String, java.lang.String, java.lang.String)
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
                ssoResourceDAO.delete(Integer.parseInt(id));
                logger.info("删除资源，[资源id={}]", id);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoResourceFacade#update(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoResourceVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult update(String uid, String token, SsoResourceVO ssoResourceVO) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ssoResourceVO, "实体为空");
            }

            @Override
            public void service() {
                ssoResourceDAO.update(SsoResourceConvertor.convertVo2Do(ssoResourceVO));
                logger.info("更新资源，[ssoResourceVO={}]", ssoResourceVO.toString());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoResourceFacade#queryById(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoResourceResult queryById(String uid, String token, String id) {
        final BdssoResourceResult result = new BdssoResourceResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(id, "资源id为空");
            }

            @Override
            public void service() {
                ArrayList<SsoResourceDO> list = new ArrayList<SsoResourceDO>();

                // 查询
                SsoResourceDO ssoResourceDO = ssoResourceDAO.query(Integer.parseInt(id));
                list.add(ssoResourceDO);

                // 设置结果
                result.setResultList(SsoResourceConvertor.convertDos2Vos(list));
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoResourceFacade#queryByResourceName(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult queryByResourceName(String uid, String token, String resourceName) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(resourceName, "资源名为空");
            }

            @Override
            public void service() {

                if (null != ssoResourceDAO.queryByResourceName(resourceName)) {
                    throw new BdssoBaseException(BdssoResultEnum.RESOURCE_NAME_ALREADY_EXIST);
                }
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoResourceFacade#fuzzyQueryForPage(java.lang.String, java.lang.String, int, int, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES, authCode = AuthCodeEnum.BDSSO_COMMON_USER_A)
    public BdssoForPageResult<SsoResourceVO> fuzzyQueryForPage(String uid, String token, int pageSize, int pageNum, String key) {
        final BdssoForPageResult<SsoResourceVO> result = new BdssoForPageResult<SsoResourceVO>();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertPositiveIntegerNumber(pageSize, "每页条数应大于0且为整数");
                AssertUtils.assertIntegerNumberGeZero(pageNum, "页码应大于等于0");
            }

            @Override
            public void service() {

                PageList pageList = extraSsoResourceDAO.fuzzyQueryForPage(key, pageSize, pageNum);

                @SuppressWarnings("unchecked")
                ArrayList<SsoResourceDO> listCopy = (ArrayList<SsoResourceDO>) pageList.clone();

                ArrayList<SsoResourceVO> resultList = SsoResourceConvertor.convertDos2Vos(listCopy);

                // 设置结果
                result.setResultList(resultList);
                result.setPageNum(pageList.getPaginator().getPage());
                result.setPageSize(pageList.getPaginator().getItemsPerPage());
                result.setTotal(pageList.getPaginator().getItems());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoResourceFacade#authByParams(java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.NO)
    public BdssoBaseAuthResult authByParams(String ticket, String appName) {
        final BdssoBaseAuthResult result = new BdssoBaseAuthResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(ticket, "用户凭据为空，请检查请求头中的ticket");
                AssertUtils.assertStringNotBlank(appName, "应用名为空");
            }

            @Override
            public void service() {

                // 解密ticket
                HashMap<String, String> sHashMap = ssoEncryptService.decrypt(ticket);

                if (!sHashMap.containsKey(SsoConstant.UID) || StringUtils.isBlank(sHashMap.get(SsoConstant.UID))) {
                    throw new BdssoBaseException(BdssoResultEnum.INVAID_TICKET);
                }

                SsoUserDO ssoUserDO = ssoUserDAO.query(Integer.parseInt(sHashMap.get(SsoConstant.UID)));
                System.out.println(ssoUserDO.getUserName());
                if (null == ssoUserDO) {
                    throw new BdssoBaseException(BdssoResultEnum.USER_NOT_EXIST);
                }

                HashMap<String, HashMap<String, Boolean>> resultMap = ssoResourceService.queryByUid(sHashMap.get(SsoConstant.UID), appName);

                result.setUid(sHashMap.get(SsoConstant.UID));
                result.setUserName(ssoUserDO.getUserName());
                result.setAuthResult(resultMap.get(ssoUserDO.getUserName()));
            }

        }, result);

        return result;
    }

}
