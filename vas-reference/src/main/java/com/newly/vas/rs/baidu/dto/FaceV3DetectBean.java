package com.newly.vas.rs.baidu.dto;

import lombok.Data;

import java.util.List;

/**
 * V3版本人脸检测javabean
 */
@Data
public class FaceV3DetectBean {
    private int error_code;
    private String error_msg;
    private long log_id;
    private long timestamp;
    private int cached;
    private Result result;

    /**
     * Result
     */
    @Data
    public static class Result {
        private int face_num;
        private List<Face_list> face_list;
    }

    /**
     * Face_list
     */
    @Data
    public static class Face_list {
        private String face_token;
        private Location location;
        private int face_probability;
        private Angle angle;
        private int age;
        private double beauty;
        private Expression expression;
        private Face_shape face_shape;
        private Gender gender;
        private Glasses glasses;
        private List<Landmark> landmark;
        private List<Landmark72> landmark72;
        private Race race;
    }

    /**
     * Rece
     */
    @Data
    public static class Race {
        private String type;
        private double probability;
    }

    /**
     * Landmark72
     */
    @Data
    public static class Landmark72 {
        private double x;
        private double y;
    }

    /**
     * Landmark
     */
    @Data
    public static class Landmark {
        private double x;
        private double y;
    }

    /**
     * Glasses
     */
    @Data
    public static class Glasses {
        private String type;
        private double probability;
    }

    /**
     * Gender
     */
    @Data
    public static class Gender {
        private String type;
        private double probability;
    }


    /**
     * Face_shape
     */
    @Data
    public static class Face_shape {
        private String type;
        private double probability;
    }

    /**
     * Expression
     */
    @Data
    public static class Expression {
        private String type;
        private double probability;
    }

    /**
     * Angle
     */
    @Data
    public static class Angle {
        private double yaw;
        private double pitch;
        private double roll;
    }

    /**
     * Location
     */
    @Data
    public static class Location {
        private double left;
        private double top;
        private int width;
        private int height;
        private int rotation;
    }
}