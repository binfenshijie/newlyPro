package com.newly.vas.rs.dora.dto;

import lombok.Data;

import java.util.List;

/**
 * 七牛1:1人脸比对接口
 */
@Data
public class FaceSimResult {

    private int code;
    private String message;
    private Result result;

    @Data
    public static class Result {
        private float similarity;
        private boolean same;
        private List<Face> faces;
    }

    @Data
    public static class Face {
        private float score;
        private List<List<Integer>> pts;
    }
}
