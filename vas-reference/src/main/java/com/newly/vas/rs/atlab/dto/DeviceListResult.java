package com.newly.vas.rs.atlab.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeviceListResult extends QiniuRestResult<DeviceListResult> {

    @Data
    public static class BaseDevice {
        private String device;
        private String segmentExpireDays;
        private Integer state;
        private Integer updatedAt;
        private Integer createdAt;
        private Integer activedAt;
        private Integer loginAt;
        private String remoteIp;
        private Integer uploadMode;
        private String activedSn;
        private Integer sdcardRotateValue;
        private Integer type;
    }

    private List<BaseDevice> items;
    private String marker;
}
