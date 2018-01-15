/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 系统工具类
 * 
 * @author byron
 * @version $Id: SystemUtil.java, v 0.1 Nov 20, 2017 3:33:43 PM byron Exp $
 */
public class SystemUtil {

    private static final HostInfo HOST_INFO = new HostInfo();

    /**
     * 取得Host的信息。
     *
     * @return <code>HostInfo</code>对象
     */
    public static final HostInfo getHostInfo() {
        return HOST_INFO;
    }

    /**
     * 代表当前主机的信息。
     */
    public static final class HostInfo {
        private final String HOST_NAME;
        private final String HOST_ADDRESS;

        /**
         * 防止从外界创建此对象。
         */
        private HostInfo() {
            String hostName;
            String hostAddress;

            try {
                InetAddress localhost = InetAddress.getLocalHost();

                hostName = localhost.getHostName();
                hostAddress = localhost.getHostAddress();
            } catch (UnknownHostException e) {
                hostName = SsoConstant.LOCALHOST;
                hostAddress = SsoConstant.LOCAL_ADDRESS;
            }

            HOST_NAME = hostName;
            HOST_ADDRESS = hostAddress;
        }

        /**
         * 取得当前主机的名称。
         * 
         * <p>
         * 例如：<code>"webserver1"</code>
         * </p>
         *
         * @return 主机名
         */
        public final String getName() {
            return HOST_NAME;
        }

        /**
         * 取得当前主机的地址。
         * 
         * <p>
         * 例如：<code>"192.168.0.1"</code>
         * </p>
         *
         * @return 主机地址
         */
        public final String getAddress() {
            return HOST_ADDRESS;
        }
    }

}
