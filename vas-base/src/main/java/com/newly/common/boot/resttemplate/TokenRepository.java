package com.newly.common.boot.resttemplate;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.newly.common.constant.ErrorCode;
import com.newly.common.exception.BusinessException;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;


/**
 * Created by bingo on 2020/4/4 上午11:31
 */
public class TokenRepository {

  private Cache<String, String> cache;

  private String token;

  public TokenRepository(long expireTime) {
    this.cache = Caffeine.newBuilder()
        .expireAfterWrite(expireTime - 120, TimeUnit.SECONDS)
        .initialCapacity(1)
        .build();
  }

  public String getToken() {
    if (token == null) {
      try {
        this.token = new String(Base64.getEncoder().encode("newly token".getBytes()), "UTF-8");
      } catch (UnsupportedEncodingException e) {
        throw new BusinessException(ErrorCode.ERROR, "Token Creation not correct!", e);
      }
    }
    return token;
  }

  /**
   * 生成并更新tokenEx
   */
  public String getTokenEx() {
    return cache.get("exToken", s -> {
      try {
        return new String(Base64.getEncoder().encode("newly ex token".getBytes()),"UTF-8");
      } catch (UnsupportedEncodingException e) {
        throw new BusinessException(ErrorCode.ERROR, "Token Creation not correct!", e);
      }
    });
  }

  public void refreshTokenEx() {
    try {
      cache.put("exToken", new String(Base64.getEncoder().encode("newly ex token".getBytes()), "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      throw new BusinessException(ErrorCode.ERROR, "Token Creation not correct!", e);
    }
  }
}
