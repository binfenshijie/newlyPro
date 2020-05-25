//package com.newly.vas.business.utils;
//
//import org.apache.http.Header;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.protocol.HttpClientContext;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.util.EntityUtils;
//import org.springframework.http.HttpStatus;
//
//import java.io.IOException;
//
//public class HttpClientUtil {
//    public static void main(String[] args) throws Exception{
//        String url = "";
//        // 1.创建一个httpclient对象
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        // 2.创建一个httpGet对象
//        HttpGet request = new HttpGet(url);
//        CloseableHttpResponse response = null;
//
//            // 3.执行请求调用httpclient的execute(),传入httpGet对象，返回CloseableHttpResponse
//        try {
//            response = httpClient.execute(request, HttpClientContext.create());
//            // 4.取得响应结果并处理
//            HttpEntity entity = response.getEntity();
//            String responseString = EntityUtils.toString(entity);
//            HttpHeaders httpHeaders = new HttpHeaders();
//            for (Header header : response.getAllHeaders()) {
//                httpHeaders.add(header.getName(), header.getValue());
//            }
//            int statusCode = response.getStatusLine().getStatusCode();
//            EntityUtils.consume(entity);
//
//            return new ResponseEntity<>(responseString, httpHeaders, HttpStatus.valueOf(statusCode));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            if (response != null) {
//                try {
//                    response.close();
//                } catch (IOException ignored) {
//                }
//            }
//            httpClient.close();
//        }
//
//    }
//}