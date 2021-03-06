package com.newly.vas.business.role.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuDTO {

    private String id;
    private String name;
    private String menuCode;
    private String pathName;
    private String icon;
    private String type;
    private List<MenuDTO> children = new ArrayList<>();
}
