package com.newly.vas.business.userInfo.controller;

import com.newly.vas.business.userInfo.bean.UserInfoBO;
import com.newly.vas.business.userInfo.bean.UserInfoDO;
import com.newly.vas.business.userInfo.service.UserInfoService;
import com.newly.common.controller.BaseController;
import com.newly.common.constant.ErrorCode;
import com.newly.common.utils.CommonUtils;
import com.newly.vas.base.constant.CommonConstants;
import com.newly.vas.business.dept.service.DepartmentService;
import com.newly.vas.business.userInfo.bean.UserInfoBO;
import com.newly.vas.business.userInfo.bean.UserInfoDO;
import com.newly.vas.business.userInfo.service.UserInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserInfo controller
 */
@RestController
@RequestMapping("/userInfo/")
//@CrossOrigin
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoService userService;

    @Autowired
    private DepartmentService deptService;


    @PostMapping(value = "/password")
    public Object changePwd (@RequestParam(value = "name") String name
            , @RequestParam(value = "oldPassword") String oldPwd
            , @RequestParam(value = "newPassword") String newPwd) {
        UserInfoDO user = userService.getByName(name);
        if (oldPwd.equals(user.getPassword())) {
            userService.changePassword(name,newPwd);
            return this.resultHandler(ErrorCode.SUCCESS, "修改密码成功");
        }else {
            return this.resultHandler(ErrorCode.FAIL, "原密码错误");
        }
    }

    @PostMapping(value = "/reset")
    @ResponseBody
    public Object reset(@RequestBody List<String> userIds){
        if (!CollectionUtils.isEmpty(userIds)) {
            userService.batchChangePassword(userIds, UserInfoService.ENCRYPT_PWD);
        }
        return this.resultHandler(ErrorCode.SUCCESS, "重置密码成功 初始密码为 " + UserInfoService.PWD);
    }

    @PostMapping(value = "/move")
    @ResponseBody
    public Object move(@RequestBody UserInfoBO userParam){
        userService.batchMoveDept(userParam.getUserIds(), userParam.getDeptId());
        return this.resultHandler(ErrorCode.SUCCESS, "部门变更成功");
    }

    @GetMapping(value = "/loginInfo")
    public Object loginInfo (@RequestParam(value = "name") String name) {
        UserInfoDO user = userService.getByName(name);
        String userCodeName = userService.getUserCodeName(user.getUserCode());
        if (user !=null) {
            Map<String, Object> data = new HashMap<>();
            data.put("loginTimes", user.getLoginTimes());
            data.put("lastLoginTime", user.getLastLoginTime());
            data.put("userId", user.getId());
            data.put("userCode",user.getUserCode());
            data.put("userCodeName",userCodeName);
            data.put("userName",user.getName());
            data.put("phone",user.getPhone());
            data.put("iszuhu",user.getIszuhu());
            return this.resultHandler(ErrorCode.SUCCESS, "", data);
        }else {
            return this.resultHandler(ErrorCode.SUCCESS, "用户不存在");
        }
    }

    @PostMapping(value = "/add")
    public Object signUp (@RequestBody UserInfoDO userInfoDO, HttpServletRequest request) {
        String phone = userInfoDO.getPhone();
        String name = userInfoDO.getName();
        String userId = null;
//        if ((userId = Sessions.getCurrentUserId(request)) == null) {
//               return this.resultHandler(ErrorCode.FAIL, "auth fail");
//        }
        if (userId == null) {
            userId = "1";
        }
        userInfoDO.setCreateUserId(userId);
        if (userService.getByPhone(phone) != null) {
            return this.resultHandler(ErrorCode.FAIL, "手机号已被注册");
        }
        if (userService.getByName(name) != null) {
            return this.resultHandler(ErrorCode.FAIL, "用户名已存在");
        }
        userInfoDO.setId(CommonUtils.generateRandomUUID());
        userInfoDO.setType(0);
        userService.save(userInfoDO);
        // userService.grantPri()
        return this.resultHandler(ErrorCode.SUCCESS, "添加成功");

    }

    /**
     * 用户列表
     * @param deptId
     * @param searchInfo
     * @param include
     * @return
     */
    @GetMapping(value = "/list")
    @ResponseBody
    public Object userList(HttpServletRequest request, @RequestParam(value = "deptId") String deptId
            , @RequestParam(value = "searchInfo", required = false) String searchInfo
            , @RequestParam(value = "include")  Integer include) {
        List<UserInfoDO> userList;
        String userCode = (String) request.getSession().getAttribute(CommonConstants.USER_CODE);
        String createUserId = (String) request.getSession().getAttribute(CommonConstants.USER_ID);
        if (include == 0) {
            userList =  this.userService.getUsersList(deptId, createUserId, searchInfo,userCode);
        } else {
            //'1','2','3'
            String deptIds = deptService.getChildrenDept(deptId);
            userList = this.userService.getUsersList(deptIds, createUserId, searchInfo,userCode);

        }
        return resultHandler(ErrorCode.SUCCESS, "success", userList);
    }

}
