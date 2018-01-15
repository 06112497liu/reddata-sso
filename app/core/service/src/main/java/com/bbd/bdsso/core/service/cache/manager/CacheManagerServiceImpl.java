/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.service.cache.manager;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbd.bdsso.common.dal.daointerface.SsoCacheManagerDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoCacheManagerDO;
import com.bbd.bdsso.common.util.SystemUtil;
import com.bbd.bdsso.common.util.enums.LocalCacheStatusEnum;
import com.bbd.bdsso.common.util.enums.LocalCacheTypeEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;
import com.bbd.bdsso.core.service.cache.AbstractSsoLocalCache;
import com.bbd.bdsso.core.service.cache.SsoLocalCache;

/**
 * SSO缓存管理服务实现
 * 
 * @author byron
 * @version $Id: CacheManagerServiceImpl.java, v 0.1 Nov 20, 2017 7:23:25 PM byron Exp $
 */
public class CacheManagerServiceImpl implements CacheManagerService {

    /** 日志 */
    private final Logger                                 logger   = LoggerFactory.getLogger(AbstractSsoLocalCache.class);

    /** 缓存实例索引 */
    private final Map<LocalCacheTypeEnum, SsoLocalCache> cacheMap = new HashMap<LocalCacheTypeEnum, SsoLocalCache>();

    /** 缓存信息DAO */
    private SsoCacheManagerDAO                           ssoCacheManagerDAO;

    /** 
     * @see com.bbd.bdsso.core.service.cache.manager.CacheManagerService#refresh()
     */
    @Override
    public void refresh() {
        logger.info("开始尝试刷新本地缓存对象...");
        for (SsoLocalCache eachCache : cacheMap.values()) {

            try {
                // 加载本服务器所有状态为WAIT_FOR_REFRESH的任务
                SsoCacheManagerDO ssoCacheManagerDO = ssoCacheManagerDAO.queryByStatus(LocalCacheStatusEnum.WAIT_FOR_REFRESH.getCode(), SystemUtil.getHostInfo().getName(),
                    SystemUtil.getHostInfo().getAddress());

                if (ssoCacheManagerDO == null) {
                    logger.info("本地缓存对象[{}，{}]无需刷新", eachCache.getCacheType().getCode(), SystemUtil.getHostInfo().getName());
                } else {
                    // 缓存刷新
                    eachCache.forceRefresh();
                    // 更新缓存记录状态
                    ssoCacheManagerDAO.updateById(LocalCacheStatusEnum.REFRESH_COMPLETE.getCode(), ssoCacheManagerDO.getId());
                }
            } catch (Exception e) {
                logger.error("本地缓存对象刷新异常[{}]：", eachCache.getCacheType().getCode(), e);
            }
        }

        logger.info("结束尝试刷新本地缓存对象");
    }

    /** 
     * @see com.bbd.bdsso.core.service.cache.manager.CacheManagerService#notifyRefresh(com.bbd.bdsso.common.util.enums.LocalCacheTypeEnum)
     */
    @Override
    public boolean notifyRefresh(LocalCacheTypeEnum cacheType) {

        logger.warn("刷新本地缓存通知[cacheType={}]", cacheType);

        if (cacheType == null) {
            return false;
        }

        try {
            // 更新本地缓存状态
            ssoCacheManagerDAO.updateStatusByName(LocalCacheStatusEnum.WAIT_FOR_REFRESH.getCode(), cacheType.getCode());
        } catch (Exception e) {
            logger.error("更新本地缓存[{}]状态失败", cacheType.getCode(), e);
            return false;
        }
        return true;
    }

    /** 
     * @see com.bbd.bdsso.core.service.cache.manager.CacheManagerService#queryCache(com.bbd.bdsso.common.util.enums.LocalCacheTypeEnum)
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends SsoLocalCache> T queryCache(LocalCacheTypeEnum cacheType) {
        T localCache = (T) cacheMap.get(cacheType);

        if (localCache == null) {
            logger.warn("获取缓存对象异常|缓存类型={}", cacheType.getCode());
            throw new BdssoBaseException("获取缓存对象异常|缓存类型=" + cacheType.getCode());
        }
        return localCache;
    }

}
