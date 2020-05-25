package com.newly.vas.rs.atlab.dto;

import lombok.Data;
import net.sf.json.JSONObject;

@Data
public class FaceSearchParam {

    public FaceSearchParam(Data data, Params params) {
        this.data = data;
        this.params = params;
    }

    private Data data;

    private Params params;

    @lombok.Data
    public static class Data {

        public Data(String uri) {
            this.uri = uri;
        }

        private String uri;

    }

    @lombok.Data
    public static class Params {

        public Params(String[] groups, Integer limit, Double threshold, Boolean use_quality) {
            this.groups = groups;
            this.limit = limit;
            this.threshold = threshold;
            this.use_quality = use_quality;
        }

        private String[] groups;
        private Integer limit;
        private Double threshold;
        private Boolean use_quality;

    }

    @Override
    public String toString() {
        JSONObject object = JSONObject.fromObject(this);
        return object.toString();
    }
}
