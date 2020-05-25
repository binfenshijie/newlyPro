package com.newly.vas.rs.video.bean;

import com.newly.common.utils.JsonUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bingo on 2018/12/17 下午5:00.
 */
@Data
public class DeviceVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String  name;                       // 卡口名称
    private String  index_code;                 // 设备ID，唯一编号（可生成）
    private Integer type;                       // 设备类型（4枪机，3球机，2高点，1微卡）
    private String  device_ip;                  // 设备ip
    private String  device_port;                // 设备端口
    private String  provider;                   // 设备厂商
    private String  connect_type;               // 连接方式
    private String  user_name;                  // 用户名
    private String  password;                   // 密码
    private List<ChannelVO> channelofvideodo;   // 关联通道

    @Override
    public String toString(){
       return JsonUtils.getJsonUtlis().object2String(this);
    }

}
   
