/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.biz.service.impl.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;
import com.bbd.bdsso.common.util.enums.BdssoResultEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;
import com.bbd.commons.lang.enums.BbdBaseEnum;
import com.bbd.commons.lang.exception.BbdBizException;

/**
 * 单点登陆事务模板
 * 
 * @author byron
 * @version $Id: BdssoTemplate.java, v 0.1 Sep 12, 2017 4:59:09 PM byron Exp $
 */
public class BdssoTemplate {

    /** 日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(BdssoTemplate.class);

    /** 事务模板 */
    @Autowired
    private TransactionTemplate bdssoTransactionTemplate;

    /**
     * 不带事务的模板
     * 
     * @param callback
     * @param result
     */
    public void executeWithoutTransaction(BdssoCallBack callback, BdssoBaseResult result) {
        doExecute(callback, result, null);
    }

    /**
     * 带事务的模板
     * 
     * @param callback
     * @param result
     */
    public void executeWithTransaction(BdssoCallBack callback, BdssoBaseResult result) {
        doExecute(callback, result, bdssoTransactionTemplate);
    }

    /**
     * 具体实现方法
     * 
     * @param callback
     * @param result
     * @param transactionTemplate
     */
    private void doExecute(final BdssoCallBack callback, final Object result, final TransactionTemplate transactionTemplate) {

        try {

            // 服务预检查
            callback.check();

            // 服务处理
            if (transactionTemplate == null) {
                callback.service();
            } else {
                transactionTemplate.execute(new TransactionCallbackWithoutResult() {

                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus status) {
                        callback.service();
                    }
                });
            }

            // 构建成功结果
            buildResult(result, true, BdssoResultEnum.SUCCESS);

        } catch (BbdBizException e) {
            BbdBaseEnum errorCode = e.getExceptionEnum();
            LOGGER.warn("单点登陆出现业务异常:" + errorCode.getCode(), e);
            buildAssertResult(result, false, errorCode, e);
        } catch (BdssoBaseException e) {
            BdssoResultEnum errorCode = e.getErrorCode();
            LOGGER.warn("单点登陆出现业务异常:" + errorCode.getCode(), e);
            buildResult(result, false, errorCode);
        } catch (DataAccessException e) {
            LOGGER.error("单点登陆出现数据库异常:" + e.getMessage(), e);
            buildResult(result, false, BdssoResultEnum.DATABASE_EXCEPTION);
        } catch (Exception e) {
            LOGGER.error("单点登陆出现未知异常:" + e.getMessage(), e);
            buildResult(result, false, BdssoResultEnum.UNKNOWN_EXCEPTION);
        }
    }

    /**
     * 设置结果
     * 
     * @param result
     * @param success
     * @param resultCode
     */
    private void buildAssertResult(Object result, boolean success, BbdBaseEnum resultCode, BbdBizException e) {
        BdssoBaseResult baseQueryResult = (BdssoBaseResult) result;
        baseQueryResult.setSuccess(success);
        baseQueryResult.setResultCode(resultCode.getCode());
        baseQueryResult.setResultDesc(e.getMessage());
    }

    /**
     * 设置结果
     * 
     * @param result
     * @param success
     * @param resultCode
     */
    private void buildResult(Object result, boolean success, BdssoResultEnum resultCode) {
        BdssoBaseResult baseQueryResult = (BdssoBaseResult) result;
        baseQueryResult.setSuccess(success);
        baseQueryResult.setResultCode(resultCode.getCode());
        baseQueryResult.setResultDesc(resultCode.getDesc());
    }

}
