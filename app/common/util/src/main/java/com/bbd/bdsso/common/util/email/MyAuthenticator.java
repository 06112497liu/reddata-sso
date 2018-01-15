/**  
 * BBD Service Inc
 * All Rights Reserved @2016
 *
 */
package com.bbd.bdsso.common.util.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 密码验证
 * 
 * @author byron
 * @version $Id: MyAuthenticator.java, v 0.1 Nov 28, 2016 10:30:25 AM byron Exp $
 */
public class MyAuthenticator extends Authenticator {

    /** 用户名 */
    private String userName = null;

    /** 密码 */
    private String password = null;

    public MyAuthenticator() {

    }

    public MyAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }

}
