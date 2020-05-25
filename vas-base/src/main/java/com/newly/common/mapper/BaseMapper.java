package com.newly.common.mapper;

import com.newly.common.bean.BaseDO;

import java.util.List;
import java.util.Map;

/**
 * 基类 定义通用方法
 * @param <T>
 */
public interface BaseMapper<T extends BaseDO>{

    /**
     * 保存
     * @param object
     */
    public int save(T object);

    /**
     * 修改
     * @param object
     */
    public int update(T object);

    /**
     * 删除
     * @param id
     */
    public int delete(String id);

    /**
     * 根据主键获取单一对象
     * @param id
     * @return
     */
    public T getById(String id);

    /**
     * 根据map获取对象列表
     * @param params
     * @return
     */
    public List<T> getListByMap(Map<String, Object> params);

    /**
     * 根据map获取分页对象列表
     * @param params
     * @return
     */
    public List<T> getPageList(Map<String, Object> params);

    /**
     * 根据map获取对象条数
     * @param params
     * @return
     */
    public int getPageCount(Map<String, Object> params);

}
