package com.newly.vas.business.dept.service;

import com.newly.vas.business.dept.bean.DepartmentBO;
import com.newly.vas.business.dept.bean.DepartmentDO;
import com.newly.common.service.BaseService;
import com.newly.vas.business.dept.bean.DepartmentBO;
import com.newly.vas.business.dept.bean.DepartmentDO;
import com.newly.vas.business.dept.mapper.DepartmentMapper;

import java.util.List;

/**
 * Created by bingo on 2020/4/16 上午11:37
 */
public interface DepartmentService extends BaseService<DepartmentMapper, DepartmentDO> {

    List<DepartmentBO> buildDeptTree(DepartmentBO parent, List<DepartmentBO> deptBOs, String searchInfo);

    String getChildrenDept(String deptId);

    List<DepartmentDO> getByNameOrDeptCode(String search);
}
