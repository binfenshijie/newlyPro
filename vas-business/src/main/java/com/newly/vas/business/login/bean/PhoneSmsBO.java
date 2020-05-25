package com.newly.vas.business.login.bean;

import lombok.Data;

/**
 * Created by bingo on 2019/1/30 10:44 AM
 */
@Data
public class PhoneSmsBO {
    private int uid ;
    private String phone_number;
    private String message;
}
