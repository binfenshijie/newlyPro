package com.newly.vas.rs.atlab.dto;

import lombok.Data;

import java.util.List;

/**
 * 对象存储资源列表返回Bean
 */
@Data
public class KodoListResult extends QiniuRestResult<KodoListResult> {
    /**
     * 返回条目的数组。不能用来判断是否还有剩余条目。
     */
    private List<KodoMetadata> items;

    /**
     * 有剩余条目则返回非空字符串，作为下一次列举的参数传入。
     * 如果没有剩余条目则返回空字符串。
     */
    private String marker;
    /**
     * 返回目录名的数组，如没有指定delimiter参数则不输出。
     */
    private String[] commonPrefixes;
}
