package com.newly.common.boot.resttemplate;

import com.newly.common.boot.resttemplate.TokenRepository;
import com.newly.common.constant.ErrorCode;
import com.newly.common.exception.BusinessException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * token认证逻辑还没加
 * Created by bingo on 2020/4/4 上午11:31
 */
public class QiniuRestTemplate {

    /**
     * @param restTemplate
     * @param tokenRepository
     */
    public QiniuRestTemplate(RestTemplate restTemplate, TokenRepository tokenRepository) {
        this.restTemplate = restTemplate;
        this.tokenRepository = tokenRepository;
    }

    private RestTemplate restTemplate;

    private TokenRepository tokenRepository;

    public <T, B> ResponseEntity<T> exchange(String url, HttpMethod method, HttpHeaders headers, B body, Class<T> responseType,
                                             Map<String, ?> uriVariables) {
        return exchange(url, method, headers, body, ParameterizedTypeReference.forType(responseType), uriVariables);
    }

    public <T, B> ResponseEntity<T> exchange(String url, HttpMethod method, HttpHeaders headers, B body,
                                             ParameterizedTypeReference<T> responseType, Map<String, ?> uriVariables) {
        HttpEntity<B> httpEntity = new HttpEntity<>(body, headers);
        if (uriVariables == null || uriVariables.isEmpty()) {
            return restTemplate.exchange(url, method, httpEntity, responseType);
        } else {
            return restTemplate.exchange(url, method, httpEntity, responseType, uriVariables);
        }
    }

    public <T, B> T executeEx(String url, HttpMethod method, HttpHeaders headers, B body, ParameterizedTypeReference<T> responseType,
                              Map<String, ?> uriVariables) {
        ResponseEntity<T> responseEntity = exchange(url, method, headers, body, responseType, uriVariables);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else if (responseEntity.getStatusCode().value() == 403) { //强制更新exToken
            tokenRepository.refreshTokenEx();
            responseEntity = exchange(url, method, headers, body, responseType, uriVariables);
            return responseEntity.getBody();
        } else {
            throw new BusinessException(ErrorCode.ERROR, "Call " + url + "not success.");
        }
    }

    public <T, B> T executeEx(String url, HttpMethod method, HttpHeaders headers, B body, Class<T> responseType,
                              Map<String, ?> uriVariables) {
        return executeEx(url, method, headers, body, ParameterizedTypeReference.forType(responseType), uriVariables);
    }

}
