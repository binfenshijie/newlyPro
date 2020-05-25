package com.newly.vas.rs.atlab.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeviceKeysResult {

    private String device; // 设备名称

    private List<KeysInnerClass> keys; // 设备密钥对，初始时只有一对密钥

    @Data
    public static class KeysInnerClass {

        public KeysInnerClass() {
        }

        private String accessKey; // 设备端的 accessKey
        private String secretKey; // 设备端的 secretKey
        private int state;  // 密钥对状态，1表示被禁用,0表示已启用
        private long createdAt;
    }
}
