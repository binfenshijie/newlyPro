package com.newly.vas.rs.atlab.dto;

import lombok.Data;
import net.sf.json.JSONObject;

import java.util.Arrays;
import java.util.List;

@Data
public class FaceDeleteParam {

    public FaceDeleteParam(String...faces) {
        this.faces = Arrays.asList(faces);
    }

    public FaceDeleteParam(List<String> faces) {
        this.faces = faces;
    }
    private List<String> faces;

    @Override
    public String toString() {
        JSONObject object = JSONObject.fromObject(this);
        return object.toString();
    }
}
