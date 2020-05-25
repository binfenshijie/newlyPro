package com.newly.vas.business.role.service;

import com.newly.vas.business.role.bean.*;
import com.newly.common.bean.CommonKeyBO;
import com.newly.common.service.BaseService;
import com.newly.vas.business.role.bean.*;
import com.newly.vas.business.role.mapper.RoleMapper;

import java.util.HashMap;
import java.util.List;

/**
 * roleservice
 */
public interface RoleService extends BaseService<RoleMapper,RoleDO> {

    public List<RoleDO> getRoleListByName(String name,String userCode, Integer pageNo, Integer pageSize, String orderBy, String orderType,String userId);

    public List<RoleDO> getRoleListByName(String name,String userCode,String userId);

    public int getRoleCountByName(String name,String userCode,String userId);

    public boolean saveOrUpdateRole(RoleBO roleBO);

    public List<CommonKeyBO> getRelatedUserByRoleId(String roleId,String userCode);

    public List<RoleDO> getRelatedRoleByUserId(String userId,String userCode);

    public List<String> getRelatedMenu(String roleId, String appCode);

    public List<String> getRelatedResTreeByRoleId(String roleId);

    public boolean deleteRoleUserByUserId(String userId);

    public boolean deleteList(String[] roleIds);

    public int getUserCountByRoleIdList(String[] roleIds);

    public List<AppDO> getListByMaps(HashMap<String, Object> stringObjectHashMap);


      public List<MenuTreeDetailBO> getTreeListByAppCodes(String userId, String userCode, String appCode);
//    public List<MenuTreeDetailBO> getTreeListByAppCodes(String appCode);

    public List<MenuTreeDetailBO> getTreeListByAppCode();

    List<MenuDTO> operatorList(String userId, String userCode, String appCode, String menuCode);

    List<String> operatorCodeList(String userId,String userCode, String appCode, String menuCode);

    List<MenuDTO> menuList(String userId,String userCode,String appCode);

    List<String> menuCodeList(String userId,String userCode, String appCode);

    int getCount(String name,String userCode);

    List<String> getNodeId(String roleId);

    List<String> getGateWayId(String roleId);
}
