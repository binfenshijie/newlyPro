package com.newly.vas.rs.atlab.dto;

import lombok.Data;

@Data
public class StatPlayMoreBase {
    /**
     * 流名，形如 rtmp://xxxx/dctest/test中的test
     */
    private String streamTitle;
    /**
     * 播放域名
     */
    private String domain;
    /**
     * 播放类型，有三种播放类型: rtmp、hls、flv
     */
    private String type;
    /**
     * 这路流的播放人数
     */
    private Integer count;
    /**
     * 这路流的播放带宽，单位 bps
     */
    private Integer bandwidth;
}