/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.util;

import javax.ws.rs.core.MediaType;

/**
 * 自定义Rest响应
 * 
 * @author byron
 * @version $Id: ContentType.java, v 0.1 Sep 13, 2017 3:18:04 PM byron Exp $
 */
public class ContentType {
    public static final String APPLICATION_JSON_UTF_8 = MediaType.APPLICATION_JSON + "; " + MediaType.CHARSET_PARAMETER + "=UTF-8";
    public static final String TEXT_XML_UTF_8         = MediaType.TEXT_XML + "; " + MediaType.CHARSET_PARAMETER + "=UTF-8";
    public static final String TEXT_PLAIN_UTF_8       = MediaType.TEXT_PLAIN + "; " + MediaType.CHARSET_PARAMETER + "=UTF-8";
}
