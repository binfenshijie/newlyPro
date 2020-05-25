package com.newly.vas.api.v1.dto;

import lombok.Data;

/**
 * 创建流 Bean
 */
@Data
public class CreateStreamTO {
    /**
     * 字符串，StreamTitle 流名，必须满足 4-200个数字或字母。
     */
    private String key;
}
