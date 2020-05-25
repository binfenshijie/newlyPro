package com.newly.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author bingo
 * @createDate 2018/3/19.
 * @since version
 */
public class IpAddressUtil {

    public static final String IP_REGULAR = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

    public static final String PORT_REGULAR = "^([1-9][0-9]{0,3}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]{1}|6553[0-5])$";

    public IpAddressUtil(){}

    public static String getRemoteIpAddress(HttpServletRequest request) {
        if (request == null) {
            return null;
        } else {
            String s = request.getHeader("X-Forwarded-For");
            if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
                s = request.getHeader("Proxy-Client-IP");
            }

            if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
                s = request.getHeader("WL-Proxy-Client-IP");
            }

            if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
                s = request.getHeader("HTTP_CLIENT_IP");
            }

            if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
                s = request.getHeader("HTTP_X_FORWARDED_FOR");
            }

            if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
                s = request.getRemoteAddr();
            }

            /* 过滤本服务的ip地址 */
            if ("127.0.0.1".equals(s) || "0:0:0:0:0:0:0:1".equals(s)) {
                try {
                    s = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException unknownhostexception) {
                }
            }
            return s;
        }

    }

    public static String getMacAddressIP(String remotePcIP) {
        String macAddress = "";
        Process pp = null;
        try {
            pp = Runtime.getRuntime().exec("nbtstat -a " + remotePcIP);
        }catch (IOException e) {
            return macAddress;
        }

        try (InputStreamReader read = new InputStreamReader(pp.getInputStream(), Charset.forName("GBK"));
             BufferedReader bufferedReader = new BufferedReader(read)) {
            String lineTxt = null;
            while((lineTxt = bufferedReader.readLine()) != null) {
                if (lineTxt.indexOf("MAC") >= 0) {
                    String[] macInfo = lineTxt.split("=");
                    if (macInfo.length > 1) {
                        macAddress = macInfo[1].trim();
                    }
                }
            }
        } catch (IOException e) {}

        return macAddress;
    }

    public static boolean isValidIp(String ip) {
        Pattern pattern = Pattern.compile(IP_REGULAR);
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }

    public static boolean isValidPort(String port) {
        Pattern pattern = Pattern.compile(PORT_REGULAR);
        Matcher matcher = pattern.matcher(port);
        return matcher.matches();
    }
}
