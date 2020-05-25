package com.newly.vas.business.userInfo.service.impl;

import com.google.common.collect.ImmutableMap;
import com.newly.vas.business.userInfo.bean.UserInfoDO;
import com.newly.vas.business.userInfo.mapper.UserInfoMapper;
import com.newly.vas.business.userInfo.service.UserInfoService;
import com.newly.common.service.impl.BaseServiceImpl;
import com.newly.vas.business.dept.service.DepartmentService;
import com.newly.vas.business.userInfo.bean.UserInfoDO;
import com.newly.vas.business.userInfo.mapper.UserInfoMapper;
import com.newly.vas.business.userInfo.service.UserInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

/**
 * UserInfoservice实现
 */
@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfoMapper,UserInfoDO> implements UserInfoService {

    @Autowired
    private UserInfoMapper userMapper;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public UserInfoMapper getDAO() {
        return userMapper;
    }

    @Override
    public UserInfoDO getByName(String name) {
       return userMapper.getByName(name);
    }

    @Override
    public void changePassword(String name, String password) {
        userMapper.changePassword(name, password);
    }

    @Override
    public UserInfoDO getByPhone(String phone) {
        return userMapper.getByPhone(phone);
    }

    @Override
    public void updateLoginInfo(UserInfoDO userInfoDO) {
         userMapper.updateLoginInfo(userInfoDO);
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
    public UserInfoDO getUserAndDept(String userId) {
        return userMapper.getUserAndDept(userId);
    }


    @Override
    @Transactional
    public void updateMenuPermission(String userId, String menuCodes) {
        userMapper.updateMenuPermission(userId, menuCodes);
    }

    @Override
    @Transactional
    public void deleteMenuPermission(String userId) {
        userMapper.deleteMenuPermission(userId);
    }

    @Override
    @Transactional
    public void saveMenuPermission(String userId, String menuCodes) {
        userMapper.saveMenuPermission(userId, menuCodes);
    }

    @Override
    @Transactional
    public void updateResPermission(String userId, String treeNodes) {
        userMapper.updateMenuPermission(userId, treeNodes);
    }

    @Override
    @Transactional
    public void deleteResPermission(String userId) {
        userMapper.deleteResPermission(userId);
    }

    @Override
    @Transactional
    public void saveResPermission(String userId, String treeNodes) {
        userMapper.saveMenuPermission(userId, treeNodes);
    }

    @Override
    @Transactional
    public void batchDeleteUser(List<String> userIds) {
        userMapper.batchDelete(userIds);
        userIds.stream().forEach(item -> {
            deleteMenuPermission(item);
            deleteResPermission(item);

        });
    }

    @Override
    @Transactional
    public void batchChangePassword(List<String> userIds, String password){
        if (!CollectionUtils.isEmpty(userIds)) {
            userMapper.batchChangePassword(userIds, password);
        }
    }


    @Transactional
    @Override
    public void batchMoveDept(List<String> userIds, String deptId) {
        if (!CollectionUtils.isEmpty(userIds)
                && StringUtils.isNoneEmpty(deptId)) {
            userMapper.batchMove(userIds, deptId);
        }
    }

    @Override
    public List<UserInfoDO> getUsersList(String deptIds, String createUserId, String searchInfo,String userCode) {
        String[] deptIdArray = deptIds.split(",");
        Map<String, Object> param = ImmutableMap.<String, Object>builder()
                .put("deptIds", deptIdArray)
                .put("searchInfo", searchInfo == null ? "" : searchInfo)
                .put("createUserId", createUserId == null ? "" : createUserId)
                .put("userCode", userCode)
                .build();
        return userMapper.getListByMap(param);

    }

    public  String getUserCodeName(String userCode){
        return userMapper.getUserCodeName(userCode);
    }
}
