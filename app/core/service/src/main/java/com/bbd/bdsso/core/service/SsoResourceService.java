/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.service;

import java.util.HashMap;

/**
 * 资源服务接口
 * 
 * @author byron
 * @version $Id: SsoResourceService.java, v 0.1 Nov 17, 2017 3:12:18 PM byron Exp $
 */
public interface SsoResourceService {

    /**
     * 通过uid来查找
     * <p>查询结果：
     * <p>username1, resourceName1 true
     * <p>username2, resourceName2 false
     * 
     * @param uid       用户id
     * @param appName   应用名
     * @return
     */
    public HashMap<String, HashMap<String, Boolean>> queryByUid(String uid, String appName);

}
