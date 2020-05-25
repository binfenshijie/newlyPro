package com.newly.common.service.impl;

import com.newly.common.bean.BaseDO;
import com.newly.common.mapper.BaseMapper;
import com.newly.common.service.BaseService;

import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl<M extends BaseMapper,T extends BaseDO> implements BaseService<M,T> {

    @Override
    public abstract M getDAO();

    @Override
    public boolean save(T object) {
        return this.getDAO().save(object) > 0;
    }

    @Override
    public boolean update(T object) {
        return this.getDAO().update(object) > 0;
    }

    @Override
    public boolean delete(String id) {
        return this.getDAO().delete(id) > 0;
    }

    @Override
    public T getById(String id) {
        return (T)this.getDAO().getById(id);
    }

    @Override
    public List<T> getListByMap(Map params) {
        return this.getDAO().getListByMap(params);
    }

    @Override
    public List<T> getPageList(Map<String, Object> params){
        return this.getDAO().getPageList(params);
    }

    /**
     * 根据map获取对象条数
     * @param params
     * @return
     */
    public int getPageCount(Map<String, Object> params){
        return this.getDAO().getPageCount(params);
    }

}
