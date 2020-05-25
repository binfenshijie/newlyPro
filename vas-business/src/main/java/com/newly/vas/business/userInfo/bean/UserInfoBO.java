package com.newly.vas.business.userInfo.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaiyanjun on 2019/4/8 下午9:30
 */
@Data
@ToString
public class UserInfoBO implements Serializable {

    private List<String> userIds;

    private String deptId;
}
