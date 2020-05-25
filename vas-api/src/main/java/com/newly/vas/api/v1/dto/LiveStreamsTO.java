package com.newly.vas.api.v1.dto;

import lombok.Data;

import java.util.List;

/**
 * 批量查询直播实时信息 Bean
 */
@Data
public class LiveStreamsTO {

    /**
     * 要查询的流名字符串数组，最大长度为100
     */
    private List<String> items;
}
