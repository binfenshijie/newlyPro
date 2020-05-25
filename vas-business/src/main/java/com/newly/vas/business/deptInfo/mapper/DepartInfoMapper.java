package com.newly.vas.business.deptInfo.mapper;

import com.newly.vas.business.deptInfo.bean.DepartInfoDO;
import com.newly.vas.business.deptInfo.bean.DeptParentDTO;
import com.newly.vas.business.deptInfo.bean.UserTree;
import com.newly.common.mapper.BaseMapper;
import com.newly.vas.business.deptInfo.bean.DepartInfoDO;
import com.newly.vas.business.deptInfo.bean.DeptParentDTO;
import com.newly.vas.business.deptInfo.bean.UserTree;

import java.util.List;

/**
 * Created by bingo on 2020/4/16 上午11:38
 */
public interface DepartInfoMapper extends BaseMapper<DepartInfoDO> {

    String getChildrenDept(String deptId);

    List<DepartInfoDO> getByNameOrDeptCode(String search);

    int getCount(String name);

    int saveParent(DeptParentDTO dept);

    UserTree getUserTree(String userId);

    List<DepartInfoDO> getUserCode(String userCode);

    String getDeptId(String userCode);
}

