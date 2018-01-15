/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.service.cache.manager;

import com.bbd.bdsso.common.util.enums.LocalCacheTypeEnum;
import com.bbd.bdsso.core.service.cache.SsoLocalCache;

/**
 * 缓存管理器
 * 
 * @author byron
 * @version $Id: CacheManagerService.java, v 0.1 Nov 20, 2017 5:56:31 PM byron Exp $
 */
public interface CacheManagerService {

    /**
     * 缓存刷新
     * 
     * <p>每台机器起独立定时任务来调用该方法，该方法会尝试刷新系统内所有实现扩展点<code>localCache</code>的缓存
     */
    public void refresh();

    /**
     * 通知刷新本地缓存
     * 
     * @param   cacheType   需要刷新的本地缓存名称枚举
     * @return  true：刷新成功；false：刷新失败
     */
    public boolean notifyRefresh(LocalCacheTypeEnum cacheType);

    /**
     * 缓存获取
     * @param <T> 
     * 
     * @param cacheType     缓存类型
     * @return              本地缓存对象
     */
    <T extends SsoLocalCache> T queryCache(LocalCacheTypeEnum cacheType);
}
