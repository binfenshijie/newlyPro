package com.newly.vas.rs.atlab.dto;

import lombok.Data;

/**
 * 帧率信息
 */
@Data
public class FrameRate {
    // 当前音频帧率
    private Integer audio;
    // 当前视频帧率。
    private Integer video;
    // 当前数据帧率
    private Integer data;
}