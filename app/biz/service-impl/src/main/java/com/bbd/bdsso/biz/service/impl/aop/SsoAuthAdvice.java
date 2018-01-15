/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.biz.service.impl.aop;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbd.bdsso.biz.service.impl.template.BdssoAopTemplate;
import com.bbd.bdsso.biz.service.impl.template.BdssoCallBack;
import com.bbd.bdsso.common.service.facade.result.BdssoBaseResult;
import com.bbd.bdsso.common.util.SsoConstant;
import com.bbd.bdsso.common.util.enums.AuthCodeEnum;
import com.bbd.bdsso.common.util.enums.AuthTypeEnum;
import com.bbd.bdsso.common.util.enums.BdssoResultEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;
import com.bbd.bdsso.core.service.SsoAccessService;
import com.bbd.bdsso.core.service.SsoEncryptService;
import com.bbd.commons.lang.util.AssertUtils;

/**
 * SSO权限AOP服务
 * 
 * @author byron
 * @version $Id: SsoAuthAdvice.java, v 0.1 Sep 20, 2017 4:01:32 PM byron Exp $
 */
public class SsoAuthAdvice {

    /** AOP模板 */
    @Autowired
    private BdssoAopTemplate  bdssoAopTemplate;

    /** 访问Token验证服务 */
    @Autowired
    private SsoAccessService  ssoAccessService;

    /** SSO加解密服务 */
    @Autowired
    private SsoEncryptService ssoEncryptService;

    @SuppressWarnings("unused")
    private Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // 获取注解枚举值
        Method method = signature.getMethod();
        AuthTypeEnum authTypeEnum = method.getAnnotation(AuthValidate.class).type();
        AuthCodeEnum authCodeEnum = method.getAnnotation(AuthValidate.class).authCode();
        String appName = method.getAnnotation(AuthValidate.class).appName();

        // 检查是否需要校验权限
        if (StringUtils.equals(authTypeEnum.getCode(), AuthTypeEnum.NO.getCode())) {
            // 不需要校验
            return joinPoint.proceed();
        }

        // 获取方法的参数名和参数值
        String[] parameterNames = signature.getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();

        // 构造参数集合
        HashMap<String, Object> parms = constrPairs(parameterNames, parameterValues);

        Class<?> c = signature.getReturnType();

        // 通过统一模板来封装返回值
        if (BdssoBaseResult.class.isAssignableFrom(c)) {

            final BdssoBaseResult result = (BdssoBaseResult) c.newInstance();
            bdssoAopTemplate.executeWithTransaction(new BdssoCallBack() {

                @Override
                public void check() {

                    // 假设包含了ticket字段，首先解密，然后将uid和token设置到parms中
                    if (parms.containsKey(SsoConstant.TICKET)) {

                        AssertUtils.assertNotNull(parms.get(SsoConstant.TICKET), SsoConstant.INVALID_TICKET);

                        HashMap<String, String> sHashMap = ssoEncryptService.decrypt(parms.get(SsoConstant.TICKET).toString());
                        if (!sHashMap.containsKey(SsoConstant.UID) || !sHashMap.containsKey(SsoConstant.TOKEN)) {
                            throw new BdssoBaseException(BdssoResultEnum.INVAID_TICKET);
                        } else {
                            parms.put(SsoConstant.UID, sHashMap.get(SsoConstant.UID));
                            parms.put(SsoConstant.TOKEN, sHashMap.get(SsoConstant.TOKEN));
                        }
                    }

                    // 确保header中包含了uid和token这两个字段
                    AssertUtils.assertNotNull(parms.get(SsoConstant.UID), SsoConstant.TICKET);
                    AssertUtils.assertNotNull(parms.get(SsoConstant.TOKEN), SsoConstant.TICKET);
                }

                @Override
                public void service() {

                    // 校验用户是否登陆
                    if (!ssoAccessService.checkValid(parms.get(SsoConstant.UID).toString(), parms.get(SsoConstant.TOKEN).toString())) {
                        throw new BdssoBaseException(BdssoResultEnum.INVAID_TICKET);
                    }

                    HashMap<String, Boolean> result = ssoAccessService.getAuthCodeList(parms.get(SsoConstant.UID).toString(), appName);

                    // 校验用户是否有权
                    if (!result.containsKey(authCodeEnum.getCode())) {
                        throw new BdssoBaseException(BdssoResultEnum.AUTH_CODE_ERROR);
                    }
                }

            }, result, joinPoint);

            // 如果校验成功，继续后续处理；反之，返回结果
            return result.getResultCode().equals(BdssoResultEnum.SUCCESS.getCode()) ? joinPoint.proceed() : result;
        }

        return joinPoint.proceed();
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
