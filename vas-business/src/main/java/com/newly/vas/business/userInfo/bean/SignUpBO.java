package com.newly.vas.business.userInfo.bean;

import lombok.Data;

/**
 * Created by bingo on 2019/1/29 7:06 PM
 */
@Data
public class SignUpBO {

    private UserInfoDO userInfoDO;

    private String smsCaptcha;

}
