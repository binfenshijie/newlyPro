package com.newly.common.utils;

/**
 * Created by bingo on 2018/12/11 3:40 PM
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by bingo on 2017/3/3.
 */
public class JsonUtils {
    private static com.newly.common.utils.JsonUtils INSTANCE = new com.newly.common.utils.JsonUtils();

    private ObjectMapper mapper = new ObjectMapper();

    public static com.newly.common.utils.JsonUtils getJsonUtlis(){
        return INSTANCE;
    }

    public byte[] object2Byte(Object value) {
        try {
            return  mapper.writeValueAsBytes(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String object2String(Object value){
        try {
            return  mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T string2Object(String content, Class<T> valueType){
        try {
            return mapper.readValue(content, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T byte2Object(byte[] content, Class<T> valueType) {
        try {
            return mapper.readValue(content, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
