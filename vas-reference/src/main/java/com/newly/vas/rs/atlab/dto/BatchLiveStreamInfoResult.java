package com.newly.vas.rs.atlab.dto;

import lombok.Data;

import java.util.List;

@Data
public class BatchLiveStreamInfoResult extends QiniuRestResult {
    /**
     * 帧率信息
     */
    @Data
    public static class BaseLiveStreamInfoResult {
        // 流名称
        private String key;
        // 推流开始的时间
        private Integer startAt;
        // 主播的IP
        private String clientIP;
        // 当前码率
        private Integer bps;
        // 当前帧率
        private FrameRate fps;
    }

    List<BaseLiveStreamInfoResult> items;

}
