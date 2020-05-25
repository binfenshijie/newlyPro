package com.newly.vas.rs.atlab.dto;

import lombok.Data;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * 更新社会化监控上的设备信息请求参数
 */
@Data
public class PatchDeviceParam {

    private List<PatchInnerParam> operations;

    @Data
    public static class PatchInnerParam {
        public PatchInnerParam() {
        }

        private String op;
        private String key;
        private Integer value;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = JSONObject.fromObject(this);
        return jsonObject.toString();
    }
}
