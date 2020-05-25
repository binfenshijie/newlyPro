package com.newly.vas.rs.video.bean;

import com.newly.common.utils.JsonUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bingo on 2018/12/17 下午5:00.
 */
@Data
public class ChannelVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;                // 卡口名称
    private Integer number;             // 通道号
    private String channel_ip;          // 设备ip
    private String channel_port;        // 设备端口
    private String user_name;           // 用户名
    private String password;            // 密码
    private String device_index_code;   // 设备ID，唯一编号（可生成）
    private String main_rtsp;           // 主码流
    private String sub_rtsp;            // 子码流
    private Integer type;               // 设备类型：0其他, 1枪机,球机2
    private String accessKey;           // 社会化监控平台设备AK
    private String secretKey;           // 社会化监控平台设备SK
    private String channelIndex;        // 社会化监控平台设备名称

    @Override
    public String toString(){
        return JsonUtils.getJsonUtlis().object2String(this);
    }


}
   
