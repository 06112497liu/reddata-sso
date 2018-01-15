<<<<<<< HEAD
/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.biz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbd.bdsso.biz.service.impl.aop.AuthValidate;
import com.bbd.bdsso.biz.service.impl.template.BdssoCallBack;
import com.bbd.bdsso.biz.service.impl.template.BdssoTemplate;
import com.bbd.bdsso.common.dal.daointerface.SsoAppDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoAppDO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoAppDAO;
import com.bbd.bdsso.common.service.facade.SsoAppFacade;
import com.bbd.bdsso.common.service.facade.result.BdssoAppResult;
import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;
import com.bbd.bdsso.common.service.facade.result.BdssoForPageResult;
import com.bbd.bdsso.common.service.facade.result.BdssoTicketResult;
import com.bbd.bdsso.common.service.facade.result.BdssoUploadResult;
import com.bbd.bdsso.common.service.facade.result.BdssoUserResult;
import com.bbd.bdsso.common.service.facade.vo.SsoAppVO;
import com.bbd.bdsso.common.service.facade.vo.SsoFileVO;
import com.bbd.bdsso.common.util.AESUtils;
import com.bbd.bdsso.common.util.FileUtils;
import com.bbd.bdsso.common.util.SsoConstant;
import com.bbd.bdsso.common.util.SsoStringUtils;
import com.bbd.bdsso.common.util.enums.AuthCodeEnum;
import com.bbd.bdsso.common.util.enums.AuthTypeEnum;
import com.bbd.bdsso.common.util.enums.BdssoResultEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;
import com.bbd.bdsso.core.model.convertor.SsoAppConvertor;
import com.bbd.bdsso.core.service.SsoAccessService;
import com.bbd.bdsso.core.service.SsoEncryptService;
import com.bbd.bdsso.core.service.SsoFileService;
import com.bbd.commons.lang.util.AssertUtils;
import com.bbd.commons.lang.util.page.PageList;

/**
 * SSO应用管理服务实现
 * 
 * @author byron
 * @version $Id: SsoAppFacadeImpl.java, v 0.1 Sep 28, 2017 4:22:14 PM byron Exp $
 */
public class SsoAppFacadeImpl implements SsoAppFacade {

    /** 日志 */
    private final Logger      logger = LoggerFactory.getLogger(SsoAppFacadeImpl.class);

    /** 应用管理DAO */
    @Autowired
    private SsoAppDAO         ssoAppDAO;

    /** 手工应用管理DAO */
    @Autowired
    private ExtraSsoAppDAO    extraSsoAppDAO;

    /** 事务模板 */
    @Autowired
    private BdssoTemplate     bdssoTransactionTemplate;

    /** 文件服务 */
    @Autowired
    private SsoFileService    ssoFileService;

    /** 加解密服务 */
    @Autowired
    private SsoEncryptService ssoEncryptService;

    /** SSO访问Token验证服务 */
    @Autowired
    private SsoAccessService  ssoAccessService;

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#add(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoAppVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult add(String uid, String token, SsoAppVO ssoAppVO) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ssoAppVO, "实体为空");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoAppVO.getAppName()) <= SsoConstant.MAX_NAME_LENGH, "应用名最长为32个字符");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoAppVO.getDescription()) <= SsoConstant.MAX_DESC_LENGH, "描述最长为64个字符");
            }

            @Override
            public void service() {
                ssoAppDAO.insert(SsoAppConvertor.convertVo2Do(ssoAppVO));
                logger.info("新增应用，[ssoAppVO={}]", ssoAppVO.toString());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#delete(java.lang.String, java.lang.String, java.lang.String)
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
                SsoAppDO ssoAppDO = ssoAppDAO.query(Integer.parseInt(id));
                if (null != ssoAppDO && (StringUtils.equals(ssoAppDO.getAppName(), SsoConstant.BDSSO))) {
                    throw new BdssoBaseException(BdssoResultEnum.NOT_ALLOWED);
                }

                ssoAppDAO.delete(Integer.parseInt(id));
                logger.info("删除应用，[应用id={}]", id);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#update(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoAppVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult update(String uid, String token, SsoAppVO ssoAppVO) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ssoAppVO, "实体为空");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoAppVO.getAppName()) <= SsoConstant.MAX_NAME_LENGH, "应用名最长为32个字符");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoAppVO.getDescription()) <= SsoConstant.MAX_DESC_LENGH, "描述最长为64个字符");
            }

            @Override
            public void service() {
                ssoAppDAO.update(SsoAppConvertor.convertVo2Do(ssoAppVO));
                logger.info("更新应用，[ssoAppVO={}]", ssoAppVO.toString());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#queryById(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoAppResult queryById(String uid, String token, String id) {
        final BdssoAppResult result = new BdssoAppResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(id, "资源id为空");
            }

            @Override
            public void service() {
                ArrayList<SsoAppDO> list = new ArrayList<SsoAppDO>();

                // 查询
                SsoAppDO ssoAppDO = ssoAppDAO.query(Integer.parseInt(id));
                list.add(ssoAppDO);

                // 设置结果
                result.setResultList(SsoAppConvertor.convertDos2Vos(list));
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#fuzzyQueryForPage(java.lang.String, java.lang.String, int, int, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES, authCode = AuthCodeEnum.BDSSO_COMMON_USER_A)
    public BdssoForPageResult<SsoAppVO> fuzzyQueryForPage(String uid, String token, int pageSize, int pageNum, String key) {
        final BdssoForPageResult<SsoAppVO> result = new BdssoForPageResult<SsoAppVO>();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertPositiveIntegerNumber(pageSize, "每页条数应大于0且为整数");
                AssertUtils.assertIntegerNumberGeZero(pageNum, "页码应大于等于0");
            }

            @Override
            public void service() {

                PageList pageList = extraSsoAppDAO.fuzzyQueryForPage(key, pageSize, pageNum);

                @SuppressWarnings("unchecked")
                ArrayList<SsoAppDO> listCopy = (ArrayList<SsoAppDO>) pageList.clone();

                // 设置结果
                result.setResultList(SsoAppConvertor.convertDos2Vos(listCopy));
                result.setPageNum(pageList.getPaginator().getPage());
                result.setPageSize(pageList.getPaginator().getItemsPerPage());
                result.setTotal(pageList.getPaginator().getItems());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#queryByName(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult queryByName(String uid, String token, String appName) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(appName, "应用名为空");
            }

            @Override
            public void service() {

                if (null != ssoAppDAO.queryByName(appName)) {
                    throw new BdssoBaseException(BdssoResultEnum.APP_NAME_ALREADY_EXIST);
                }
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#uploadFile(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoFileVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoUploadResult uploadFile(String uid, String token, SsoFileVO file) {
        final BdssoUploadResult result = new BdssoUploadResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(file, "文件为空");
            }

            @Override
            public void service() {

                // 文件名转换，替换成日期+后缀
                String newFileName = FileUtils.getNewFileName(file.getFileName());

                // 文件上传
                String filePath = ssoFileService.upload(newFileName, file.getFileData());
                logger.info("开始上传文件，[fileName={}]", newFileName);
                AssertUtils.assertStringNotBlank(filePath, "文件上传失败");
                result.setFilePath(filePath);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#verifyTicket(java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.NO, authCode = AuthCodeEnum.BDSSO_COMMON_USER_A)
    public BdssoBaseResult verifyTicket(String ticket) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ticket, "ticket为空");
            }

            @Override
            public void service() {

                HashMap<String, String> ticketAuth = ssoEncryptService.decrypt(ticket);

                if (!ssoAccessService.checkValid(ticketAuth.get(SsoConstant.UID), ticketAuth.get(SsoConstant.TOKEN))) {
                    throw new BdssoBaseException(BdssoResultEnum.INVAID_TICKET);
                }
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#queryByNameWithoutAuth(java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.NO)
    public BdssoAppResult queryByNameWithoutAuth(String appName) {
        final BdssoAppResult result = new BdssoAppResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(appName, "应用名为空");
            }

            @Override
            public void service() {

                SsoAppDO ssoAppDO = ssoAppDAO.queryByName(appName);

                if (null == ssoAppDO) {
                    throw new BdssoBaseException(BdssoResultEnum.APP_NOT_EXIST);
                }

                List<SsoAppVO> resultList = new ArrayList<SsoAppVO>();
                resultList.add(SsoAppConvertor.convertDo2Vo(ssoAppDO));
                result.setResultList(resultList);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#buildTicket(java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.NO)
    public BdssoTicketResult buildTicket(String uid, String token) {
        final BdssoTicketResult result = new BdssoTicketResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(uid, "uid为空");
                AssertUtils.assertStringNotBlank(token, "token为空");
            }

            @Override
            public void service() {
                result.setTicket(ssoEncryptService.encrypt(SsoStringUtils.assemblyStr(uid, token), AESUtils.SECRETE));
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#parseTicket(java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.NO)
    public BdssoUserResult parseTicket(String ticket) {
        final BdssoUserResult result = new BdssoUserResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                AssertUtils.assertNotNull(ticket, "ticket为空");
            }

            @Override
            public void service() {
                HashMap<String, String> ticketAuth = ssoEncryptService.decrypt(ticket);
                result.setUid(ticketAuth.get(SsoConstant.UID));
                result.setToken(ticketAuth.get(SsoConstant.TOKEN));
            }

        }, result);

        return result;
    }
}
=======
/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.biz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbd.bdsso.biz.service.impl.aop.AuthValidate;
import com.bbd.bdsso.biz.service.impl.template.BdssoCallBack;
import com.bbd.bdsso.biz.service.impl.template.BdssoTemplate;
import com.bbd.bdsso.common.dal.daointerface.SsoAppDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoAppDO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoAppDAO;
import com.bbd.bdsso.common.service.facade.SsoAppFacade;
import com.bbd.bdsso.common.service.facade.result.BdssoAppResult;
import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;
import com.bbd.bdsso.common.service.facade.result.BdssoForPageResult;
import com.bbd.bdsso.common.service.facade.result.BdssoTicketResult;
import com.bbd.bdsso.common.service.facade.result.BdssoUploadResult;
import com.bbd.bdsso.common.service.facade.result.BdssoUserResult;
import com.bbd.bdsso.common.service.facade.vo.SsoAppVO;
import com.bbd.bdsso.common.service.facade.vo.SsoFileVO;
import com.bbd.bdsso.common.util.AESUtils;
import com.bbd.bdsso.common.util.FileUtils;
import com.bbd.bdsso.common.util.SsoConstant;
import com.bbd.bdsso.common.util.SsoStringUtils;
import com.bbd.bdsso.common.util.enums.AuthCodeEnum;
import com.bbd.bdsso.common.util.enums.AuthTypeEnum;
import com.bbd.bdsso.common.util.enums.BdssoResultEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;
import com.bbd.bdsso.core.model.convertor.SsoAppConvertor;
import com.bbd.bdsso.core.service.SsoAccessService;
import com.bbd.bdsso.core.service.SsoEncryptService;
import com.bbd.bdsso.core.service.SsoFileService;
import com.bbd.commons.lang.util.AssertUtils;
import com.bbd.commons.lang.util.page.PageList;

/**
 * SSO应用管理服务实现
 * 
 * @author byron
 * @version $Id: SsoAppFacadeImpl.java, v 0.1 Sep 28, 2017 4:22:14 PM byron Exp $
 */
public class SsoAppFacadeImpl implements SsoAppFacade {

    /** 日志 */
    private final Logger      logger = LoggerFactory.getLogger(SsoAppFacadeImpl.class);

    /** 应用管理DAO */
    @Autowired
    private SsoAppDAO         ssoAppDAO;

    /** 手工应用管理DAO */
    @Autowired
    private ExtraSsoAppDAO    extraSsoAppDAO;

    /** 事务模板 */
    @Autowired
    private BdssoTemplate     bdssoTransactionTemplate;

    /** 文件服务 */
    @Autowired
    private SsoFileService    ssoFileService;

    /** 加解密服务 */
    @Autowired
    private SsoEncryptService ssoEncryptService;

    /** SSO访问Token验证服务 */
    @Autowired
    private SsoAccessService  ssoAccessService;

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#add(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoAppVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult add(String uid, String token, SsoAppVO ssoAppVO) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ssoAppVO, "实体为空");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoAppVO.getAppName()) <= SsoConstant.MAX_NAME_LENGH, "应用名最长为32个字符");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoAppVO.getDescription()) <= SsoConstant.MAX_DESC_LENGH, "描述最长为64个字符");
            }

            @Override
            public void service() {
                ssoAppDAO.insert(SsoAppConvertor.convertVo2Do(ssoAppVO));
                logger.info("新增应用，[ssoAppVO={}]", ssoAppVO.toString());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#delete(java.lang.String, java.lang.String, java.lang.String)
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
                SsoAppDO ssoAppDO = ssoAppDAO.query(Integer.parseInt(id));
                if (null != ssoAppDO && (StringUtils.equals(ssoAppDO.getAppName(), SsoConstant.BDSSO))) {
                    throw new BdssoBaseException(BdssoResultEnum.NOT_ALLOWED);
                }

                ssoAppDAO.delete(Integer.parseInt(id));
                logger.info("删除应用，[应用id={}]", id);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#update(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoAppVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult update(String uid, String token, SsoAppVO ssoAppVO) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ssoAppVO, "实体为空");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoAppVO.getAppName()) <= SsoConstant.MAX_NAME_LENGH, "应用名最长为32个字符");
                AssertUtils.assertTrue(SsoStringUtils.length(ssoAppVO.getDescription()) <= SsoConstant.MAX_DESC_LENGH, "描述最长为64个字符");
            }

            @Override
            public void service() {
                ssoAppDAO.update(SsoAppConvertor.convertVo2Do(ssoAppVO));
                logger.info("更新应用，[ssoAppVO={}]", ssoAppVO.toString());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#queryById(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoAppResult queryById(String uid, String token, String id) {
        final BdssoAppResult result = new BdssoAppResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(id, "资源id为空");
            }

            @Override
            public void service() {
                ArrayList<SsoAppDO> list = new ArrayList<SsoAppDO>();

                // 查询
                SsoAppDO ssoAppDO = ssoAppDAO.query(Integer.parseInt(id));
                list.add(ssoAppDO);

                // 设置结果
                result.setResultList(SsoAppConvertor.convertDos2Vos(list));
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#fuzzyQueryForPage(java.lang.String, java.lang.String, int, int, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES, authCode = AuthCodeEnum.BDSSO_COMMON_USER_A)
    public BdssoForPageResult<SsoAppVO> fuzzyQueryForPage(String uid, String token, int pageSize, int pageNum, String key) {
        final BdssoForPageResult<SsoAppVO> result = new BdssoForPageResult<SsoAppVO>();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertPositiveIntegerNumber(pageSize, "每页条数应大于0且为整数");
                AssertUtils.assertIntegerNumberGeZero(pageNum, "页码应大于等于0");
            }

            @Override
            public void service() {

                PageList pageList = extraSsoAppDAO.fuzzyQueryForPage(key, pageSize, pageNum);

                @SuppressWarnings("unchecked")
                ArrayList<SsoAppDO> listCopy = (ArrayList<SsoAppDO>) pageList.clone();

                // 设置结果
                result.setResultList(SsoAppConvertor.convertDos2Vos(listCopy));
                result.setPageNum(pageList.getPaginator().getPage());
                result.setPageSize(pageList.getPaginator().getItemsPerPage());
                result.setTotal(pageList.getPaginator().getItems());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#queryByName(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoBaseResult queryByName(String uid, String token, String appName) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(appName, "应用名为空");
            }

            @Override
            public void service() {

                if (null != ssoAppDAO.queryByName(appName)) {
                    throw new BdssoBaseException(BdssoResultEnum.APP_NAME_ALREADY_EXIST);
                }
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#uploadFile(java.lang.String, java.lang.String, com.bbd.bdsso.common.service.facade.vo.SsoFileVO)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoUploadResult uploadFile(String uid, String token, SsoFileVO file) {
        final BdssoUploadResult result = new BdssoUploadResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(file, "文件为空");
            }

            @Override
            public void service() {

                // 文件名转换，替换成日期+后缀
                String newFileName = FileUtils.getNewFileName(file.getFileName());

                // 文件上传
                String filePath = ssoFileService.upload(newFileName, file.getFileData());
                logger.info("开始上传文件，[fileName={}]", newFileName);
                AssertUtils.assertStringNotBlank(filePath, "文件上传失败");
                result.setFilePath(filePath);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#verifyTicket(java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.NO, authCode = AuthCodeEnum.BDSSO_COMMON_USER_A)
    public BdssoBaseResult verifyTicket(String ticket) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(ticket, "ticket为空");
            }

            @Override
            public void service() {

                HashMap<String, String> ticketAuth = ssoEncryptService.decrypt(ticket);
                if (!ssoAccessService.checkValid(ticketAuth.get(SsoConstant.UID), ticketAuth.get(SsoConstant.TOKEN))) {
                    throw new BdssoBaseException(BdssoResultEnum.INVAID_TICKET);
                }
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#queryByNameWithoutAuth(java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.NO)
    public BdssoAppResult queryByNameWithoutAuth(String appName) {
        final BdssoAppResult result = new BdssoAppResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(appName, "应用名为空");
            }

            @Override
            public void service() {

                SsoAppDO ssoAppDO = ssoAppDAO.queryByName(appName);

                if (null == ssoAppDO) {
                    throw new BdssoBaseException(BdssoResultEnum.APP_NOT_EXIST);
                }

                List<SsoAppVO> resultList = new ArrayList<SsoAppVO>();
                resultList.add(SsoAppConvertor.convertDo2Vo(ssoAppDO));
                result.setResultList(resultList);
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#buildTicket(java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.NO)
    public BdssoTicketResult buildTicket(String uid, String token) {
        final BdssoTicketResult result = new BdssoTicketResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(uid, "uid为空");
                AssertUtils.assertStringNotBlank(token, "token为空");
            }

            @Override
            public void service() {
                result.setTicket(ssoEncryptService.encrypt(SsoStringUtils.assemblyStr(uid, token), AESUtils.SECRETE));
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAppFacade#parseTicket(java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.NO)
    public BdssoUserResult parseTicket(String ticket) {
        final BdssoUserResult result = new BdssoUserResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                AssertUtils.assertNotNull(ticket, "ticket为空");
            }

            @Override
            public void service() {
                HashMap<String, String> ticketAuth = ssoEncryptService.decrypt(ticket);
                result.setUid(ticketAuth.get(SsoConstant.UID));
                result.setToken(ticketAuth.get(SsoConstant.TOKEN));
            }

        }, result);

        return result;
    }
}
>>>>>>> commit
