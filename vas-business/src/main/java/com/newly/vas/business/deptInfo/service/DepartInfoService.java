package com.newly.vas.business.deptInfo.service;

import com.newly.vas.business.deptInfo.bean.DepartInfoBO;
import com.newly.vas.business.deptInfo.bean.DepartInfoDO;
import com.newly.vas.business.deptInfo.bean.DeptParentDTO;
import com.newly.vas.business.deptInfo.bean.UserTree;
import com.newly.common.service.BaseService;
import com.newly.vas.business.deptInfo.bean.DepartInfoBO;
import com.newly.vas.business.deptInfo.bean.DepartInfoDO;
import com.newly.vas.business.deptInfo.bean.DeptParentDTO;
import com.newly.vas.business.deptInfo.bean.UserTree;
import com.newly.vas.business.deptInfo.mapper.DepartInfoMapper;


import java.util.List;

/**
 * Created by bingo on 2020/4/16 上午11:37
 */
public interface DepartInfoService extends BaseService<DepartInfoMapper, DepartInfoDO> {

    List<DepartInfoBO> buildDeptTree(DepartInfoBO parent, List<DepartInfoBO> deptBOs, String searchInfo);

    String getChildrenDept(String deptId);

    List<DepartInfoDO> getByNameOrDeptCode(String search);

    int getCount(String name);

    int saveParent(DeptParentDTO dept);

    UserTree getUserTree(String userId);

    List<DepartInfoDO> getListByMaps(String userCode);

    String getDeptId(String userCode);
}
