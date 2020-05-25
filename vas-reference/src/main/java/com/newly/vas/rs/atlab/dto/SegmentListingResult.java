package com.newly.vas.rs.atlab.dto;

import lombok.Data;

import java.util.List;

/**
 * 视频片段查询 Bean
 */
@Data
public class SegmentListingResult extends QiniuRestResult<SegmentListingResult> {
    @Data
    public class Segment {
        /**
         * 视频片段的起始时间
         */
        private int from;
        /**
         * 视频片段的结束时间
         */
        private int to;
        /**
         * 视频片段截帧文件
         */
        private String frame;
    }

    private List<Segment> items;
    /**
     * 返回的列举位置标记，作为下次列举的起点信息
     */
    private String marker;
}
