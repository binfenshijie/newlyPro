package com.newly.vas.rs.atlab.dto;

import java.util.List;

public class StreamInfoResult extends QiniuRestResult {
    //"createdAt": <CreatedAt>, // Unix Time
    private Integer createdAt;
    //"updatedAt": <UpdatedAt>, // Unix Time，更新流配置时会自动更新这个时间
    private Integer updatedAt;
    //"expireAt": <ExpireAt>, // Unix Time，过期时间
    private Integer expireAt;
    //"converts": ["<Profile1>", "<Profile2>"], // 流的转码规格
    private List<String> converts;
    //"disabledTill": <DisabledTill> // 禁用的结束时间，-1 表示永久禁用
    private Integer disabledTill;

}
