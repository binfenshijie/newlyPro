package com.newly.vas.business.login.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.newly.common.bean.BaseDO;
import lombok.Data;

import java.util.Date;

/**
 * 获取用户登录信息
 */
@Data
public class LoginInfoDO extends BaseDO {

    /**
     * 主键
     */
    private String id;
    /**
     * 用户账号
     */
    private String userName;
    /**
     * user_code表关联键
     */
    private String userCode;

    /**
     * SESSION_ID
     */
    private String sessionId;
    /**
     * IP
     */
    @JsonProperty("ip")
    private String Ip;
    /**
     * 国家
     */
    @JsonProperty("country")
    private String country;
    /**
     * 地区
     */
    @JsonProperty("area")
    private String area;
    /**
     * 省份（自治区或直辖市）
     */
    @JsonProperty("region")
    private String region;
    /**
     * 市区
     */
    @JsonProperty("city")
    private String city;
    /**
     * 地区
     */
    @JsonProperty("county")
    private String county;
    /**
     * 运营商
     */
    @JsonProperty("isp")
    private String isp;
    /**
     * 国家代号
     */
    @JsonProperty("country_id")
    private String countryId;
    /**
     * 区号
     */
    @JsonProperty("area_id")
    private String areaId;
    /**
     * 省行政区划代码
     */
    @JsonProperty("region_id")
    private String regionId;
    /**
     * 市行政区划代码
     */
    @JsonProperty("city_id")
    private String cityId;
    /**
     * 县行政区划代码
     */
    @JsonProperty("county_id")
    private String countyId;
    /**
     * 运营商代码
     */
    @JsonProperty("isp_id")
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
