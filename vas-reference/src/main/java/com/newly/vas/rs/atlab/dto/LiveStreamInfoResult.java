package com.newly.vas.rs.atlab.dto;

import lombok.Data;

/**
 * 直播实时信息返回
 */
@Data
public class LiveStreamInfoResult extends QiniuRestResult {
    /**
     * 帧率信息
     */
    @Data
    public static class FrameRate {
        // 当前音频帧率
        private Integer audio;
        // 当前视频帧率。
        private Integer video;
        // 当前数据帧率
        private Integer data;
    }

    // 推流开始的时间
    private Integer startAt;
    // 主播的IP
    private String clientIP;
    // 当前码率
    private Integer bps;
    // 当前帧率
    private FrameRate fps;
}
