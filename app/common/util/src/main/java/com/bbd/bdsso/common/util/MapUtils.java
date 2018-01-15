/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Map工具类
 * 
 * @author byron
 * @version $Id: MapUtils.java, v 0.1 Nov 6, 2017 4:23:47 PM byron Exp $
 */
public class MapUtils {

    /**
     * 构建权限比较结果，返回结果格式为
     * <p>auth_code_1  true
     * <p>auth_code_2  false
     * <p>auth_code_3  true
     * 
     * @param sourceList    SSO系统的权限值
     * @param compareList   客户端传递过来的权限值
     * @return
     */
    public static HashMap<String, Boolean> constr(HashMap<String, Boolean> sourceList, ArrayList<String> compareList) {
        HashMap<String, Boolean> result = new HashMap<String, Boolean>();

        for (String s : compareList) {
            if (sourceList.containsKey(s)) {
                result.put(s, true);
            } else {
                result.put(s, false);
            }
        }

        return result;
    }

    /**
     * 构建权限比较结果，返回结果格式为
     * <p>auth_code_1  true
     * <p>auth_code_2  false
     * <p>auth_code_3  true
     * 
     * @param sourceList    SSO系统的权限值
     * @return
     */
    public static HashMap<String, Boolean> constr(HashMap<String, Boolean> sourceList) {
        HashMap<String, Boolean> result = new HashMap<String, Boolean>();

        for (String s : sourceList.keySet()) {
            if (sourceList.containsKey(s)) {
                result.put(s, true);
            } else {
                result.put(s, false);
            }
        }

        return result;
    }

}
