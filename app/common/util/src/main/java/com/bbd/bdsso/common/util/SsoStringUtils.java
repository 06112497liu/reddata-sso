/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.util;

import java.util.regex.Pattern;

import com.bbd.bdsso.common.util.enums.BdssoResultEnum;
import com.bbd.bdsso.common.util.exception.BdssoBaseException;

/**
 * 字符串工具类
 * 
 * @author byron
 * @version $Id: SsoStringUtils.java, v 0.1 Nov 9, 2017 10:45:01 AM byron Exp $
 */
public class SsoStringUtils {

    /**
     * 组合字符串，结果行如：uid=xxx&token=xxx
     * 
     * @param uid       用户id
     * @param token     用户token
     * @return
     */
    public static String assemblyStr(String uid, String token) {
        return SsoConstant.UID + SsoConstant.EQUAL + uid + SsoConstant.AND + SsoConstant.TOKEN + SsoConstant.EQUAL + token;
    }

    /**
     * 验证邮箱，邮箱表达式以","分割
     * 
     * @param email                 用户邮箱
     * @param emailEegularExp       邮箱后缀表达式
     * @return
     */
    public static boolean checkEmail(String email, String emailEegularExp) {

        Pattern p = null;
        /*String s[] = emailEegularExp.trim().split(SsoConstant.COMMA);
        if (s.length < 0) {
            throw new BdssoBaseException(BdssoResultEnum.ERROR_EMAIL_REGULAR_EXPRES);
        }
        for (int i = 0; i < s.length; i++) {
            p = Pattern.compile("^[a-zA-Z0-9_-]+@" + s[i] + "$");
            if (p.matcher(email).matches()) {
                return true;
            }
        }*/
        p = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
        if (p.matcher(email).matches()) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串长度
     * 
     * @param value
     * @return
     */
    public static int length(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为2
                valueLength += 2;
            } else {
                // 其他字符长度为1
                valueLength += 1;
            }
        }
        return valueLength;
    }

}
