package com.newly.vas.business.dept.mapper;


import com.newly.vas.business.dept.bean.DepartmentDO;
import com.newly.common.mapper.BaseMapper;
import com.newly.vas.business.dept.bean.DepartmentDO;

import java.util.List;

/**
 * Created by bingo on 2020/4/16 上午11:38
 */
public interface DepartmentMapper extends BaseMapper<DepartmentDO> {

    String getChildrenDept(String deptId);

    List<DepartmentDO> getByNameOrDeptCode(String search);
}

