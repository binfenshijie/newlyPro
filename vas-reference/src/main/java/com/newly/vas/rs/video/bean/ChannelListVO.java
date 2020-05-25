package com.newly.vas.rs.video.bean;

import com.newly.common.utils.JsonUtils;
import lombok.Data;

import java.util.List;

/**
 * Created by bingo on 2019/2/26 2:12 PM
 */
@Data
public class ChannelListVO {
    private List<ChannelVO> channelList; //关联通道

    @Override
    public String toString(){
        return JsonUtils.getJsonUtlis().object2String(this);
    }

}
