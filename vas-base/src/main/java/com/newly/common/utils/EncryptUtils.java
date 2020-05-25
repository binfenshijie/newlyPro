package com.newly.common.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by bingo on 2020/4/17 下午2:05
 * 常用加密解密算法类
 */
public class EncryptUtils {

    /**
     * 用MD5算法进行加密
     * @param str 需要加密的字符串
     * @return MD5加密后的结果
     */
    public static String encodeMD5String(final String str) {
        return encode(str, "MD5");
    }

    /**
     * 用SHA算法进行加密
     * @param str 需要加密的字符串
     * @return SHA加密后的结果
     */
    public static String encodeSHAString(final String str) {
        return encode(str, "SHA-1");
    }

    /**
     * 用SHA256算法进行加密
     * @param str 需要加密的字符串
     * @return SHA256加密后的结果
     */
    public static String encodeSHA256String(final String str) {
        return encode(str, "SHA-256");
    }

    /**
     * 用base64算法进行加密
     * @param str 需要加密的字符串
     * @return base64加密后的结果
     */
    public static String encodeBase64String(final byte[] str) {
        return Base64.getEncoder().encodeToString(str);
    }

    /**
     * 用base64算法进行解密
     * @param str 需要解密的字符串
     * @return base64解密后的结果
     * @throws IOException
     */
    public static String decodeBase64String(final String str){
        return new String(Base64.getDecoder().decode(str));
    }

    private static String encode(final String str, String method) {
        MessageDigest md = null;
        String dstr = null;
        try {
            md = MessageDigest.getInstance(method);
            md.update(str.getBytes());
            dstr = bytes2Hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return dstr;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
}
