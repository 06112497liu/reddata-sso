/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.biz.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.bbd.bdsso.biz.service.impl.aop.AuthValidate;
import com.bbd.bdsso.biz.service.impl.template.BdssoCallBack;
import com.bbd.bdsso.biz.service.impl.template.BdssoTemplate;
import com.bbd.bdsso.common.dal.daointerface.SsoAccountValidDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoAccountValidDO;
import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoAccountValidDAO;
import com.bbd.bdsso.common.service.facade.SsoAccountValidFacade;
import com.bbd.bdsso.common.service.facade.result.BdssoAccountValidResult;
import com.bbd.bdsso.common.service.facade.result.BdssoForPageResult;
import com.bbd.bdsso.common.service.facade.vo.SsoAccountValidVO;
import com.bbd.bdsso.common.util.enums.AuthCodeEnum;
import com.bbd.bdsso.common.util.enums.AuthTypeEnum;
import com.bbd.bdsso.core.model.convertor.SsoAccountValidConvertor;
import com.bbd.commons.lang.util.AssertUtils;
import com.bbd.commons.lang.util.page.PageList;

/**
 * 用户账户验证服务实现
 * 
 * @author byron
 * @version $Id: SsoAccountValidFacadeImpl.java, v 0.1 Sep 28, 2017 2:04:54 PM byron Exp $
 */
public class SsoAccountValidFacadeImpl implements SsoAccountValidFacade {

    /** 用户账户验证DAO */
    @Autowired
    private SsoAccountValidDAO      ssoAccountValidDAO;

    /** 手工用户账户验证DAO */
    @Autowired
    private ExtraSsoAccountValidDAO extraSsoAccountValidDAO;

    /** 事务模板 */
    @Autowired
    private BdssoTemplate           bdssoTransactionTemplate;

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAccountValidFacade#queryById(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES)
    public BdssoAccountValidResult queryById(String uid, String token, String id) {
        final BdssoAccountValidResult result = new BdssoAccountValidResult();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertStringNotBlank(id, "资源id为空");
            }

            @Override
            public void service() {
                ArrayList<SsoAccountValidDO> list = new ArrayList<SsoAccountValidDO>();

                // 查询
                SsoAccountValidDO ssoAccountValidDO = ssoAccountValidDAO.query(Integer.parseInt(id));
                list.add(ssoAccountValidDO);

                // 设置结果
                result.setResultList(SsoAccountValidConvertor.convertDos2Vos(list));
            }

        }, result);

        return result;
    }

    /** 
     * @see com.bbd.bdsso.common.service.facade.SsoAccountValidFacade#fuzzyQueryForPage(java.lang.String, java.lang.String, int, int, java.lang.String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.YES, authCode = AuthCodeEnum.BDSSO_COMMON_USER_A)
    public BdssoForPageResult<SsoAccountValidVO> fuzzyQueryForPage(String uid, String token, int pageSize, int pageNum, String key) {
        final BdssoForPageResult<SsoAccountValidVO> result = new BdssoForPageResult<SsoAccountValidVO>();

        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {

            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertPositiveIntegerNumber(pageSize, "每页条数应大于0且为整数");
                AssertUtils.assertIntegerNumberGeZero(pageNum, "页码应大于等于0");
            }

            @Override
            public void service() {

                PageList pageList = extraSsoAccountValidDAO.fuzzyQueryForPage(key, pageSize, pageNum);

                @SuppressWarnings("unchecked")
                ArrayList<SsoAccountValidDO> listCopy = (ArrayList<SsoAccountValidDO>) pageList.clone();

                // 设置结果
                result.setResultList(SsoAccountValidConvertor.convertDos2Vos(listCopy));
                result.setPageNum(pageList.getPaginator().getPage());
                result.setPageSize(pageList.getPaginator().getItemsPerPage());
                result.setTotal(pageList.getPaginator().getItems());
            }

        }, result);

        return result;
    }
}
