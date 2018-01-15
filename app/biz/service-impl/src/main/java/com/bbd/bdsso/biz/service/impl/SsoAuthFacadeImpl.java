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
import com.bbd.bdsso.common.dal.daointerface.SsoAuthDAO;
import com.bbd.bdsso.common.dal.daointerface.SsoUserDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoAuthDO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoAuthDAO;
import com.bbd.bdsso.common.dal.manual.dataobject.ExSsoAuthDO;
import com.bbd.bdsso.common.service.facade.SsoAuthFacade;
import com.bbd.bdsso.common.service.facade.result.BdssoAuthResult;
import com.bbd.bdsso.common.service.facade.result.BdssoBaseAuthResult;
import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;
import com.bbd.bdsso.common.service.facade.result.BdssoForPageResult;
import com.bbd.bdsso.common.service.facade.vo.SsoAuthAddParamVO;
import com.bbd.bdsso.common.service.facade.vo.SsoAuthVO;
import com.bbd.bdsso.common.service.facade.vo.SsoExAuthVO;
import com.bbd.bdsso.common.util.MapUtils;
import com.bbd.bdsso.common.util.SsoConstant;
import com.bbd.bdsso.common.util.SsoStringUtils;
import com.bbd.bdsso.common.util.enums.AuthCodeEnum;
import com.bbd.bdsso.common.util.enums.AuthTypeEnum;
import com.bbd.bdsso.common.util.enums.BdssoResultEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;
import com.bbd.bdsso.core.model.convertor.SsoAuthConvertor;
import com.bbd.bdsso.core.service.SsoAccessService;
import com.bbd.bdsso.core.service.SsoEncryptService;
import com.bbd.commons.lang.util.AssertUtils;
import com.bbd.commons.lang.util.page.PageList;

/**
 * SSO权限服务实现
 * 
 * @author byron
 * @version $Id: SsoAuthFacadeImpl.java, v 0.1 Sep 28, 2017 5:01:37 PM byron Exp $
 */
public class SsoAuthFacadeImpl implements SsoAuthFacade {

    /** 日志 */
    private final Logger      logger = LoggerFactory.getLogger(SsoAuthFacadeImpl.class);

    /** 用户权限DAO */
    @Autowired
    private SsoAuthDAO        ssoAuthDAO;

    /** 手工用户权限DAO */
    @Autowired
    private ExtraSsoAuthDAO   extraSsoAuthDAO;

    /** SSO访问Token验证服务 */
    @Autowired
    private SsoAccessService  ssoAccessService;

    /** 事务模板 */
    @Autowired
    private BdssoTemplate     bdssoTransactionTemplate;

    /** SSO加解密服务 */
    @Autowired
    private SsoEncryptService ssoEncryptService;

    /** SSO用户DAO */
    @Autowired
    private SsoUserDAO        ssoUserDAO;

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAuthFacade#add(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoAuthVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult add(String uid, String token, SsoAuthVO ssoAuthVO) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ssoAuthVO, "实体为空");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoAuthVO.getAuthCode()) <= SsoConstant.MAX_NAME_LENGH, "权限编码最长为32个字符");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoAuthVO.getAuthName()) <= SsoConstant.MAX_NAME_LENGH, "权限名最长为32个字符");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoAuthVO.getDescription()) <= SsoConstant.MAX_DESC_LENGH, "描述最长为64个字符");
            }

            @Override
            public void service() {
                ssoAuthDAO.insert(SsoAuthConvertor.convertVo2Do(ssoAuthVO));
                logger.info("新增权限，[ssoAuthVO={}]", ssoAuthVO.toString());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAuthFacade#delete(java.lang.String, java.lang.String, java.lang.String)
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
                SsoAuthDO ssoAuthDO = ssoAuthDAO.query(Integer.parseInt(id));

                if (null != ssoAuthDO
                    && (StringUtils.equals(ssoAuthDO.getAuthCode(), AuthCodeEnum.BDSSO_ADMIN_A.getCode()) || StringUtils.equals(ssoAuthDO.getAuthCode(), AuthCodeEnum.BDSSO_COMMON_USER_A.getCode()))) {
                    throw new BdssoBaseException(BdssoResultEnum.NOT_ALLOWED);
                }

                ssoAuthDAO.delete(Integer.parseInt(id));
                logger.info("删除权限，[权限id={}]", id);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAuthFacade#update(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoAuthVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult update(String uid, String token, SsoAuthVO ssoAuthVO) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ssoAuthVO, "实体为空");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoAuthVO.getAuthName()) <= SsoConstant.MAX_NAME_LENGH, "权限名最长为32个字符");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoAuthVO.getAuthCode()) <= SsoConstant.MAX_NAME_LENGH, "权限编码最长为32个字符");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoAuthVO.getDescription()) <= SsoConstant.MAX_DESC_LENGH, "描述最长为64个字符");
            }

            @Override
            public void service() {
                ssoAuthDAO.update(SsoAuthConvertor.convertVo2Do(ssoAuthVO));
                logger.info("更新权限，[ssoAuthVO={}]", ssoAuthVO.toString());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAuthFacade#queryById(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoAuthResult queryById(String uid, String token, String id) {
        final BdssoAuthResult result = new BdssoAuthResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(id, "资源id为空");
            }

            @Override
            public void service() {
                ArrayList<SsoAuthDO> list = new ArrayList<SsoAuthDO>();

                // 查询
                SsoAuthDO ssoAuthDO = ssoAuthDAO.query(Integer.parseInt(id));
                list.add(ssoAuthDO);

                // 设置结果
                result.setResultList(SsoAuthConvertor.convertDos2Vos(list));
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAuthFacade#fuzzyQueryForPage(java.lang.String, java.lang.String, int, int, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES, authCode = AuthCodeEnum.BDSSO_COMMON_USER_A)
    public BdssoForPageResult<SsoAuthAddParamVO> fuzzyQueryForPage(String uid, String token, int pageSize, int pageNum, String key) {
        final BdssoForPageResult<SsoAuthAddParamVO> result = new BdssoForPageResult<SsoAuthAddParamVO>();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertPositiveIntegerNumber(pageSize, "每页条数应大于0且为整数");
                AssertUtils.assertIntegerNumberGeZero(pageNum, "页码应大于等于0");
            }

            @Override
            public void service() {

                PageList pageList = extraSsoAuthDAO.fuzzyQueryForPage(key, pageSize, pageNum);

                @SuppressWarnings("unchecked")
                ArrayList<ExSsoAuthDO> listCopy = (ArrayList<ExSsoAuthDO>) pageList.clone();

                ArrayList<SsoAuthAddParamVO> resultList = SsoAuthConvertor.convertDos2AddParamVos(listCopy);

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
     * @see com.bbd.bdsso.common.service.facade.SsoAuthFacade#queryByAuthCode(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult queryByAuthCode(String uid, String token, String authCode) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(authCode, "权限码为空");
            }

            @Override
            public void service() {

                if (null != ssoAuthDAO.queryByAuthCode(authCode)) {
                    throw new BdssoBaseException(BdssoResultEnum.AUTH_CODE_ALREADY_EXIST);
                }
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAuthFacade#queryByAuthName(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult queryByAuthName(String uid, String token, String authName) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(authName, "权限名为空");
            }

            @Override
            public void service() {

                if (null != ssoAuthDAO.queryByAuthName(authName)) {
                    throw new BdssoBaseException(BdssoResultEnum.AUTH_NAME_ALREADY_EXIST);
                }
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAuthFacade# auth(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES, authCode = AuthCodeEnum.BDSSO_COMMON_USER_A)
    public BdssoBaseAuthResult authByParams(String ticket, String authCode, String appName) {
        final BdssoBaseAuthResult result = new BdssoBaseAuthResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 非空校验
                AssertUtils.assertStringNotBlank(ticket, "用户凭据为空，请检查请求头中的ticket");
                AssertUtils.assertStringNotBlank(authCode, "权限码为空");
                AssertUtils.assertStringNotBlank(appName, "应用名为空");
            }

            @Override
            public void service() {

                // 解密ticket
                HashMap<String, String> sHashMap = ssoEncryptService.decrypt(ticket);

                if (!sHashMap.containsKey(SsoConstant.UID) || StringUtils.isBlank(sHashMap.get(SsoConstant.UID))) {
                    throw new BdssoBaseException(BdssoResultEnum.INVAID_TICKET);
                }

                ArrayList<String> authCodeList = new ArrayList<String>();
                authCodeList.add(authCode);

                HashMap<String, Boolean> authResult = MapUtils.constr(ssoAccessService.getResourceCodeList(sHashMap.get(SsoConstant.UID), appName), authCodeList);

                // 设置结果
                result.setAuthResult(authResult);
                result.setUid(sHashMap.get(SsoConstant.UID));
                result.setUserName(ssoUserDAO.query(Integer.parseInt(sHashMap.get(SsoConstant.UID))).getUserName());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAuthFacade#authByParams(java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseAuthResult authByParams(String ticket, String appName) {
        final BdssoBaseAuthResult result = new BdssoBaseAuthResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 非空校验
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

                // 校验authCodeList
                HashMap<String, Boolean> authResult = ssoAccessService.getResourceCodeList(sHashMap.get(SsoConstant.UID), appName);

                // 设置结果
                result.setAuthResult(authResult);
                result.setUid(sHashMap.get(SsoConstant.UID));
                result.setUserName(ssoUserDAO.query(Integer.parseInt(sHashMap.get(SsoConstant.UID))).getUserName());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAuthFacade#authByParams(java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoExAuthVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES, authCode = AuthCodeEnum.BDSSO_COMMON_USER_A)
    public BdssoBaseAuthResult authByParams(String ticket, SsoExAuthVO ssoExAuthVO) {
        final BdssoBaseAuthResult result = new BdssoBaseAuthResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 非空校验
                AssertUtils.assertStringNotBlank(ticket, "用户凭据为空，请检查请求头中的ticket");
                AssertUtils.assertNotNull(ssoExAuthVO, "实体为空");
            }

            @Override
            public void service() {

                // 解密ticket
                HashMap<String, String> sHashMap = ssoEncryptService.decrypt(ticket);

                if (!sHashMap.containsKey(SsoConstant.UID) || StringUtils.isBlank(sHashMap.get(SsoConstant.UID))) {
                    throw new BdssoBaseException(BdssoResultEnum.INVAID_TICKET);
                }

                HashMap<String, Boolean> authResult = MapUtils.constr(ssoAccessService.getResourceCodeList(sHashMap.get(SsoConstant.UID), ssoExAuthVO.getAppName()), ssoExAuthVO.getAuthCodeList());

                // 设置结果
                result.setAuthResult(authResult);
                result.setUid(sHashMap.get(SsoConstant.UID));
                result.setUserName(ssoUserDAO.query(Integer.parseInt(sHashMap.get(SsoConstant.UID))).getUserName());
            }

        }, result);

        return result;
    }

}
