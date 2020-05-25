package com.newly.vas.business.role.mapper;

import com.newly.vas.business.role.bean.*;
import com.newly.common.bean.CommonKeyBO;
import com.newly.common.mapper.BaseMapper;
import com.newly.vas.business.role.bean.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * rolemapper
 */
public interface RoleMapper extends BaseMapper<RoleDO> {

    public List<RoleDO> getPageList(Map<String, Object> params);

    public int getPageCount(Map<String, Object> params);

    public int deleteList(List<String> idList);

    public int batchInsertRoleMenu(List<RoleMenuDO> roleList);

    public int batchInsertRoleResTree(List<RoleResTreeDO> roleList);

    public int batchInsertRoleUser(List<RoleUserDO> roleList);

    public List<CommonKeyBO> getRelatedUserByRoleId(HashMap<String,Object> map);

    public List<RoleDO> getRelatedRoleByUserId(HashMap<String,Object> map);

    public List<String> getRelatedMenu(Map<String, Object> params);

    public List<String> getRelatedResTreeByRoleId(String roleId);

    public int deleteRoleMenuByRoleId(List<String> roleIdList);

    public int deleteRoleResTreeByRoleId(List<String> roleIdList);

    public int deleteRoleUserByRoleId(List<String> roleIdList);

    public int deleteRoleUserByUserId(String userId);

    public int getUserCountByRoleIdList(List<String> idList);

    public int existsUserRole(RoleUserDO roleUserDO);

    List<AppDO> getListByMaps(HashMap<String, Object> stringObjectHashMap);

    List<MenuDO> getListMenu(Map<String, Object> params);

    List<ResTreeDO> getTreeListByAppCode(HashMap<Object, Object> objectObjectHashMap);

    List<String> getRelatedMenuByRoleList(Map<String, Object> params);

    List<MenuDO> getListByMapa(Map<String, Object> params);

    List<MenuDO> getMenuByList(Map<String, Object> params);

    int getcount(HashMap<String,Object> map);

    List<String> getNodeId(String roleId);

    int batchInsertRoleGatewayTree(List<RoleGateWayTreeDO> roleGateWayTreeList);

    List<String> getGateWayId(String roleId);
}
