package com.newly.common.controller;


import com.newly.common.bean.BaseData;
import com.newly.common.bean.BasePage;
import com.newly.common.bean.BasePageWithData;
import com.newly.common.bean.BaseResult;

import java.util.List;

public class BaseController {

    public <T> BaseData resultHandler(int code, String msg) {
        return new BaseData(code, msg);
    }


    public <T> BaseData resultHandler(int code, String msg, T data) {
        return new BaseResult<T>(code, msg, data);

    }

    public <T> BaseData resultHandler(int code, String msg, List list, int totalCount, int pageNo) {
        return new BasePage<T>(code, msg, totalCount, list, pageNo);
    }

    public <T> BaseData resultHandler(int code, String msg, List list, int totalCount, int pageNo, Object object) {
        return new BasePageWithData<>(code, msg, totalCount, list, pageNo, object);
    }
}
