package com.newly.vas.api.v1.dto;

import lombok.Data;

/**
 * 禁播流 Bean
 */
@Data
public class DisabledStreamTO {
    /**
     * 整数，Unix 时间戳，表示流禁播的结束时间，单位 s(秒)，-1 表示永久禁播。0 表示解除禁播。
     */
    private Integer disabledTill;
}
