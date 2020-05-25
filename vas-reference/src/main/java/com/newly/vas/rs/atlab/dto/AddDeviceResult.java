package com.newly.vas.rs.atlab.dto;

import lombok.Data;

@Data
public class AddDeviceResult {

    private String device;
    private String uploadMode;
    private int segmentExpireDays;
    private long createdAt;
    private long updatedAt;
    private long activedAt;
    private long loginAt;
    private int state;

}
