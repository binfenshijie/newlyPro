package com.newly.vas.business.user.service;

import com.newly.vas.business.user.bean.UserDO;
import com.newly.vas.business.user.mapper.UserMapper;
import com.newly.common.service.BaseService;
import com.newly.common.utils.EncryptUtils;
import com.newly.vas.business.login.bean.SysLogBO;
import com.newly.vas.business.user.bean.UserDO;
import com.newly.vas.business.user.mapper.UserMapper;

import java.util.HashMap;
import java.util.List;

/**
 * Created by bingo on 2020/4/22 下午4:02.
 */
public interface UserService extends BaseService<UserMapper, UserDO> {

    UserDO getUserAndDept(String userId);

    void batchDeleteUser(List<String> userIds);

    List<String> batchSaveUser(List<UserDO> users) throws Exception;

    void saveUserWithRole(UserDO userDO, List<String> roleIds);

    void batchGrantRole(List<String> userIds, List<String> roleIds);

    void updateUserWithRole(UserDO userDO, List<String> roleIds);

    void batchChangePassword(List<String> userIds, String password);

    void batchMoveDept(List<String> userIds, String deptId);

    boolean deleteAuth(String userName, String password);

    List<UserDO> getUsersPage(String deptIds, String searchInfo, Integer pageNo
            , Integer pageSize, String orderBy, String orderType);

    Integer getUsersCount(String deptIds, String searchInfo);

    List<UserDO> getUsersList(String deptIds, String roleId, String searchInfo);

    UserDO getByName(String name);

    Integer getSumLoginTimes();

    String PWD = "123456";

    String ENCRYPT_PWD = EncryptUtils.encodeSHA256String(PWD);


    int phoneCount(HashMap<String, Object> map);

    int idCardCount(HashMap<String, Object> map);

    int nameCount(HashMap<String, Object> map);

    int emailCount(HashMap<String, Object> map);

    int updatePhone(String userId,String phone);


    int saveLog(SysLogBO sysLogBO);
}
