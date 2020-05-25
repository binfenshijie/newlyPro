package com.newly.vas.business.role.service.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.newly.vas.business.role.bean.*;
import com.newly.vas.business.role.service.RoleService;
import com.newly.common.bean.CommonKeyBO;
import com.newly.common.service.impl.BaseServiceImpl;
import com.newly.common.utils.CommonUtils;
import com.newly.vas.business.role.bean.*;
import com.newly.vas.business.role.mapper.RoleMapper;
import com.newly.vas.business.role.service.RoleService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * roleservice实现
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper,RoleDO> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleMapper getDAO() {
        return roleMapper;
    }

    @Override
    public boolean deleteList(String[] roleIds) {
        List<String> idList = Arrays.asList(roleIds);
        this.roleMapper.deleteRoleMenuByRoleId(idList);
        this.roleMapper.deleteRoleResTreeByRoleId(idList);
        this.roleMapper.deleteRoleUserByRoleId(idList);
        return this.roleMapper.deleteList(idList) > 0 ? true : false;
    }

    @Override
    public int getUserCountByRoleIdList(String[] roleIds) {
        List<String> idList = Arrays.asList(roleIds);
        return this.roleMapper.getUserCountByRoleIdList(idList);
    }

    @Override
    public List<RoleDO> getRoleListByName(String name,String userCode, Integer pageNo, Integer pageSize, String orderBy, String orderType,String userId){
        Map<String,Object> params = new HashMap<>();
        params.put("name",name);
        params.put("userCode",userCode);
        params.put("userId",userId);
        int offset = CommonUtils.calcOffset(pageNo,pageSize);
        params.put("offset",offset);
        params.put("pageSize",pageSize);
        params.put("orderBy",orderBy);
        params.put("orderType",orderType);
        return this.roleMapper.getPageList(params);
    }

    @Override
    public List<RoleDO> getRoleListByName(String name,String userCode,String userId){
        Map<String,Object> params = new HashMap<>();
        params.put("name",name);
        params.put("userCode",userCode);
        params.put("userId",userId);
        return this.roleMapper.getListByMap(params);
    }

    @Override
    public int getRoleCountByName(String name,String userCode,String userId){
        Map<String,Object> params = new HashMap<>();
        params.put("name",name);
        params.put("userCode",userCode);
        params.put("userId",userId);
        return this.roleMapper.getPageCount(params);
    }

    @Transactional
    @Override
    public boolean saveOrUpdateRole(RoleBO roleBO){

        //创建角色
        RoleDO role = new RoleDO();
        role.setName(roleBO.getName());
        role.setRemark(roleBO.getRemark());
        if (roleBO.getId() == null || "".equals(roleBO.getId())){
            role.setId(CommonUtils.generateRandomUUID());
            role.setCreateUserId(roleBO.getCreateUserId());
            role.setUserCode(roleBO.getUserCode());
            this.roleMapper.save(role);
        }else{
            //更新角色名和remark
            role.setId(roleBO.getId());
            this.roleMapper.update(role);

            List<String> roleIdList = ImmutableList.of(role.getId());
//            List<String> roleIdLists=new ArrayList<String>(Collections.singleton(role.getId()));
            //清除之前的角色功能关系
            this.roleMapper.deleteRoleMenuByRoleId(roleIdList);

            //清除之前的角色资源关系
            this.roleMapper.deleteRoleResTreeByRoleId(roleIdList);

            //清除之前的关联关系 重新建立
            this.roleMapper.deleteRoleUserByRoleId(roleIdList);
            //更新角色和用户的关系
            String relatedUser = roleBO.getRelatedUser();
            if(relatedUser != null && !"".equals(relatedUser)){
                List<RoleUserDO> roleUserList = new ArrayList<>();
                JSONArray userList = JSONArray.fromObject(relatedUser);
                if(userList != null && userList.size() > 0){
                    for (Iterator<String> iter = userList.iterator();iter.hasNext();){
                        String code = iter.next();
                        RoleUserDO roleUserDO = new RoleUserDO(code,role.getId());
                        int count = this.roleMapper.existsUserRole(roleUserDO);
                        if (count == 0) {
                            roleUserList.add(roleUserDO);
                        }
                    }
                    //批量插入角色资源中间表
                    if (roleUserList != null && roleUserList.size() > 0){
                        this.roleMapper.batchInsertRoleUser(roleUserList);
                    }
                }
            }
        }

        //建立角色和功能权限的关系
        String menu = roleBO.getMenu();
        if (menu != null && !"".equals(menu)){
            List<RoleMenuDO> roleMenuList = new ArrayList<>();
            JSONArray menuList = JSONArray.fromObject(menu);
            if(menuList != null && menuList.size() > 0){
                for (Iterator<JSONObject> iter = menuList.iterator();iter.hasNext();){
                    JSONObject obj = iter.next();
                    String appCode = obj.getString("appCode");
                    JSONArray codes = obj.getJSONArray("checked");
                    if(codes != null && codes.size() > 0){
                        for(Iterator<String> codeIter = codes.iterator();codeIter.hasNext();){
                            String code = codeIter.next();
                            RoleMenuDO roleMenu = new RoleMenuDO(role.getId(),appCode,code);
                            roleMenuList.add(roleMenu);
                        }
                    }
                }
                //批量插入角色功能中间表
                if (roleMenuList != null && roleMenuList.size() > 0) {
                    this.roleMapper.batchInsertRoleMenu(roleMenuList);
                }
            }
        }

        //建立角色和资源权限的关系
        String resTree = roleBO.getResTree();
        if(resTree != null && !"".equals(resTree)){
            List<RoleResTreeDO> roleResTreeList = new ArrayList<>();
            JSONArray resTreeList = JSONArray.fromObject(resTree);
            if(resTreeList != null && resTreeList.size() > 0){
                for (Iterator<String> iter = resTreeList.iterator();iter.hasNext();){
                    String code = String.valueOf(iter.next());
                    RoleResTreeDO roleResTree = new RoleResTreeDO(role.getId(),code);
                    roleResTreeList.add(roleResTree);
                }
                //批量插入角色资源中间表
                if (roleResTreeList != null && roleResTreeList.size() > 0) {
                    this.roleMapper.batchInsertRoleResTree(roleResTreeList);
                }
            }
        }

        //建立角色和网关权限的关系
       /* String gateWayTree = roleBO.getGateWayTree();
        if(gateWayTree != null && !"".equals(gateWayTree)){
            List<RoleGateWayTreeDO> roleGateWayTreeList = new ArrayList<>();
            JSONArray gateWayTreeList = JSONArray.fromObject(gateWayTree);
            if(gateWayTreeList != null && gateWayTreeList.size() > 0){
                for (Iterator<String> iter = gateWayTreeList.iterator();iter.hasNext();){
                    String code = String.valueOf(iter.next());
                    RoleGateWayTreeDO roleGateWayTree = new RoleGateWayTreeDO(role.getId(),code);
                    roleGateWayTreeList.add(roleGateWayTree);
                }
                //批量插入角色资源中间表
                if (roleGateWayTreeList != null && roleGateWayTreeList.size() > 0) {
                    this.roleMapper.batchInsertRoleGatewayTree(roleGateWayTreeList);
                }
            }
        }*/
        return true;
    }

    public List<CommonKeyBO> getRelatedUserByRoleId(String roleId,String userCode){
        HashMap<String,Object> map=new HashMap<>();
        map.put("roleId",roleId);
        map.put("userCode",userCode);
        return this.roleMapper.getRelatedUserByRoleId(map);
    }

    public List<RoleDO> getRelatedRoleByUserId(String userId,String userCode){
        HashMap<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("userCode",userCode);
        return this.roleMapper.getRelatedRoleByUserId(map);
    }

    public List<String> getRelatedMenu(String roleId, String appCode){
        Map<String,Object> params = new HashMap<>();
        params.put("roleId",roleId);
        params.put("appCode",appCode);
        return this.roleMapper.getRelatedMenu(params);
    }

    public List<String> getRelatedResTreeByRoleId(String roleId){
        return this.roleMapper.getRelatedResTreeByRoleId(roleId);
    }

    public boolean deleteRoleUserByUserId(String userId){
        return this.roleMapper.deleteRoleUserByUserId(userId) > 0 ? true : false;
    }
    public String[] getAuthTreeNode() {
		return null;
		
	}

    public List<AppDO> getListByMaps(HashMap<String, Object> stringObjectHashMap){
        return this.roleMapper.getListByMaps(stringObjectHashMap);
    }


    /**
     * 根据系统编码获取功能菜单树
     * @param appCode
     * @return
     */
//    @Override
//    public List<MenuTreeDetailBO> getTreeListByAppCodes(String appCode){
//        Map<String,Object> params = new HashMap<>();
//        params.put("appCode",appCode);
//        List<MenuDO> menuList = this.roleMapper.getListMenu(params);
//        List<MenuTreeDetailBO> treeList = this.recusionTree("-1",menuList);
//        return treeList;
//    }
    @Override
    public List<MenuTreeDetailBO> getTreeListByAppCodes(String userId, String userCode, String appCode){
        Map<String,Object> params = new HashMap<>();
        HashMap<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("userCode",userCode);
        List<RoleDO> roleList = this.roleMapper.getRelatedRoleByUserId(map);
        params.put("appCode",appCode);
        params.put("roleId",roleList.get(0).getId());
        List<MenuDO> menuList = this.roleMapper.getListMenu(params);
        List<MenuTreeDetailBO> treeList = this.recusionTree("-1",menuList);
        return treeList;
    }

    /**
     * 将一级数据整理为多级树形结构
     * @param pId
     * @param allList
     * @return
     */
    private List<MenuTreeDetailBO> recusionTree(String pId, List<MenuDO> allList){

        List<MenuTreeDetailBO> result = new ArrayList<MenuTreeDetailBO>();
        for (MenuDO tree : allList) {
            if(pId.equals(tree.getParentId())){
                MenuTreeDetailBO data = new MenuTreeDetailBO(tree.getId(),tree.getName());
                data.setChildren(recusionTree(tree.getId(),allList));
                result.add(data);
            }
        }

        return result;
    }

    private List<MenuTreeDetailBO> recusionTrees(String pId, List<ResTreeDO> allList){

        List<MenuTreeDetailBO> result = new ArrayList<MenuTreeDetailBO>();
        for (ResTreeDO tree : allList) {
            if(pId.equals(tree.getParentTreeNode())){
                MenuTreeDetailBO data = new MenuTreeDetailBO(tree.getTreeNode(),tree.getName());
                data.setChildren(recusionTrees(tree.getTreeNode(),allList));
                result.add(data);
            }
        }

        return result;
    }

    public List<MenuTreeDetailBO> getTreeListByAppCode(){
        List<ResTreeDO> resList = this.roleMapper.getTreeListByAppCode(new HashMap<>());
        List<MenuTreeDetailBO> treeList = this.recusionTrees("-1",resList);
        return treeList;
    }

    @Override
    public List<MenuDTO> operatorList(String userId,String userCode, String appCode, String menuCode) {
        List<MenuDTO> result = new ArrayList<>();
        HashMap<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("userCode",userCode);
        List<RoleDO> roleList = this.roleMapper.getRelatedRoleByUserId(map);
        Map<String,Object> params = new HashMap<>();
        if (roleList != null && roleList.size() > 0){
            //2。 根据角色列表 找到权限列表
            params.put("appCode",appCode);
            params.put("roleList",roleList);
            List<String> menuList = this.roleMapper.getRelatedMenuByRoleList(params);

            //3。 根据权限整理成约定的树形菜单并返回
            if(menuList != null && menuList.size() > 0){
//                Map<String,Object> params = new HashMap<>();
                params.put("menuCode",menuCode);
                List<MenuDO> tmpList = this.roleMapper.getListByMapa(params);
                MenuDO menu = tmpList == null || tmpList.size() != 1 ? null : tmpList.get(0);
                if (menu != null){
                    List<MenuBO> list = getOperatorByList(menuList,"0",menu.getId());
                    list.stream().forEach(t->{
                        MenuDTO bean = new MenuDTO();
                        BeanUtils.copyProperties(t,bean);
                        result.add(bean);
                    });
                }

            }
        }
        return result;
    }

    public List<MenuBO> getOperatorByList(List<String> menuList, String type, String parentId){

        Map<String,Object> params = new HashMap<>();
        params.put("menuList",menuList);
        params.put("type",type);
        if (parentId != null && !"".equals(parentId)){
            params.put("parentId",parentId);
        }
        List<MenuDO> originList = this.roleMapper.getMenuByList(params);
        List<MenuBO> result = this.recusionTree2(parentId,originList);
        return result;
    }

    /**
     * 将一级数据整理为多级树形结构
     * @param pId
     * @param allList
     * @return
     */
    private List<MenuBO> recusionTree2(String pId, List<MenuDO> allList){

        List<MenuBO> result = new ArrayList<MenuBO>();
        for (MenuDO data : allList) {
            if(pId.equals(data.getParentId())){
                MenuBO tree = new MenuBO();
                tree.setId(data.getId());
                tree.setIcon(data.getIcon());
                tree.setMenuCode(data.getMenuCode());
                tree.setName(data.getName());
                tree.setPathName(data.getPathName());
                tree.setType(data.getType());
                tree.setChildren(recusionTree2(data.getId(),allList));
                result.add(tree);
            }
        }

        return result;
    }

    @Override
    public List<String> operatorCodeList(String userId,String userCode, String appCode, String menuCode) {
        List<String> result = new ArrayList<>();
        HashMap<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("userCode",userCode);
        List<RoleDO> roleList = this.roleMapper.getRelatedRoleByUserId(map);
        Map<String,Object> params = new HashMap<>();
        if (roleList != null && roleList.size() > 0){
            //2。 根据角色列表 找到权限列表
            params.put("appCode",appCode);
            params.put("roleList",roleList);
            List<String> menuList = this.roleMapper.getRelatedMenuByRoleList(params);

            //3。 根据权限整理成约定的树形菜单并返回
            if(menuList != null && menuList.size() > 0){
//                Map<String,Object> params = new HashMap<>();
                params.put("menuCode",menuCode);
                List<MenuDO> tmpList = this.roleMapper.getListByMapa(params);
                MenuDO menu = tmpList == null || tmpList.size() != 1 ? null : tmpList.get(0);
                if (menu != null){
                    result = getMenuCodeByList(menuList,"0",menu.getId());
                }

            }
        }
        return result;
    }





    public List<MenuDTO> menuList(String userId,String userCode, String appCode) {
        List<MenuDTO> result = new ArrayList<>();
        HashMap<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("userCode",userCode);
        List<RoleDO> roleList = this.roleMapper.getRelatedRoleByUserId(map);
        Map<String,Object> params = new HashMap<>();
        if(roleList != null && roleList.size() > 0){
            params.put("appCode",appCode);
            params.put("roleList",roleList);
            //2。 根据角色列表 找到权限列表
            List<String> menuList = this.roleMapper.getRelatedMenuByRoleList(params);

            //3。 根据权限整理成约定的树形菜单并返回
            if(menuList != null && menuList.size() > 0){
                List<MenuBO> list = getMenuByList(menuList,"1",null);
                list.stream().forEach(t->{
                    MenuDTO bean = new MenuDTO();
                    BeanUtils.copyProperties(t,bean);
                    result.add(bean);
                });
            }
        }
            return result;
    }


    public List<String> menuCodeList(String userId,String userCode, String appCode) {
        List<String> result = new ArrayList<>();
        HashMap<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("userCode",userCode);
        List<RoleDO> roleList = this.roleMapper.getRelatedRoleByUserId(map);
        Map<String,Object> params = new HashMap<>();
        if(roleList != null && roleList.size() > 0){
            params.put("appCode",appCode);
            params.put("roleList",roleList);
            //2。 根据角色列表 找到权限列表
            List<String> menuList = this.roleMapper.getRelatedMenuByRoleList(params);
            if(menuList != null && menuList.size() > 0){
                result = getMenuCodeByList(menuList,"1",null);
            }
        }
        return result;
    }

    //无权限的
    public List<String> getMenuCodeByList(List<String> menuList, String type, String parentId){

        List<String> result = Lists.newArrayList();

        Map<String,Object> params = new HashMap<>();
        params.put("menuList",menuList);
        params.put("type",type);
        if (parentId != null && !"".equals(parentId)){
            params.put("parentId",parentId);
        }
        List<MenuDO> originList = this.roleMapper.getMenuByList(params);
        if (originList != null){
            result = originList.stream().map(t->t.getMenuCode()).collect(Collectors.toList());
        }
        return result;
    }

    //有权限的
    public List<MenuBO> getMenuByList(List<String> menuList, String type, String parentId){

        Map<String,Object> params = new HashMap<>();
        params.put("menuList",menuList);
        params.put("type",type);
        if (parentId != null && !"".equals(parentId)){
            params.put("parentId",parentId);
        }
        List<MenuDO> originList = this.roleMapper.getMenuByList(params);
        List<MenuBO> result = this.recusionTree2("-1",originList);
        return result;
    }



    public List<String> getNodeId(String roleId){
        List<String> noIdList = this.roleMapper.getNodeId(roleId);
        return noIdList;
    }
    public List<String> getGateWayId(String roleId){
        List<String> getGateWayId = this.roleMapper.getGateWayId(roleId);
        return getGateWayId;
    }

    public int getCount(String name,String userCode){
        HashMap<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("userCode",userCode);
        int count= roleMapper.getcount(map);
        return count;
    }
}
