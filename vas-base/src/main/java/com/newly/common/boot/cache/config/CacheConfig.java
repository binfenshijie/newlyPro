package com.newly.common.boot.cache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;

/**
 * Created by bingo on 2020/4/22 下午4:20.
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.host}")
    private String host;

    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisConnectionFactory jedisConnectionFactory(){

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig());
        return jedisConnectionFactory;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.redis.pool")
    public JedisPoolConfig jedisPoolConfig(){
        return new JedisPoolConfig();
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setUsePrefix(true);
        cacheManager.setDefaultExpiration(300L);
        return cacheManager;
    }

    @Primary
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory,
                                                       RedisSerializer<Object> redisSerializer) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setDefaultSerializer(redisSerializer());
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(redisSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(redisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisSerializer<Object> redisSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =
                new Jackson2JsonRedisSerializer<Object>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper());
        return jackson2JsonRedisSerializer;
    }

    private ObjectMapper objectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        return om;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new QiniuKeyGenerator();
    }

    private class QiniuKeyGenerator extends SimpleKeyGenerator {

        @Override
        public Object generate(Object target, Method method, Object... params) {
            return super.generate(target, method, params).toString();
        }
    }
}