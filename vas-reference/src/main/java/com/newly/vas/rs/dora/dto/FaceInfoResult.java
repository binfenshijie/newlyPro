package com.newly.vas.rs.dora.dto;

import lombok.Data;

import java.util.List;

@Data
public class FaceInfoResult {
    private String name;
    private String desc;
    private FaceQuality face_quality;
    private BoundingBox bounding_box;

    @Data
    public static class FaceQuality {
        private String orientation;
        private String quality;
    }

    @Data
    public static class BoundingBox {
        private Double score;
        private List<List<Integer>> pts;
    }
}
