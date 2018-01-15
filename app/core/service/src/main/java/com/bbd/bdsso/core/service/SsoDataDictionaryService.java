/**
 * BrandBigData.com Inc
 * Copyright (c) 2017 All Rights Reserved
 */
package com.bbd.bdsso.core.service;

import com.bbd.bdsso.common.dal.dataobject.SsoDataDictionaryDO;

import java.util.HashMap;
import java.util.List;

/**
 * SSO数据字典服务接口
 * @author $ hyjun
 * @version Id: SsoDataDictionaryService, v 0.1 2017-12-12 上午 10:51 hyjun Exp $$
 */
public interface SsoDataDictionaryService {
    /**
     * 通过uid、appName和authCode来验证权限
     *
     * @param type
     * @return
     */
    public List<SsoDataDictionaryDO> getDataByType(String type);
}
