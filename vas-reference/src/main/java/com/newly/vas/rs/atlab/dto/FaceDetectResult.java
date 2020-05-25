package com.newly.vas.rs.atlab.dto;

import java.util.List;

/**
 * Created by bingo on 2018/8/29 下午7:12
 */
public class FaceDetectResult extends RestResult<FaceDetectResult.ResultBean> {

    /**
     * result : {"detections":[{"boundingBox":{"pts":[[652,6],[841,6],[841,262],[652,262]],"score":0.99996805}},{"boundingBox":{"pts":[[870,49],[1090,49],[1090,326],[870,326]],"score":0.9999567}},{"boundingBox":{"pts":[[307,60],[493,60],[493,263],[307,263]],"score":0.9998048}},{"boundingBox":{"pts":[[152,119],[299,119],[299,332],[152,332]],"score":0.9997826}}]}
     */

    public static class ResultBean {
        private List<DetectionsBean> detections;

        public List<DetectionsBean> getDetections() {
            return detections;
        }

        public void setDetections(List<DetectionsBean> detections) {
            this.detections = detections;
        }

        public static class DetectionsBean {
            /**
             * boundingBox : {"pts":[[652,6],[841,6],[841,262],[652,262]],"score":0.99996805}
             */

            private BoundingBoxBean boundingBox;

            public BoundingBoxBean getBoundingBox() {
                return boundingBox;
            }

            public void setBoundingBox(BoundingBoxBean boundingBox) {
                this.boundingBox = boundingBox;
            }

            public static class BoundingBoxBean {
                /**
                 * pts : [[652,6],[841,6],[841,262],[652,262]]
                 * score : 0.99996805
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
}
