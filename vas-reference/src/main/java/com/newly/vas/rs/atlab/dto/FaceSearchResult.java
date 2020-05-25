package com.newly.vas.rs.atlab.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by bingo on 2018/8/29 下午5:57
 * /v1/face/groups/search
 */
@Data
public class FaceSearchResult extends RestResult<FaceSearchResult.Result> {

    @Data
    public static class Result {
        /**
         * faces : [{"bounding_box":{"pts":[[188,127],[348,127],[348,349],[188,349]],"score":0.9999807},"faces":[{"bounding_box":{"pts":[[198,47],[279,47],[279,172],[198,172]],"score":0.99999535},"desc":"a female politican","group":"4","id":"1101","name":"anne","review":false,"score":0.78255147}]},{"bounding_box":{"pts":[[558,196],[720,196],[720,433],[558,433]],"score":0.9999678},"faces":[{"bounding_box":{"pts":[[336,53],[628,53],[628,475],[336,475]],"score":0.99999905},"desc":"a Hollywood star","group":"4","id":"1102","name":"jessica","review":false,"score":0.47173265}]}]
         * review : false
         */

        private boolean review;
        private List<FacesX> faces;

        @Data
        public static class FacesX {
            /**
             * bounding_box : {"pts":[[188,127],[348,127],[348,349],[188,349]],"score":0.9999807}
             * faces : [{"bounding_box":{"pts":[[198,47],[279,47],[279,172],[198,172]],"score":0.99999535},"desc":"a female politican","group":"4","id":"1101","name":"anne","review":false,"score":0.78255147}]
             */

            private BoundingBox bounding_box;
            private List<Faces> faces;

            @Data
            public static class BoundingBox {
                /**
                 * pts : [[188,127],[348,127],[348,349],[188,349]]
                 * score : 0.9999807
                 */

                private double score;
                private List<List<Integer>> pts;
            }

            @Data
            public static class Faces {
                /**
                 * bounding_box : {"pts":[[198,47],[279,47],[279,172],[198,172]],"score":0.99999535}
                 * desc : a female politican
                 * group : 4
                 * id : 1101
                 * name : anne
                 * review : false
                 * score : 0.78255147
                 */

                private BoundingBoxX bounding_box;
                private String desc;
                private String group;
                private String id;
                private String name;
                private boolean review;
                private double score;
                private FaceQuality face_quality;

                @Data
                public static class BoundingBoxX {
                    /**
                     * pts : [[198,47],[279,47],[279,172],[198,172]]
                     * score : 0.99999535
                     */

                    private double score;
                    private List<List<Integer>> pts;

                }
            }

            @Data
            public static class FaceQuality{
                private String orientation;
                private String quality;
            }
        }
    }
}