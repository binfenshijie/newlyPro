package com.newly.vas.rs.dora.dto;


import lombok.Data;
import net.sf.json.JSONObject;
@Data
public class FaceSimParam {

    public FaceSimParam(Data data1, Data data2) {
        this.data[0] = data1;
        this.data[1] = data2;
    }

    private Data[] data = new Data[2];

    @lombok.Data
    public static class Data {

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
