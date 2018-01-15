/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.util;

import java.util.Date;

/**
 * 文件工具类
 * 
 * @author byron
 * @version $Id: FileUtils.java, v 0.1 Nov 27, 2017 10:21:53 AM byron Exp $
 */
public class FileUtils {

    /**
     * 生产最新的文件名，如把test.png转换成1511750609482.png
     * 
     * @param fileName  文件名
     * @return
     */
    public static String getNewFileName(String fileName) {
        String postfix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        return new Date().getTime() + postfix;
    }
}
