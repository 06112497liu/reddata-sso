/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.service.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbd.bdsso.common.dal.daointerface.SsoCacheManagerDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoCacheManagerDO;
import com.bbd.bdsso.common.util.SystemUtil;
import com.bbd.bdsso.common.util.enums.LocalCacheStatusEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;

/**
 * 缓存刷新通用逻辑
 * 
 * @author byron
 * @version $Id: AbstractSsoLocalCache.java, v 0.1 Nov 20, 2017 3:10:17 PM byron Exp $
 */
public abstract class AbstractSsoLocalCache implements SsoLocalCache {

    /** 日志 */
    private final Logger       logger = LoggerFactory.getLogger(AbstractSsoLocalCache.class);

    /** SSO本地缓存管理器DAO */
    @Autowired
    private SsoCacheManagerDAO ssoCacheManagerDAO;

    /** 
     * @see com.bbd.bdsso.core.service.cache.SsoLocalCache#init()
     */
    @Override
    public void init() {
        synchronized (this) {
            logger.info("缓存[{}|{}]开始初始化", getCacheType().getCode(), Thread.currentThread().getId());

            // 获取缓存实例
            SsoCacheManagerDO ssoCacheManagerDO = ssoCacheManagerDAO.queryByCacheName(SystemUtil.getHostInfo().getName(), SystemUtil.getHostInfo().getAddress(), getCacheType().getCode());

            if (null != ssoCacheManagerDO) {
                // 初始化
                ssoCacheManagerDAO.updateById(LocalCacheStatusEnum.INITIAL.getCode(), ssoCacheManagerDO.getId());
            } else {

                // 插入任务
                SsoCacheManagerDO initDO = new SsoCacheManagerDO();
                initDO.setCacheName(getCacheType().getCode());
                initDO.setDescription(LocalCacheStatusEnum.INITIAL.getDesc());
                initDO.setServerHost(SystemUtil.getHostInfo().getName());
                initDO.setServerIp(SystemUtil.getHostInfo().getAddress());
                initDO.setStatus(LocalCacheStatusEnum.INITIAL.getCode());
                ssoCacheManagerDAO.insert(initDO);
            }

            // 刷新缓存
            forceRefresh();

            logger.info("缓存[{}|{}]初始化完成", getCacheType().getCode(), Thread.currentThread().getId());
        }
    }

    /** 
     * @see com.bbd.bdsso.core.service.cache.SsoLocalCache#forceRefresh()
     */
    @Override
    public void forceRefresh() {
        synchronized (this) {
            logger.info("缓存[{}|{}]开始刷新", getCacheType().getCode(), Thread.currentThread().getId());

            try {
                forcedRefreshContent();
            } catch (Exception e) {
                logger.error("缓存[{}|{}]刷新异常", getCacheType().getCode(), Thread.currentThread().getId());
                throw new BdssoBaseException("[bdsso]缓存刷新失败");
            }
            logger.info("缓存[{}|{}]完成", getCacheType().getCode(), Thread.currentThread().getId());
        }
    }

    /**
     * 强制刷新本地缓存
     */
    protected abstract void forcedRefreshContent();

}
