/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.biz.service.impl;

import com.bbd.bdsso.biz.service.impl.aop.AuthValidate;
import com.bbd.bdsso.biz.service.impl.template.BdssoCallBack;
import com.bbd.bdsso.biz.service.impl.template.BdssoTemplate;
import com.bbd.bdsso.common.dal.dataobject.SsoDataDictionaryDO;
import com.bbd.bdsso.common.service.facade.SsoDataDictionaryFacade;
import com.bbd.bdsso.common.service.facade.result.*;
import com.bbd.bdsso.common.util.enums.AuthTypeEnum;
import com.bbd.bdsso.core.model.convertor.SsoDataDictionaryConvertor;
import com.bbd.bdsso.core.service.SsoDataDictionaryService;
import com.bbd.commons.lang.util.AssertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 用户访问服务接口服务实现
 * 
 * @author byron
 * @version $Id: SsoAccessFacadeImpl.java, v 0.1 Sep 26, 2017 4:10:41 PM byron Exp $
 */
public class SsoDataDictionaryFacadeImpl implements SsoDataDictionaryFacade {

    /** 日志 */
    private final Logger      logger = LoggerFactory.getLogger(SsoDataDictionaryFacadeImpl.class);

    @Autowired
    SsoDataDictionaryService ssoDataDictionaryService;
    /** 事务模板 */
    @Autowired
    private BdssoTemplate     bdssoTransactionTemplate;

    /**
     * @see SsoDataDictionaryFacade#query(String, String, String)
     */
    @Override
    @AuthValidate(type = AuthTypeEnum.NO)
    public BdssoDataDictionaryResult query(String uid,String token,String type) {
        final BdssoDataDictionaryResult result = new BdssoDataDictionaryResult();
        bdssoTransactionTemplate.executeWithTransaction(new BdssoCallBack() {
            @Override
            public void check() {
                // 入参检查
                AssertUtils.assertNotNull(type, "数据类型为空");
            }

            @Override
            public void service() {
                List<SsoDataDictionaryDO> list = ssoDataDictionaryService.getDataByType(type);
                result.setResultList(SsoDataDictionaryConvertor.convertDos2Vos(list));
            }

        }, result);
        return result;
    }
}
