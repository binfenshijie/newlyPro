package com.newly.vas.rs.atlab.dto;

import lombok.Data;

import java.util.List;

/**
 * 域名级别实时播放人数带宽
 * https://developer.qiniu.com/pili/kb/4933/domain-level-number-statistical-interface-bandwidth
 */
@Data
public class StatPlayMoreResult  extends QiniuRestResult {

    @Data
    public static class StatPlayMoreTotal {
        /**
         * 总的播放人数
         */
        private Integer count;
        /**
         * 总的播放带宽，单位 bps
         */
        private Integer bandwidth;
    }

    private StatPlayMoreTotal total;
    private List<StatPlayMoreBase> items;

}
