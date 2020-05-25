package com.newly.vas.base.utils;


import com.mysql.jdbc.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.security.GeneralSecurityException;

public class Auth {

    public final String accessKey;
    private final SecretKeySpec secretKey;
    private static final String FormMime = "application/x-www-form-urlencoded";

    private Auth(String accessKey, SecretKeySpec secretKeySpec) {
        this.accessKey = accessKey;
        this.secretKey = secretKeySpec;
    }

//    public static Auth create(String accessKey, String secretKey) {
//        if (StringUtils.isNullOrEmpty(accessKey) || StringUtils.isNullOrEmpty(secretKey)) {
//            throw new IllegalArgumentException("empty key");
//        }
//        byte[] sk = StringUtils.utf8Bytes(secretKey);
//        SecretKeySpec secretKeySpec = new SecretKeySpec(sk, "HmacSHA1");
//        return new Auth(accessKey, secretKeySpec);
//    }
//
//    private Mac createMac() {
//        Mac mac;
//        try {
//            mac = javax.crypto.Mac.getInstance("HmacSHA1");
//            mac.init(secretKey);
//        } catch (GeneralSecurityException e) {
//            e.printStackTrace();
//            throw new IllegalArgumentException(e);
//        }
//        return mac;
//    }
//
//    public String sign(byte[] data) {
//        Mac mac = createMac();
//        String encodedSign = UrlSafeBase64.encodeToString(mac.doFinal(data));
//        return this.accessKey + ":" + encodedSign;
//    }
//
//    public String sign(String data) {
//        return sign(StringUtils.utf8Bytes(data));
//    }
//
//    public String signWithData(byte[] data) {
//        String s = UrlSafeBase64.encodeToString(data);
//        return sign(StringUtils.utf8Bytes(s)) + ":" + s;
//    }
//
//    public String signWithData(String data) {
//        return signWithData(StringUtils.utf8Bytes(data));
//    }
//
//    /**
//     * 生成HTTP请求签名字符串
//     *
//     * @param urlString
//     * @param body
//     * @param contentType
//     * @return
//     */
//    public String signRequest(String urlString, byte[] body, String contentType) {
//        URI uri = URI.create(urlString);
//        String path = uri.getRawPath();
//        String query = uri.getRawQuery();
//
//        Mac mac = createMac();
//
//        mac.update(StringUtils.utf8Bytes(path));
//
//        if (query != null && query.length() != 0) {
//            mac.update((byte) ('?'));
//            mac.update(StringUtils.utf8Bytes(query));
//        }
//        mac.update((byte) '\n');
//        if (body != null && FormMime.equalsIgnoreCase(contentType)) {
//            mac.update(body);
//        }
//
//        String digest = UrlSafeBase64.encodeToString(mac.doFinal());
//
//        return this.accessKey + ":" + digest;
//    }

//    /**
//     * 验证回调签名是否正确
//     *
//     * @param originAuthorization 待验证签名字符串，以 "Newly "作为起始字符
//     * @param url                 回调地址
//     * @param body                回调请求体。原始请求体，不要解析后再封装成新的请求体--可能导致签名不一致。
//     * @param contentType         回调ContentType
//     * @return
//     */
//    public boolean isValidCallback(String originAuthorization, String url, byte[] body, String contentType) {
//        String authorization = "Newly " + signRequest(url, body, contentType);
//        return authorization.equals(originAuthorization);
//    }
//
//
//    public static void main(String[] args) {
//        Auth auth = Auth.create("AAAAAAAAAAAAA", "BBBBBBBBBBB");
//        System.out.println("Newly " + auth.signRequest("http://192.168.9.71/vas-server/service/rs/v1/gatewayRemoteService/gatewayState?a=1",null,null));
//    }
}
