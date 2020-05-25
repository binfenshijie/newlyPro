package com.newly.vas.rs.atlab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.sf.json.JSONObject;

@Data
public class FaceAddParam {

    public FaceAddParam(Data...datas) {
        this.data = datas;
    }

    private Data[] data;

    @lombok.Data
    @AllArgsConstructor
    public static class Data{

        private String uri;

        private Attribute attribute;

        @lombok.Data
        @AllArgsConstructor
        public static class Attribute{

            private String id;
            private String name;
            private String mode;
            private String desc;

        }

    }

    @Override
    public String toString() {
        JSONObject object = JSONObject.fromObject(this);
        return object.toString();
    }
}
