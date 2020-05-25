package com.newly.vas.rs.atlab.dto;

import lombok.Data;

@Data
public class KodoMetadata {

    /**
     * 资源名。
     */
    private String key;
    /**
     * 上传时间，单位：100纳秒，其值去掉低七位即为Unix时间戳。
     */
    private long putTime;
    /**
     * 文件的HASH值，使用hash值算法计算。
     */
    private String hash;
    /**
     * 资源内容的大小，单位：字节。
     */
    private int fsize;
    /**
     * 资源的 MIME 类型。
     */
    private String mimeType;
    /**
     * 资源内容的唯一属主标识，请参考上传策略 (PutPolicy)。
     */
    private String endUser;
    /**
     * 资源的存储类型，1 表示低频存储，0表示普通存储。
     */
    private int type;
    /**
     * 文件的存储状态，即禁用状态和启用状态间的的互相转换，0表示启用，1表示禁用，请参考：文件状态。
     */
    private int status;

    private String md5;

}
