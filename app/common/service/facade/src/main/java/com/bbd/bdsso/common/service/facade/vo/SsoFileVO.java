/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.vo;

import java.io.Serializable;

import javax.ws.rs.FormParam;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

/**
 * 文件上传VO
 * 
 * @author byron
 * @version $Id: SsoFileVO.java, v 0.1 Oct 16, 2017 5:46:26 PM byron Exp $
 */
public class SsoFileVO implements Serializable {

    /** 序列化id */
    private static final long serialVersionUID = 7573255930314561145L;

    /** 文件名 */
    @FormParam("fileName")
    private String            fileName;

    /** 文件字节码 */
    private byte[]            fileData;

    /**
     * Getter method for property <tt>fileName</tt>.
     * 
     * @return property value of fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Setter method for property <tt>fileName</tt>.
     * 
     * @param fileName value to be assigned to property fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Getter method for property <tt>fileData</tt>.
     * 
     * @return property value of fileData
     */
    public byte[] getFileData() {
        return fileData;
    }

    /**
     * Setter method for property <tt>fileData</tt>.
     * 
     * @param fileData value to be assigned to property fileData
     */
    @FormParam("fileData")
    @PartType("application/octet-stream")
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
