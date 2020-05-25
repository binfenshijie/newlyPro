package com.newly.vas.business.user.service.impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.newly.vas.business.role.bean.RoleUserDO;
import com.newly.vas.business.role.mapper.RoleMapper;
import com.newly.vas.business.user.bean.UserDO;
import com.newly.vas.business.user.mapper.UserMapper;
import com.newly.common.service.impl.BaseServiceImpl;
import com.newly.common.utils.CommonUtils;
import com.newly.vas.business.login.bean.SysLogBO;
import com.newly.vas.business.role.bean.RoleUserDO;
import com.newly.vas.business.role.mapper.RoleMapper;
import com.newly.vas.business.user.bean.UserDO;
import com.newly.vas.business.user.mapper.UserMapper;
import com.newly.vas.business.user.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bingo on 2020/4/22 下午4:03.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserDO> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDO getUserAndDept(String userId) {
        return userMapper.getUserAndDept(userId);
    }

    @Override
    @Transactional
    public void batchDeleteUser(List<String> userIds) {
        userMapper.batchDelete(userIds);
        userIds.stream().forEach(userId -> roleMapper.deleteRoleUserByUserId(userId));

    }

    @Override
    public List<String> batchSaveUser(List<UserDO> users) throws Exception {
        List<String> userIds = Lists.newArrayList();
        users.stream().filter(item -> StringUtils.isEmpty(item.getId()))
                .forEach(item -> item.setId(CommonUtils.generateRandomUUID()));
        users.forEach(item -> userIds.add(item.getId()));
        userMapper.batchSave(users);
        return userIds;
    }

    @Override
    @Transactional
    public void saveUserWithRole(UserDO user, List<String> roleIds) {
        if (StringUtils.isEmpty(user.getId())) {
            user.setId(CommonUtils.generateRandomUUID());
            userMapper.save(user);
        }
        List<RoleUserDO> roleUserDOS = Lists.newArrayList();
         if (!CollectionUtils.isEmpty(roleIds)) {
            roleIds.stream().forEach(roleId-> {
                RoleUserDO roleUserDO = new RoleUserDO(user.getId(), roleId);
                roleUserDOS.add(roleUserDO);
            });
            roleMapper.batchInsertRoleUser(roleUserDOS);
        }
    }

    @Override
    public void batchGrantRole(List<String> userIds, List<String> roleIds) {

        List<RoleUserDO> roleUserDOS = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(userIds) && CollectionUtils.isNotEmpty(roleIds)) {
            roleIds.stream().forEach(roleId-> {
                userIds.stream().forEach(userId -> {
                    RoleUserDO roleUserDO = new RoleUserDO(userId, roleId);
                    roleUserDOS.add(roleUserDO);
                });

            });
        }
        roleMapper.batchInsertRoleUser(roleUserDOS);
    }

    @Override
    @Transactional
    public void updateUserWithRole(UserDO userDO, List<String> roleIds) {
        userMapper.update(userDO);
        roleMapper.deleteRoleUserByUserId(userDO.getId());
        this.saveUserWithRole(userDO, roleIds);
    }

    @Override
    public void batchChangePassword(List<String> userIds, String password){
        if (!CollectionUtils.isEmpty(userIds)) {
            userMapper.batchChangePassword(userIds, password);
        }
    }


    @Override
    public void batchMoveDept(List<String> userIds, String deptId) {
        if (!CollectionUtils.isEmpty(userIds)
                && StringUtils.isNoneEmpty(deptId)) {
            userMapper.batchMove(userIds, deptId);
        }
    }

    @Override
    public boolean deleteAuth(String userName, String password) {
        UserDO user = userMapper.getByName(userName);
        if (user != null && password.equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public List<UserDO> getUsersPage(String deptIds, String searchInfo, Integer pageNo, Integer pageSize, String orderBy, String orderType) {
        int offset = CommonUtils.calcOffset(pageNo,pageSize);
        String[] deptIdArray = deptIds.split(",");
        Map<String, Object> param = ImmutableMap.<String, Object>builder()
                .put("deptIds", deptIdArray)
                .put("searchInfo", searchInfo == null ? "" : searchInfo)
                .put("offset", offset)
                .put("pageSize", pageSize)
                .put("orderBy", orderBy)
                .put("orderType", orderType)
                .build();
        return userMapper.getPageList(param);
    }

    @Override
    public Integer getUsersCount(String deptIds, String searchInfo) {
        String[] deptIdArray = deptIds.split(",");
        Map<String, Object> params = ImmutableMap.<String, Object>builder()
                .put("deptIds", deptIdArray)
                .put("searchInfo", searchInfo == null ? "" : searchInfo)
                .build();
        return userMapper.getPageCount(params);
    }

    @Override
    public List<UserDO> getUsersList(String deptIds, String roleId, String searchInfo) {
        String[] deptIdArray = deptIds.split(",");
        Map<String, Object> param = ImmutableMap.<String, Object>builder()
                .put("deptIds", deptIdArray)
                .put("searchInfo", searchInfo == null ? "" : searchInfo)
                .put("roleId", roleId == null ? "" : roleId)
                .build();
        return userMapper.getUsersByDeptIdAndRoleId(param);


    }

    @Override
    public UserDO getByName(String name) {
        return userMapper.getByName(name);
    }

    @Override
    public Integer getSumLoginTimes() {
        return userMapper.getSumLoginTimes();
    }

    @Override
    public UserMapper getDAO() {
        return this.userMapper;
    }


   public int phoneCount(HashMap<String, Object> map){
        int count= userMapper.phoneCount(map);
        return count;
   }
    public int idCardCount(HashMap<String, Object> map){
        int count= userMapper.idCardCount(map);
        return count;
    }
    public int nameCount(HashMap<String, Object> map){
        int count= userMapper.nameCount(map);
        return count;
    }
    public int emailCount(HashMap<String, Object> map){
        int count= userMapper.emailCount(map);
        return count;
    }

    public int updatePhone(String userId,String phone){
        int count= userMapper.updatePhone(userId,phone);
        return count;
    }

    public int saveLog(SysLogBO sysLogBO){
        return userMapper.saveLog(sysLogBO);
    }
}
