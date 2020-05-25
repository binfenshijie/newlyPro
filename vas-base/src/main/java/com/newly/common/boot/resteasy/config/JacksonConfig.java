package com.newly.common.boot.resteasy.config;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.text.SimpleDateFormat;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Created by bingo on 2020/4/21.
 * 自定义了resteasy的objectMapper
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonConfig implements ContextResolver<ObjectMapper> {

  private ObjectMapper objectMapper;

  public JacksonConfig(){
    objectMapper = new ObjectMapper();
    //utc标准时间格式
    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    objectMapper.setSerializationInclusion(Include.NON_NULL);
  }

  @Override
  public ObjectMapper getContext(Class<?> arg0) {
    return objectMapper;
  }
}