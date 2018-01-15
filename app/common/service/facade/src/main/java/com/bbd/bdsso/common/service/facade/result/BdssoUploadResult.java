/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.result;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * SSO上传结果
 * 
 * @author byron
 * @version $Id: BdssoUploadResult.java, v 0.1 Oct 16, 2017 10:35:53 PM byron Exp $
 */
public class BdssoUploadResult extends BdssoBaseResult {

    /** 序列化id */
    private static final long serialVersionUID = -2762433990068038894L;

    /** 文件上传后得到的路径 */
    private String            filePath;

    /**
     * Getter method for property <tt>filePath</tt>.
     * 
     * @return property value of filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Setter method for property <tt>filePath</tt>.
     * 
     * @param filePath value to be assigned to property filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
