package com.newly.vas.api.v1.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by bingo on 2020/4/27 下午2:52.
 */
@Data
public class BaseResult<T> extends BaseData implements Serializable {

    private static final long serialVersionUID = 6803323956728517039L;

    private T data;      //返回的数据

    public BaseResult() {
    }

    public BaseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}


