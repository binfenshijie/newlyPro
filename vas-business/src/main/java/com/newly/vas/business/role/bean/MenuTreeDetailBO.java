package com.newly.vas.business.role.bean;

import lombok.Data;

import java.util.List;

@Data
public class MenuTreeDetailBO {

    public MenuTreeDetailBO(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public MenuTreeDetailBO(String id, String label, List<MenuTreeDetailBO> children) {
        this.id = id;
        this.label = label;
        this.children = children;
    }

    private String id;
    private String label;
    private List<MenuTreeDetailBO> children;

}
