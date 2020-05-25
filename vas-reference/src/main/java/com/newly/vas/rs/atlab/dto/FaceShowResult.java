package com.newly.vas.rs.atlab.dto;

import lombok.Data;

/**
 * Created by bingo on 2018/8/29 下午7:01
 * /v1/face/group/<id>
 */
@Data
public class FaceShowResult extends RestResult<FaceShowResult.ResultBean>{

    /**
     * code : 0
     * message :
     * result : [{"id":"<id>","value":{"name":"xx"}},"..."]
     */

    public static class ResultBean {
        /**
         * id : <id>
         * value : {"name":"xx"}
         */

        private String id;
        private ValueBean value;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public ValueBean getValue() {
            return value;
        }

        public void setValue(ValueBean value) {
            this.value = value;
        }

        public static class ValueBean {
            /**
             * name : xx
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
