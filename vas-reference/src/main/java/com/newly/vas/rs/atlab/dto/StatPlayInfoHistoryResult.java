package com.newly.vas.rs.atlab.dto;

import lombok.Data;

import java.util.List;

/**
 * 获取历史时间段内的播放人数带宽返回
 */
@Data
public class StatPlayInfoHistoryResult extends QiniuRestResult<StatPlayInfoHistoryResult> {

    @Data
    public static class StatPlayInfoHistoryBase {
        private Integer time;
        private String key;
//        private StatPlayBase stats;
    }

    private String marker;
    private List<StatPlayInfoHistoryBase> items;
}
