package com.newly.vas.business.userInfo.service;

import com.newly.common.service.BaseService;
import com.newly.common.utils.EncryptUtils;
import com.newly.vas.business.userInfo.bean.UserInfoDO;
import com.newly.vas.business.userInfo.mapper.UserInfoMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserInfoservice
 */
public interface UserInfoService extends BaseService<UserInfoMapper,UserInfoDO> {

    UserInfoDO getByName(String name);

    void changePassword(String name, String password);

    UserInfoDO getByPhone(String phone);

    void updateLoginInfo(UserInfoDO userInfoDO);

    Integer getUsersCount(String deptIds, String searchInfo);

    UserInfoDO getUserAndDept(String userId);

    void updateMenuPermission(String userId, String menuCodes);

    void deleteMenuPermission(String userId);

    void saveMenuPermission(String userId, String menuCodes);

    void updateResPermission(String userId, String treeNodes);

    void deleteResPermission(String userId);

    void saveResPermission(String userId, String treeNodes);

    @Transactional
    void batchDeleteUser(List<String> userIds);

    void batchChangePassword(List<String> userIds, String password);

    void batchMoveDept(List<String> userIds, String deptId);

    List<UserInfoDO> getUsersList(String deptIds, String createUserId, String searchInfo,String userCode);

    String PWD = "123456";

    String ENCRYPT_PWD = EncryptUtils.encodeSHA256String(PWD);

    String getUserCodeName(String userCode);
}
