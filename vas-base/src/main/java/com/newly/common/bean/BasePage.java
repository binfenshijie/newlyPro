package com.newly.common.bean;

import com.newly.common.bean.BaseData;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页返回类
 * Created by bingo on 2020/4/27 下午2:52.
 */
@Data
public class BasePage<T> extends BaseData implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 4221425786302636919L;

    protected int total;

    protected List<T> list;

    protected int pageNo;

    public BasePage(int code, String msg, int total, List<T> list, int pageNo) {
        this.code = code;
        this.msg = msg;
        this.total = total;
        this.list = list;
        this.pageNo = pageNo;
    }

    public BasePage() {
    }
}

