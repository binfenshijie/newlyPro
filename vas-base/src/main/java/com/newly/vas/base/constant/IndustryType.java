package com.newly.vas.base.constant;

/**
 * Created by bingo on 2019/2/12 4:51 PM
 */
public enum IndustryType {

    GOVERNMENT("政府部门", 1),
    INTERNET("互联网行业", 2),
    MANUFACTURE("制造业", 3);
    private String name;

    private Integer value;

    IndustryType(String name, Integer value) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
