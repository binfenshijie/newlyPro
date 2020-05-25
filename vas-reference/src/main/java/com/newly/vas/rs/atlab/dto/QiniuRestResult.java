package com.newly.vas.rs.atlab.dto;

import lombok.Data;
import net.sf.json.JSONObject;

/**
 * @author GaiYanjun
 * @description:
 * @date 2019-04-24 13:49
 */
@Data
public class QiniuRestResult<T> {

    private String error;
    private T result;


    public QiniuRestResult(){

    }
    public QiniuRestResult(String error, T result) {
        this.error = error;
        this.result = result;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = JSONObject.fromObject(this);
        return jsonObject.toString();
    }
}
