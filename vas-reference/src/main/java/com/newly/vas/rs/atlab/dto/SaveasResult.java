package com.newly.vas.rs.atlab.dto;

import lombok.Data;

/**
 * 视频片段收藏返回Bean
 */
@Data
public class SaveasResult {
    private String fname;
    private String persistentId;
    private int duration;
}
