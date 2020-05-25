package com.newly.vas.business.login.bean;

import lombok.Data;

import java.util.Date;

@Data
public class LoginInfoVO {
    /**
     * 主键
     */
//    private String id;
    /**
     * IP
     */
    private String Ip;
    /**
     * 国家
     */
    private String country;
    /**
     * 地区
     */
    private String area;
    /**
     * 省份（自治区或直辖市）
     */
    private String region;
    /**
     * 市区
     */
    private String city;
    /**
     * 地区
     */
    private String county;
    /**
     * 运营商
     */
    private String isp;
    /**
     * 国家代号
     */
    private String countryId;
    /**
     * 区号
     */
    private String areaId;
    /**
     * 省行政区划代码
     */
    private String regionId;
    /**
     * 市行政区划代码
     */
    private String cityId;
    /**
     * 县行政区划代码
     */
    private String countyId;
    /**
     * 运营商代码
     */
    private String ispId;
    /**
     * 登录时间
     */
    private Date loginTime;
    /**
     * 备注
     */
    private String remark;
}
