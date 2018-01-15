/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbd.bdsso.common.dal.manual.daointerface.ExtraSsoResourceDAO;
import com.bbd.bdsso.common.dal.manual.dataobject.ExSsoResourceDO;
import com.bbd.bdsso.core.service.SsoResourceService;

/**
 * 资源服务实现
 * 
 * @author byron
 * @version $Id: SsoResourceServiceImpl.java, v 0.1 Nov 17, 2017 3:30:00 PM byron Exp $
 */
public class SsoResourceServiceImpl implements SsoResourceService {

    /** SSO资源手动DAO */
    @Autowired
    private ExtraSsoResourceDAO extraSsoResourceDAO;

    /** 
     * @see com.bbd.bdsso.core.service.SsoResourceService#queryByUid(java.lang.String, java.lang.String)
     */
    @Override
    public HashMap<String, HashMap<String, Boolean>> queryByUid(String uid, String appName) {

        List<ExSsoResourceDO> list = extraSsoResourceDAO.queryAllByParams();

        // 非空校验
        if (null == list) {
            return null;
        }

        HashMap<String, HashMap<String, Boolean>> resultMap = new HashMap<String, HashMap<String, Boolean>>();

        for (ExSsoResourceDO exSsoResourceDO : list) {
            // 过滤条件
            if (StringUtils.equals(uid, exSsoResourceDO.getId().toString()) && StringUtils.equals(appName, exSsoResourceDO.getAppName())) {

                if (resultMap.containsKey(exSsoResourceDO.getUserName())) {
                    resultMap.get(exSsoResourceDO.getUserName()).put(exSsoResourceDO.getResourceName(), true);
                } else {
                    HashMap<String, Boolean> map = new HashMap<String, Boolean>();
                    map.put(exSsoResourceDO.getResourceName(), true);
                    resultMap.put(exSsoResourceDO.getUserName(), map);
                }
            }
        }
        return resultMap;
    }

}
