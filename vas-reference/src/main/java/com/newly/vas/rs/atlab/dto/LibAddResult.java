package com.newly.vas.rs.atlab.dto;

import java.util.List;

/**
 * Created by bingo on 2018/8/29 下午6:51
 * /v1/face/group/<id>/new
 */
public class LibAddResult {


    /**
     * error : group already exist
     */

    private String error;
    private List<AttributesBean> attributes;
    private List<ErrorsBean> errors;
    private List<String> faces;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<AttributesBean> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributesBean> attributes) {
        this.attributes = attributes;
    }

    public List<ErrorsBean> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorsBean> errors) {
        this.errors = errors;
    }

    public List<String> getFaces() {
        return faces;
    }

    public void setFaces(List<String> faces) {
        this.faces = faces;
    }

    public static class AttributesBean {
        /**
         * bounding_box : {"pts":[[198,47],[279,47],[279,172],[198,172]],"score":0.99999535}
         */

        private BoundingBoxBean bounding_box;

        public BoundingBoxBean getBounding_box() {
            return bounding_box;
        }

        public void setBounding_box(BoundingBoxBean bounding_box) {
            this.bounding_box = bounding_box;
        }

        public static class BoundingBoxBean {
            /**
             * pts : [[198,47],[279,47],[279,172],[198,172]]
             * score : 0.99999535
             */

            private double score;
            private List<List<Integer>> pts;

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public List<List<Integer>> getPts() {
                return pts;
            }

            public void setPts(List<List<Integer>> pts) {
                this.pts = pts;
            }
        }
    }

    public static class ErrorsBean {
        /**
         * code : xxx
         * message : xxx
         */

        private Integer code;
        private String message;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
