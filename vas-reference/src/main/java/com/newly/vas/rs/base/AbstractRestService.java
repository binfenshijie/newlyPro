package com.newly.vas.rs.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import javax.ws.rs.core.UriBuilder;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by bingo on 2019/1/29 下午4:38
 * rest远程调用虚类
 * 继承此类并实现basehandler处理函数
 */
@Slf4j
public abstract class AbstractRestService implements RestService {

    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected final Logger logger = LoggerFactory.getLogger(AbstractRestService.class);

    public <T> T exchange(
            String url,                     // 请求完整路径
            HttpMethod method,              // 请求方法
            String body,                    // 请求json字符串
            Class<T> responseType,          // 返回类
            Map<String, ?> uriVariables,    // uri占位符参数
            HttpHeaders heads
    ) {
        if (uriVariables != null && !uriVariables.isEmpty()) {
            url = UriBuilder.fromPath(url).buildFromMap(uriVariables).toString();
            url = UriBuilder.fromUri(url).buildFromMap(uriVariables).toString();
        }
        String decodeUrl = url;
        try {
            decodeUrl = java.net.URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }

//        log.info("--------->>>>> {} ", heads);
        url = decodeUrl;
        String data = null;
        try {
            data = this.basicHandler(url, body, method, heads);
//            logger.info("invoke url {}, response {}", url, data);
            if (data != null) {
                return objectMapper.readValue(data, responseType);
            }
        } catch (Exception e) {
            logger.error("invoke url {}  fail! response {}. error: {}", url, data, e);
        }
        return null;
    }

    public <T> T exchange(
            String url,
            HttpMethod method,
            String body,
            Class<T> responseType,
            Map<String, ?> uriVariables
    ) {
        return this.exchange(url, method, body, responseType, uriVariables, null);
    }

    public <T> T exchange(
            String url,
            HttpMethod method,
            String body,
            Class<T> responseType
    ) {
        return this.exchange(url, method, body, responseType, null, null);
    }

    public <T> T exchange(
            String url,
            HttpMethod method,
            Class<T> responseType,
            Map<String, ?> uriVariables
    ) {
        return this.exchange(url, method, null, responseType, uriVariables, null);
    }


    public <T> T exchange(
            String url,
            HttpMethod method,
            Class<T> responseType
    ) {
        return this.exchange(url, method, null, responseType, null, null);
    }

    protected abstract String basicHandler(
            String url,
            String body,
            HttpMethod method,
            HttpHeaders heads);
}
