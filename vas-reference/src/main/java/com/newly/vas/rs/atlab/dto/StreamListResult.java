package com.newly.vas.rs.atlab.dto;

import lombok.Data;

import java.util.List;

@Data
public class StreamListResult extends QiniuRestResult {
    @Data
    public static class StreamListBase {
        private String key;
    }

    private List<StreamListBase> items;
    private String marker;
}
