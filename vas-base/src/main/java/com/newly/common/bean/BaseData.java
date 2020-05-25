package com.newly.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bingo
 * @version 1.00
 * @Date 2020/4/2 下午3:33
 */
@Data
public class BaseData implements Serializable {

    private static final long serialVersionUID = 4446716670192135555L;

    protected int code; //处理结果业务代码

    protected String msg; //返回消息

    public BaseData(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseData() {
    }
}