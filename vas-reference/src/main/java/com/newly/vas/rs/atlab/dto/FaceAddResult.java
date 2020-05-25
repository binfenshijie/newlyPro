package com.newly.vas.rs.atlab.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by bingo on 2018/8/29 下午6:54
 * /v1/face/group/<id>/add
 */
@Data
public class FaceAddResult {
    private List<String> faces;
    private List<AttributesBean> attributes;
    private List<ErrorsBean> errors;

    @Data
    public static class AttributesBean {
        /**
         * bounding_box : {"pts":[[205,400],[1213,400],[1213,535],[205,535]],"score":0.998}
         */

        private BoundingBoxBean bounding_box;

        private FaceQuality face_quality;

        @Data
        public static class BoundingBoxBean {
            /**
             * pts : [[205,400],[1213,400],[1213,535],[205,535]]
             * score : 0.998
             */

            private double score;
            private List<List<Integer>> pts;
        }

        @Data
        public static class FaceQuality{
            private String orientation;
            private String quality;
        }
    }

    @Data
    public static class ErrorsBean {
        /**
         * code : xxx
         * message : xxx
         */

        private Integer code;
        private String message;

    }
}
