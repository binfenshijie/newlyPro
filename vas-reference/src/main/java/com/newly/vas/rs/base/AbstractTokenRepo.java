package com.newly.vas.rs.base;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.newly.common.constant.ErrorCode;
import com.newly.common.exception.BusinessException;

import java.util.concurrent.TimeUnit;

public abstract class AbstractTokenRepo implements TokenRepo{

    protected Cache<String, String> cache =  Caffeine.newBuilder()
            .expireAfterWrite(3600 - 120, TimeUnit.SECONDS)
                .initialCapacity(2)
                .build();

    protected abstract String createToken();

    @Override
    public String getToken() {
        return cache.get("token", s -> {
            try {
                return createToken();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        });
    }


    @Override
    public void refreshToken() {
        try {
            cache.put("token", createToken());
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.ERROR, "Token Creation not correct!", e);
        }
    }
}
