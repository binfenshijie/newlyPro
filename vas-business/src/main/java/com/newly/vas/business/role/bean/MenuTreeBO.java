package com.newly.vas.business.role.bean;

import lombok.Data;

import java.util.List;

@Data
public class MenuTreeBO {

    private String name;
    private String appCode;
    private List<String> checked;
    private List<MenuTreeDetailBO> data;

}
