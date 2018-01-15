/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.bbd.bdsso.common.util.enums.BdssoResultEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;
import com.bbd.bdsso.core.service.SsoFileService;

/**
 * 文件上传服务实现
 * 
 * @author byron
 * @version $Id: SsoFileServiceImpl.java, v 0.1 Oct 16, 2017 5:19:44 PM byron Exp $
 */
public class SsoFileServiceImpl implements SsoFileService {

    /** 文件系统的根目录 */
    private String fileSystemRootPath;

    /** url和port */
    private String hostAndPort;

    /**
     * @throws IOException 
     * @see com.bbd.bdsso.core.service.SsoFileService#upload(java.io.File, byte[])
     */
    @Override
    public String upload(String fileName, byte[] data) {

        String filePath = fileSystemRootPath + fileName;

        File file = new File(filePath);
        try {
            FileUtils.writeByteArrayToFile(file, data);
        } catch (IOException e) {
            throw new BdssoBaseException(BdssoResultEnum.ERROR_UPLOAD_FILE);
        }
        return hostAndPort + fileName;
    }

    /**
     * Getter method for property <tt>fileSystemRootPath</tt>.
     * 
     * @return property value of fileSystemRootPath
     */
    public String getFileSystemRootPath() {
        return fileSystemRootPath;
    }

    /**
     * Setter method for property <tt>fileSystemRootPath</tt>.
     * 
     * @param fileSystemRootPath value to be assigned to property fileSystemRootPath
     */
    public void setFileSystemRootPath(String fileSystemRootPath) {
        this.fileSystemRootPath = fileSystemRootPath;
    }

    /**
     * Setter method for property <tt>hostAndPort</tt>.
     * 
     * @param hostAndPort value to be assigned to property hostAndPort
     */
    public void setHostAndPort(String hostAndPort) {
        this.hostAndPort = hostAndPort;
    }

    /** 
     * @see com.bbd.bdsso.core.service.SsoFileService#getRootPath()
     */
    @Override
    public String getRootPath() {
        return fileSystemRootPath;
    }

    /** 
     * @see com.bbd.bdsso.core.service.SsoFileService#getUrlPrefix()
     */
    @Override
    public String getUrlPrefix() {
        return hostAndPort;
    }

}
