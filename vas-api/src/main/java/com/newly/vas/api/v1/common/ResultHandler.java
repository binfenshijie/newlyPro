package com.newly.vas.api.v1.common;

import com.newly.vas.api.v1.common.bean.BaseData;
import com.newly.vas.api.v1.common.bean.BaseResult;

public class ResultHandler {
    public <T> BaseData resultHandler(int code, String msg) {
        return new BaseData(code, msg);
    }


    public <T> BaseData resultHandler(int code, String msg, T data) {
        return new BaseResult<T>(code, msg, data);
    }
}
