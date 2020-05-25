package com.newly.vas.api.v1.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.json.JSONObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoRestResult<T> {

    private String error;
    private T result;

    @Override
    public String toString() {
        JSONObject jsonObject = JSONObject.fromObject(this);
        return jsonObject.toString();
    }


    public static void main(String[] args) {
//        test_test_01 dGVzdF90ZXN0XzAx
//        test_test_02 dGVzdF90ZXN0XzAy
      System.out.println("test_test_03");
    }
}
