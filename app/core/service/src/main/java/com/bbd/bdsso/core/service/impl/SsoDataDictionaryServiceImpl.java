/**
 * BrandBigData.com Inc
 * Copyright (c) 2017 All Rights Reserved
 */
package com.bbd.bdsso.core.service.impl;

import com.bbd.bdsso.common.dal.daointerface.SsoDataDictionaryDAO;
import com.bbd.bdsso.common.dal.dataobject.SsoDataDictionaryDO;
import com.bbd.bdsso.common.util.enums.BdssoResultEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;
import com.bbd.bdsso.core.service.SsoDataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * SSO数据字典服务
 * @author $ hyjun
 * @version Id: SsoDataDictionaryServiceImpl, v 0.1 2017-12-12 上午 10:53 hyjun Exp $$
 */
public class SsoDataDictionaryServiceImpl implements SsoDataDictionaryService {

    @Autowired
    private SsoDataDictionaryDAO ssoDataDictionaryDAO;

    @Override
    public List<SsoDataDictionaryDO> getDataByType(String type) {
        List<SsoDataDictionaryDO> ssoDataDictionaryDOS = ssoDataDictionaryDAO.queryByType(type);
        // 数据不存在
        if (null == ssoDataDictionaryDOS || ssoDataDictionaryDOS.isEmpty())
            throw new BdssoBaseException(BdssoResultEnum.DATA_DICTIONARY_NOT_EXIST);

        return ssoDataDictionaryDOS;
    }
}
