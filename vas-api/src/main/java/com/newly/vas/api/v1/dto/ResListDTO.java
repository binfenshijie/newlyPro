package com.newly.vas.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResListDTO {
    /**
     * 通道
     */
    @Data
    public static class ChannelBase {
        /**
         * 通道名称
         */
        private String name;
        /**
         * 通道号
         */
        private Integer number;
        /**
         * 通道类型
         */
        private Integer type;
        /**
         * 通道是否在线（1:在线，0:离线）
         */
        private Integer isOnline;
    }

    /**
     * 设备
     */
    @Data
    public static class DeviceBase {
        /**
         * 设备ID
         */
        @JsonIgnore
        private String id;
        /**
         * 设备编号
         */
        @JsonProperty("deviceID")
        private String deviceIndex;
        /**
         * 所属网关
         */
        @JsonIgnore
        private String gatewayId;
        /**
         * 设备名称
         */
        private String name;
        /**
         * 通道类型
         */
        private Integer type;
        /**
         * 设备厂家
         */
        private Integer firm;
        /**
         * 设备是否在线（1:在线，0:离线）
         */
        private Integer isOnline;
        /**
         * 通道列表
         */
        private List<ChannelBase> channels;

    }

    /**
     * 网关ID
     */
    @JsonIgnore
    private String id;
    /**
     * 网关编号
     */
    @JsonProperty("gatewayID")
    private String gatewayIndex;
    /**
     * 网关名称
     */
    private String name;
    /**
     * 网关是否在线（1在线，0离线）
     */
    private Integer isOnline;
    /**
     * 设备列表
     */
    private List<DeviceBase> devices;
}