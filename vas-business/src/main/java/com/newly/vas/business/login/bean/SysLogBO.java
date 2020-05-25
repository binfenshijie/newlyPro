package com.newly.vas.business.login.bean;

import lombok.Data;

import java.util.Date;

@Data
public class SysLogBO {

    private String createDate;
    private String remark;
    private String userName;
    private String sessionId;
}
