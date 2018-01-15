/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.util;

import java.util.Date;

/**
 * 日期工具类
 * 
 * @author byron
 * @version $Id: DateUtils.java, v 0.1 Sep 25, 2017 5:04:31 PM byron Exp $
 */
public class DateUtils {

    /**
     * 获取当前时刻+duration
     * 
     * @param duration
     * @return
     */
    public static Date plusDuration(int duration) {
        // 当前时刻
        Date now = new Date();
        return new Date(now.getTime() + duration);
    }

    /**
     * 计算两个时刻的差值，单位为毫秒
     * 
     * @param source
     * @param now
     * @return
     */
    public static long getDuration(Date source, Date now) {
        return now.getTime() - source.getTime();
    }

}
