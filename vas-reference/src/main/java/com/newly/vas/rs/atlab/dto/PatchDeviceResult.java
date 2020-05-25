package com.newly.vas.rs.atlab.dto;

import lombok.Data;

/**
 * 更新社会化监控上的设备信息返回参数
 */
@Data
public class PatchDeviceResult extends QiniuRestResult<PatchDeviceResult> {

    /**
     * 设备名称
     */
    private String device;
    /**
     * 设备是否在线，设备在线才会出现此字段
     */
    private Long loginAt;
    /**
     * 设备录制文件的生命周期天数
     * >0:自定义设置文件保存天数
     * 0:不录制
     * -1:永久
     * -2:继承app配置
     */
    private Integer segmentExpireDays;

    /**
     * 远程IP
     */
    private String remoteIp;
    /**
     * 视频文件上传模式，默认为-1
     * -1：遵循app配置
     * 0:遵循视频采集端配置
     * 1:强制24小时上传
     * 2:强制关闭上传
     */
    private Integer uploadMode;

    /**
     * 设备激活状态，1表示被禁用, 0表示已启用
     */
    private String state;
    /**
     * 设备首次激活时间
     */
    private Long activedAt;
    /**
     * 设备创建时间
     */
    private Long createdAt;
    /**
     * 设备更新时间
     */
    private Long updatedAt;

    private Integer sdcardRotateValue;

    private int type;

    private int licenseMode;
}
