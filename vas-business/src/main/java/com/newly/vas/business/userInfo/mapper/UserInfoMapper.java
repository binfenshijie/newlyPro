package com.newly.vas.business.userInfo.mapper;

import com.newly.vas.business.userInfo.bean.UserInfoDO;
import com.newly.common.mapper.BaseMapper;
import com.newly.vas.business.userInfo.bean.UserInfoDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserInfomapper
 */
public interface UserInfoMapper extends BaseMapper<UserInfoDO> {
    UserInfoDO getByName(@Param("name") String name);

    void changePassword(@Param("name")String name, @Param("password") String password);

    UserInfoDO getByPhone(@Param("phone") String phone);

    void updateLoginInfo(UserInfoDO userInfoDO);

    UserInfoDO getUserAndDept(@Param("userId") String userId);

    void batchDelete(@Param("userIds") List<String> userIds);

    void batchChangePassword(@Param("userIds") List<String> userIds, @Param("password") String password);

    void batchMove(@Param("userIds") List<String> userIds, @Param("deptId") String deptId);

    void updateMenuPermission(@Param("userId")String userId, @Param("menuCodes")String menuCodes);

    void deleteMenuPermission(String userId);

    void saveMenuPermission(@Param("userId") String userId, @Param("menuCodes")String menuCodes);

    void updateResPermission(@Param("userId")String userId, @Param("treeNodes")String treeNodes);

    void deleteResPermission(String userId);

    void saveResPermission(@Param("userId") String userId, @Param("treeNodes")String treeNodes);

    String getUserCodeName(String userCode);

    List<String> getAllUserCode();
}
