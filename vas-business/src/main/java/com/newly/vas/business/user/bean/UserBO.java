package com.newly.vas.business.user.bean;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by bingo on 2020/4/16 下午6:30
 */
@Data
@ToString
public class UserBO extends UserDO {

    private List<String> userIds;

    private List<String> roleIds;

    private List<String> deptIds;
}
