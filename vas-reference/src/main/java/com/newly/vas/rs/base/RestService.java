package com.newly.vas.rs.base;

import org.springframework.http.HttpMethod;

import java.util.Map;

/**
 * Created by bingo on 2019/1/28 下午1:38
 * rest抽象接口
 */
public interface RestService {

    /**
     * @param url          请求完整路径
     * @param method       请求方法
     * @param body         请求json字符串
     * @param responseType 返回类
     * @param uriVariables uri占位符参数
     * @param <T>
     * @return example http://152.121.4.1/group/{groupId}/delete/{faceId}
     * map.put(groupId,"1").put(faceId,"11")
     * 路径名和map key对应q
     */
    <T> T exchange(String url, HttpMethod method, String body, Class<T> responseType, Map<String, ?> uriVariables);

    /**
     * @param url          请求完整路径
     * @param method       请求方法
     * @param body         请求json字符串
     * @param responseType 返回类
     * @param <T>
     * @return
     */
    <T> T exchange(String url, HttpMethod method, String body, Class<T> responseType);

    /**
     * @param url          请求完整路径
     * @param method       请求方法
     * @param responseType 返回类
     * @param uriVariables uri占位符参数
     * @param <T>
     * @return
     */
    <T> T exchange(String url, HttpMethod method, Class<T> responseType, Map<String, ?> uriVariables);

    /**
     * @param url          请求完整路径
     * @param method       请求方法
     * @param responseType 返回类
     * @param <T>
     * @return
     */
    <T> T exchange(String url, HttpMethod method, Class<T> responseType);

}
