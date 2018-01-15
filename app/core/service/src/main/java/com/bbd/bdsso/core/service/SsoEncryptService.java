/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.core.service;

import java.util.HashMap;

/**
 * SSO加解密服务
 * 
 * @author byron
 * @version $Id: SsoEncryptService.java, v 0.1 Oct 25, 2017 4:52:05 PM byron Exp $
 */
public interface SsoEncryptService {

    /**
     * 加密服务
     * 
     * 形如对"appName=test&timestamp=1508921935014"的字符串进行加密，加密后得到
     * "bpzvR+M9kGPAJfTZgdy3/FNbfbTLnPRNlJ3egl8ZeV4P1PM13wEVQB1DeVbhLXd4"
     * 
     * @param s
     * @return
     */
    public String encrypt(String s, String secret);

    /**
     * 解密服务
     * 
     * 形如对加密后的字符"bpzvR+M9kGPAJfTZgdy3/FNbfbTLnPRNlJ3egl8ZeV4P1PM13wEVQB1DeVbhLXd4"串进行解密，
     * 得到key-value结果
     * 
     * @param s
     * @return
     */
    public HashMap<String, String> decrypt(String s);

}
