package com.newly.vas.rs.atlab.dto;

import lombok.Data;

import java.util.List;

/**
 * 历史时间段内的域名级别播放人数带宽
 * 查询过去一段时间内的域名级别播放人数带宽
 * 返回的列表以 5 分钟为间隔
 * https://developer.qiniu.com/pili/kb/4933/domain-level-number-statistical-interface-bandwidth
 */
@Data
public class StatPlayHistoryMoreResult extends QiniuRestResult {
    @Data
    public static class StatPlayHistoryMoreItem {
        /**
         * 这条记录的的时间戳，对齐到 5 分钟
         */
        private Integer time;
        private List<StatPlayMoreBase> values;
    }

    /**
     * 若这次请求 items 个数超过了 limit，读取到哪条记录记为 marker
     */
    private String marker;
    private List<StatPlayHistoryMoreItem> items;

}
