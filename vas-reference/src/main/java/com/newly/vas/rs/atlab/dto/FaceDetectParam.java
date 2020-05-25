package com.newly.vas.rs.atlab.dto;

import lombok.Data;
import net.sf.json.JSONObject;

@Data
public class FaceDetectParam {

    public FaceDetectParam(Data data) {
        this.data = data;
    }

    private Data data;

    @lombok.Data
    public static class Data{

        public Data(String uri) {
            this.uri = uri;
        }

        private String uri;

    }

    @Override
    public String toString() {
        JSONObject object = JSONObject.fromObject(this);
        return object.toString();
    }
}
