package com.newly.vas.rs.atlab.dto;

import lombok.Data;

/**
 * Created by bingo on 2018/8/29 下午5:03
 */
@Data
public class RestResult<T> {

    private Integer code;
    private String message;
    private T result;

}
