package com.newly.vas.rs.atlab.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by bingo on 2018/8/29 下午6:46
 * /v1/face/sim
 */
@Data
public class FaceSimResultBean extends RestResult<FaceSimResultBean.ResultBean>{

    public static class ResultBean {
        /**
         * faces : [{"score":0.987,"pts":[[225,195],[351,195],[351,389],[225,389]]},{"score":0.997,"pts":[[225,195],[351,195],[351,389],[225,389]]}]
         * similarity : 0.87
         * same : true
         */

        private double similarity;
        private boolean same;
        private List<FacesBean> faces;

        public double getSimilarity() {
            return similarity;
        }

        public void setSimilarity(double similarity) {
            this.similarity = similarity;
        }

        public boolean isSame() {
            return same;
        }

        public void setSame(boolean same) {
            this.same = same;
        }

        public List<FacesBean> getFaces() {
            return faces;
        }

        public void setFaces(List<FacesBean> faces) {
            this.faces = faces;
        }

        public static class FacesBean {
            /**
             * score : 0.987
             * pts : [[225,195],[351,195],[351,389],[225,389]]
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
}
