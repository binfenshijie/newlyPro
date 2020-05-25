package com.newly.vas.rs.atlab.dto;

import lombok.Data;
import net.sf.json.JSONObject;

@Data
public class AddDeviceParam {

    /*设备名称*/
    private String device;
    /*设备录制文件的生命周期天数，默认为-2 >0:自定义设置文件保存天数 0:不录制 -1:永久 -2:继承app配置*/
    private int segmentExpireDays = -2;

    @Override
    public String toString() {
        JSONObject jsonObject = JSONObject.fromObject(this);
        return jsonObject.toString();
    }
}
