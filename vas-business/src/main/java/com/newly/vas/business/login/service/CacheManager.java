package com.newly.vas.business.login.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by bingo on 2019/1/30 3:35 PM
 */
public class CacheManager {

    private static class CacheManagerHolder{
        private final static CacheManager cacheInstance = new CacheManager();
    }

    public static CacheManager getInstance(){
        return CacheManagerHolder.cacheInstance;
    }

    private Cache<String, String> cache;

    private  Logger logger = LoggerFactory.getLogger(CacheManager.class);

    private static final long CHECK_PHONE_TIMES = 180;  //手机验证码过期时间 3分钟

    private CacheManager() {
        cache = Caffeine.newBuilder()
                .expireAfterWrite(CHECK_PHONE_TIMES, TimeUnit.SECONDS)
                .initialCapacity(100).build();
    }
    public void putPhoneCaptcha(String phone, String msg){
        cache.put(phone, msg);
    }

    public String getPhoneCaptcha(String phone){
       return cache.getIfPresent(phone);
    }

}