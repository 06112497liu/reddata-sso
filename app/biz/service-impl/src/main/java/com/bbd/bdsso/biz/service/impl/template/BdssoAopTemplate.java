/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.biz.service.impl.template;

import java.util.HashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
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
import com.bbd.bdsso.core.service.SsoAccessService;
import com.bbd.bdsso.core.service.SsoEncryptService;
import com.bbd.commons.lang.enums.BbdBaseEnum;
import com.bbd.commons.lang.exception.BbdBizException;

/**
 * 
 * @author byron
 * @version $Id: BdssoAopTemplate.java, v 0.1 Oct 12, 2017 10:15:33 AM byron Exp $
 */
public class BdssoAopTemplate {

    /** 日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(BdssoAopTemplate.class);

    /** 事务模板 */
    @Autowired
    private TransactionTemplate bdssoTransactionTemplate;

    /** 访问Token验证服务 */
    @Autowired
    private SsoAccessService    ssoAccessService;

    /** SSO加解密服务 */
    @Autowired
    private SsoEncryptService   ssoEncryptService;

    /**
     * 不带事务的模板
     * 
     * @param callback
     * @param result
     * @param joinPoint
     * @throws Throwable 
     */
    public void executeWithoutTransaction(BdssoCallBack callback, BdssoBaseResult result, ProceedingJoinPoint joinPoint) throws Throwable {
        doExecute(callback, result, null, joinPoint);
    }

    /**
     * 带事务的模板
     * 
     * @param callback
     * @param result
     * @param joinPoint
     * @throws Throwable 
     */
    public void executeWithTransaction(BdssoCallBack callback, BdssoBaseResult result, ProceedingJoinPoint joinPoint) throws Throwable {
        doExecute(callback, result, bdssoTransactionTemplate, joinPoint);
    }

    /**
     * 具体实现方法
     * 
     * @param callback
     * @param result
     * @param transactionTemplate
     * @param joinPoint
     * @throws Throwable 
     */
    private void doExecute(final BdssoCallBack callback, final Object result, final TransactionTemplate transactionTemplate, ProceedingJoinPoint joinPoint) throws Throwable {

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

                        // 更新token
                        updateToken(joinPoint);
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

    /**
     * 更新token
     * 
     * @param joinPoint
     */
    private void updateToken(ProceedingJoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // 获取方法的参数名和参数值
        String[] parameterNames = signature.getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();

        // 构造参数集合
        HashMap<String, Object> parms = constrPairs(parameterNames, parameterValues);

        // 更新uid对应的token
        String uid = null;
        if (parms.containsKey("ticket")) {
            uid = ssoEncryptService.decrypt(parms.get("ticket").toString()).get("uid");
            parms.put("uid", uid);
        }
        ssoAccessService.updateToken(parms.get("uid").toString());
    }

    /**
     * 参数构造器
     * 
     * @param parameterNames    参数名
     * @param parameterValues   参数值
     * @return
     */
    private HashMap<String, Object> constrPairs(String[] parameterNames, Object[] parameterValues) {
        HashMap<String, Object> parms = new HashMap<String, Object>();
        for (int i = 0; i < parameterNames.length; i++) {
            parms.put(parameterNames[i], parameterValues[i]);
        }
        return parms;
    }

}
