package com.newly.vas.business.user.mapper;

import com.newly.common.mapper.BaseMapper;
import com.newly.vas.business.login.bean.SysLogBO;
import com.newly.vas.business.user.bean.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bingo on 2020/4/13 下午4:29
 */
public interface UserMapper extends BaseMapper<UserDO> {

    UserDO getUserAndDept(@Param("userId") String userId);

    UserDO getByName(@Param("name") String userName);

    void batchDelete(@Param("userIds") List<String> userIds);

    void batchChangePassword(@Param("userIds") List<String> userIds, @Param("password") String password);

    void batchMove(@Param("userIds") List<String> userIds, @Param("deptId") String deptId);

    void batchSave(@Param("users") List<UserDO> users);

    List<UserDO> getUsersByDeptIdAndRoleId(Map<String, Object> params);

    void updateLoginInfo(UserDO user);

    Integer getSumLoginTimes();

    int phoneCount(HashMap<String, Object> map);
    int idCardCount(HashMap<String, Object> map);
    int nameCount(HashMap<String, Object> map);
    int emailCount(HashMap<String, Object> map);

    int updatePhone(@Param("userId") String userId,@Param("phone") String phone);

    int saveLog(SysLogBO sysLogBO);
}
