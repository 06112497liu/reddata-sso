/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.service.cache;

import com.bbd.bdsso.common.util.enums.LocalCacheStatusEnum;

/**
 * Bdsso本地缓存
 * 
 * @author byron
 * @version $Id: SsoLocalCache.java, v 0.1 Nov 20, 2017 2:01:06 PM byron Exp $
 */
public interface SsoLocalCache {

    /**
     * 初始化
     */
    public void init();

    /**
     * 强制刷新
     */
    public void forceRefresh();

    /**
     * 输出日志
     */
    public void dump();

    /**
     * 获取枚举类型
     * 
     * @return
     */
    public LocalCacheStatusEnum getCacheType();

}
