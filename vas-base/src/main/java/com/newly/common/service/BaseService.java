package com.newly.common.service;

import com.newly.common.bean.BaseDO;
import com.newly.common.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface BaseService<M extends BaseMapper,T extends BaseDO> {

    /**
     * 获取mapper对象操作数据库
     * @return
     */
    public M getDAO();

    /**
     * 保存
     * @param object
     */
    public boolean save(T object);

    /**
     * 修改
     * @param object
     */
    public boolean update(T object);

    /**
     * 删除
     * @param id
     */
    public boolean delete(String id);

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
