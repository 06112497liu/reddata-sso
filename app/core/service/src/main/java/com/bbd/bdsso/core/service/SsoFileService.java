/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.service;

/**
 * 文件上传服务
 * 
 * @author byron
 * @version $Id: SsoFileService.java, v 0.1 Oct 16, 2017 5:17:11 PM byron Exp $
 */
public interface SsoFileService {

    /**
     * 文件上传服务
     * 
     * @param file          文件名
     * @param data          文件二进制流
     * @return
     */
    public String upload(String file, byte[] data);

    /**
     * 获取文件系统根目录
     * 
     * @return
     */
    public String getRootPath();

    /**
     * 获取文件系统url前缀
     * 
     * @return
     */
    public String getUrlPrefix();

}
