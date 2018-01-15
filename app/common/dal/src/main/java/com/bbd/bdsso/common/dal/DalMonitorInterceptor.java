/**  
 * BBD Service Inc
 * All Rights Reserved @ 2016
 */
package com.bbd.bdsso.common.dal;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DAO层日志摘要拦截器
 * 
 * @author byron
 * @version $Id: DalMonitorInterceptor.java, v 0.1 Jul 15, 2016 11:09:54 AM byron Exp $
 */
public class DalMonitorInterceptor implements MethodInterceptor {

    /** 日志 */
    private final Logger logger = LoggerFactory.getLogger(DalMonitorInterceptor.class);

    /** 
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        // 包名、类名、DB名、方法名
        String className = invocation.getMethod().getDeclaringClass().getSimpleName();

        // 方法名
        String method = className + "." + invocation.getMethod().getName();

        // 开始时间
        long startTime = System.currentTimeMillis();

        // 是否有错误
        boolean hasError = false;

        try {
            return invocation.proceed();
        } catch (Throwable e) {

            // 标记异常
            hasError = true;
            throw e;

        } finally {
            if (logger.isInfoEnabled()) {
                long elapseTime = System.currentTimeMillis() - startTime;

                if (hasError) {
                    logger.info("bdsso," + method + ",N," + elapseTime + "ms");
                } else {
                    logger.info("bdsso," + method + ",Y," + elapseTime + "ms");
                }
            }
        }
    }
}
