package com.newly.common.bean;

import com.newly.common.bean.BasePage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BasePageWithData<T> extends BasePage implements Serializable {

    private static final long serialVersionUID = -3144974522217761782L;

    private Object data;

    public BasePageWithData(int code, String msg, int total, List<T> list, int pageNo, Object data) {
        this.code = code;
        this.msg = msg;
        this.total = total;
        this.list = list;
        this.pageNo = pageNo;
    }

}
