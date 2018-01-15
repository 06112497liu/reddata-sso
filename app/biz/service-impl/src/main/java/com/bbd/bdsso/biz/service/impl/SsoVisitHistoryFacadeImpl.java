/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.biz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbd.bdsso.biz.service.impl.aop.AuthValidate;
import com.bbd.bdsso.biz.service.impl.template.BdssoCallBack;
import com.bbd.bdsso.biz.service.impl.template.BdssoTemplate;
import com.bbd.bdsso.common.dal.daointerface.SsoVisitHistoryDAO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoVisitHistoryDAO;
import com.bbd.bdsso.common.dal.manual.dataobject.ExSsoVisitHistoryDO;
import com.bbd.bdsso.common.service.facade.SsoVisitHistoryFacade;
import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;
import com.bbd.bdsso.common.service.facade.result.BdssoForPageResult;
import com.bbd.bdsso.common.service.facade.vo.SsoVisitHistoryVO;
import com.bbd.bdsso.common.util.enums.AuthCodeEnum;
import com.bbd.bdsso.common.util.enums.AuthTypeEnum;
import com.bbd.bdsso.common.util.enums.BdssoResultEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;
import com.bbd.bdsso.core.model.convertor.SsoVisitHistoryConvertor;
import com.bbd.bdsso.core.service.SsoAccessService;
import com.bbd.bdsso.core.service.SsoEncryptService;
import com.bbd.commons.lang.util.AssertUtils;
import com.bbd.commons.lang.util.page.PageList;

/**
 * SSO访问记录服务实现
 * 
 * @author byron
 * @version $Id: SsoVisitHistoryFacadeImpl.java, v 0.1 Sep 28, 2017 10:03:28 PM byron Exp $
 */
public class SsoVisitHistoryFacadeImpl implements SsoVisitHistoryFacade {

    /** 日志 */
    private final Logger            logger = LoggerFactory.getLogger(SsoAuthFacadeImpl.class);

    /** 访问记录DAO */
    @Autowired
    private SsoVisitHistoryDAO      ssoVisitHistoryDAO;

    /** 手工访问记录DAO */
    @Autowired
    private ExtraSsoVisitHistoryDAO extraSsoVisitHistoryDAO;

    /** 事务模板 */
    @Autowired
    private BdssoTemplate           bdssoTransactionTemplate;

    /** SSO访问Token验证服务 */
    @Autowired
    private SsoAccessService        ssoAccessService;

    /** SSO加解密服务 */
    @Autowired
    private SsoEncryptService       ssoEncryptService;

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoVisitHistoryFacade#fuzzyQueryForPage(java.lang.String, java.lang.String, int, int, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES, authCode = AuthCodeEnum.BDSSO_COMMON_USER_A)
    public BdssoForPageResult<SsoVisitHistoryVO> fuzzyQueryForPage(String uid, String token, int pageSize, int pageNum, String key) {
        final BdssoForPageResult<SsoVisitHistoryVO> result = new BdssoForPageResult<SsoVisitHistoryVO>();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertPositiveIntegerNumber(pageSize, "每页条数应大于0且为整数");
                AssertUtils.assertIntegerNumberGeZero(pageNum, "页码应大于等于0");
            }

            @Override
            public void service() {

                PageList pageList = extraSsoVisitHistoryDAO.fuzzyQueryForPage(key, pageSize, pageNum);

                @SuppressWarnings("unchecked")
                ArrayList<ExSsoVisitHistoryDO> listCopy = (ArrayList<ExSsoVisitHistoryDO>) pageList.clone();

                // 设置结果
                result.setResultList(SsoVisitHistoryConvertor.convertExDos2Vos(listCopy));
                result.setPageNum(pageList.getPaginator().getPage());
                result.setPageSize(pageList.getPaginator().getItemsPerPage());
                result.setTotal(pageList.getPaginator().getItems());
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoVisitHistoryFacade#add(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES, authCode = AuthCodeEnum.BDSSO_COMMON_USER_A)
    public BdssoBaseResult add(String ticket, String ip, String appName) {
        final BdssoBaseResult result = new BdssoBaseResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(ip, "客户ip为空");
                AssertUtils.assertStringNotBlank(appName, "应用名为空");
            }

            @Override
            public void service() {

                HashMap<String, String> ticketAuth = ssoEncryptService.decrypt(ticket);

                String uid = ticketAuth.get("uid");
                String token = ticketAuth.get("token");

                if (!ssoAccessService.checkValid(uid, token)) {
                    throw new BdssoBaseException(BdssoResultEnum.INVAID_TICKET);
                }

                SsoVisitHistoryVO ssoVisitHistoryVO = new SsoVisitHistoryVO();
                ssoVisitHistoryVO.setDescription(appName);
                ssoVisitHistoryVO.setLastLoginDate(new Date());
                ssoVisitHistoryVO.setLastLoginIp(ip);
                ssoVisitHistoryVO.setUserId(uid);

                ssoVisitHistoryDAO.insert(SsoVisitHistoryConvertor.convertVo2Do(ssoVisitHistoryVO));
                logger.info("新增用户访问记录，[ssoVisitHistoryVO=" + ssoVisitHistoryVO.toString() + "]");
            }

        }, result);

        return result;
    }
}
