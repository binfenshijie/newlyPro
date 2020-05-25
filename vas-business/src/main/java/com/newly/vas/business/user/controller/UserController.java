package com.newly.vas.business.user.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.newly.vas.base.constant.CommonConstants;
import com.newly.vas.business.annotation.Log;
import com.newly.vas.business.deptInfo.service.DepartInfoService;
import com.newly.vas.business.user.bean.UserBO;
import com.newly.vas.business.user.bean.UserDO;
import com.newly.common.controller.BaseController;
import com.newly.common.constant.ErrorCode;
import com.newly.vas.base.constant.CommonConstants;
import com.newly.vas.business.annotation.Log;
import com.newly.vas.business.deptInfo.service.DepartInfoService;
import com.newly.vas.business.user.bean.UserBO;
import com.newly.vas.business.user.bean.UserDO;
import com.newly.vas.business.user.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bingo
 * 最新的
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private DepartInfoService deptService;

    /**
     * 用户详情
     * @param userId
     * @return
     */
    @GetMapping(value = "/detail/{userId}")
    @ResponseBody
    public Object userDetail(@PathVariable("userId") String userId) {
        return this.resultHandler(ErrorCode.SUCCESS, "", userService.getUserAndDept(userId));
    }


    /**
     * 批量删除用户
     * @param userIds
     * @return
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    @Log("删除用户")
    public Object userDelete(@RequestBody List<String> userIds) {
        userService.batchDeleteUser(userIds);
        return this.resultHandler(ErrorCode.SUCCESS, "删除成功");
    }

    /**
     * 验证用户是否存在
     * @param name
     * @return
     */
    @GetMapping(value = "/verify/{userName}")
    @ResponseBody
    public Object verify(@PathVariable("userName") String name){
        if (null != userService.getByName(name)) {
            return this.resultHandler(ErrorCode.FAIL, "用户已存在");
        }
        return this.resultHandler(ErrorCode.SUCCESS, "");

    }

    @GetMapping(value = "/get/{userName}")
    @ResponseBody
    public Object userInfo(@PathVariable("userName") String name){
        UserDO user = userService.getByName(name);
        if (user !=null) {
            user.setPassword(null);
        }
        return this.resultHandler(ErrorCode.SUCCESS, "", user);

    }

    /**
     * 更新用户
     * @param userParam
     * @return
     */
    @PostMapping(value = "/update")
    @ResponseBody
    @Log("更新用户信息")
    public Object userUpdate(@RequestBody UserBO userParam){
        if (StringUtils.isEmpty(userParam.getId())) {
            return this.resultHandler(ErrorCode.FAIL, "用户id为空");
        }

        userService.updateUserWithRole(userParam, userParam.getRoleIds());
        return this.resultHandler(ErrorCode.SUCCESS, "编辑成功");
    }


    /**
     * 更新用户
     * @param userParam
     * @return
     */
    @PostMapping(value = "/updatePhone")
    @ResponseBody
    public Object updatePhone(String userId,String phone){
        userService.updatePhone(userId,phone);
        return this.resultHandler(ErrorCode.SUCCESS, "编辑成功");
    }

    /**
     * 增加用户
     * @param userParam
     * @param request
     * @return
     */
    @PostMapping(value = "/add")
    @ResponseBody
    @Log("添加用户")
    public Object userAdd(@RequestBody UserBO userParam, HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute(CommonConstants.USER_ID);
        if (userParam.getId() == null && userId !=null) {
            userParam.setCreateUserId(userId);
        }
        HashMap<String,Object> map=new HashMap<>();
        map.put("phone",userParam.getPhone());
        map.put("name",userParam.getName());
        map.put("email",userParam.getEmail());
        int phoneCount=userService.phoneCount(map);
        int nameCount=userService.nameCount(map);
        int emailCount=userService.emailCount(map);
        if(phoneCount>0 && userParam.getPhone()!="" && userParam.getPhone()!=null){
            return this.resultHandler(ErrorCode.FAIL, "手机号码不能重复！");
        }else if(nameCount>0 && userParam.getName()!="" && userParam.getName()!=null){
            return this.resultHandler(ErrorCode.FAIL, "用户名不能重复！");
        }else if(emailCount>0 && userParam.getEmail()!="" &userParam.getEmail()!=null){
            return this.resultHandler(ErrorCode.FAIL, "邮箱不能重复！");
        }
        userService.saveUserWithRole(userParam, userParam.getRoleIds());
        return this.resultHandler(ErrorCode.SUCCESS, "添加用户成功");
    }

    /**
     * 批量赋权限
     * @param userParam
     * @param request
     * @return
     */
    @PostMapping(value = "/batchGrantRole")
    @ResponseBody
    public Object batchGrantRole(@RequestBody UserBO userParam, HttpServletRequest request) {
        userService.batchGrantRole(userParam.getUserIds(), userParam.getRoleIds());
        return this.resultHandler(ErrorCode.SUCCESS, "批量赋予角色成功");
    }

    /**
     * 上传用户
     * @param file
     * @param deptId
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/upload")
    @ResponseBody
    public Object upload(HttpServletRequest request, MultipartFile file, @RequestParam(value = "deptId") String deptId) throws Exception{
        String userCode = (String) request.getSession().getAttribute(CommonConstants.USER_CODE);
        if (file.getOriginalFilename() == null || !file.getOriginalFilename().endsWith("xlsx")) {
            return this.resultHandler(ErrorCode.FAIL, "文件格式错误");
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        params.setNeedVerfiy(true);
        List<UserDO> list = ExcelImportUtil.importExcel(file.getInputStream(), UserDO.class, params);
        if (list == null || list.size() == 0) {
            return this.resultHandler(ErrorCode.FAIL, "表格数据为空或者未按要求填写");
        }
        list.stream().forEach(item -> {
            item.setDeptId(deptId);
            item.setPassword(UserService.ENCRYPT_PWD);
            item.setUserCode(userCode);
        });
        List<String> userIds = null;
        try {
            userIds =  userService.batchSaveUser(list);
        }catch (DuplicateKeyException e) {
            return this.resultHandler(ErrorCode.FAIL, "上传用户重复或数据库已有用户");
        }
        return this.resultHandler(ErrorCode.SUCCESS, "_添加成功，默认密码是：" + UserService.PWD, userIds);
    }

    /**
     * 删除时候验证
     * @param userName
     * @param password
     * @return
     */
    @PostMapping(value = "/deleteAuth")
    @ResponseBody
    public Object auth(@RequestParam String userName, @RequestParam String password) {
        if (userService.deleteAuth(userName, password)) {
            return this.resultHandler(ErrorCode.SUCCESS, "验证成功");
        }
        return this.resultHandler(ErrorCode.FAIL, "验证失败");

    }

    /**
     * 重置用户
     * @param userIds
     * @return
     */
    @PostMapping(value = "/reset")
    @ResponseBody
    public Object reset(@RequestBody List<String> userIds){
        if (!CollectionUtils.isEmpty(userIds)) {
            userService.batchChangePassword(userIds, UserService.ENCRYPT_PWD);
        }
        return this.resultHandler(ErrorCode.SUCCESS, "重置密码成功 初始密码为 " + UserService.PWD);
    }


    /**
     * 移动部门
     * @param userParam
     * @return
     */
    @PostMapping(value = "/move")
    @ResponseBody
    public Object move(@RequestBody UserBO userParam){
        userService.batchMoveDept(userParam.getUserIds(), userParam.getDeptId());
        return this.resultHandler(ErrorCode.SUCCESS, "部门变更成功");
    }

    /**
     * 用户分页列表
     * @param deptId
     * @param searchInfo
     * @param pageNo
     * @param pageSize
     * @param orderBy
     * @param orderType
     * @param include
     * @return
     */
    @GetMapping(value = "/page")
    @ResponseBody
    @Log("用户管理")
    public Object userPage(@RequestParam(value = "deptId") String deptId
            , @RequestParam(value = "searchInfo", required = false) String searchInfo
            , @RequestParam("pageNo") Integer pageNo
            , @RequestParam("pageSize") Integer pageSize
            , @RequestParam(value = "orderBy", required = false, defaultValue = "CREATE_TIME") String orderBy
            , @RequestParam(value = "orderType", required = false, defaultValue = "desc") String orderType
            , @RequestParam(value = "include")  Integer include) {

        List<UserDO> userList;
        int userCount;
        if (include == 0) {
            userList =  this.userService.getUsersPage(deptId, searchInfo, pageNo, pageSize, orderBy, orderType);
            userCount = this.userService.getUsersCount(deptId, searchInfo);
        } else {
            //'1','2','3'
            String deptIds = deptService.getChildrenDept(deptId);

            userList = this.userService.getUsersPage(deptIds, searchInfo, pageNo, pageSize, orderBy, orderType);
            userCount = this.userService.getUsersCount(deptIds, searchInfo);
            logger.info("子 deptId {}, 一共userCount{}", deptIds, userCount);
        }

        return resultHandler(ErrorCode.SUCCESS, "success", userList, userCount, pageNo);
    }

    /**
     * 用户列表
     * @param deptId
     * @param searchInfo
     * @param roleId
     * @param include
     * @return
     */
    @GetMapping(value = "/list")
    @ResponseBody
    public Object userList(@RequestParam(value = "deptId") String deptId
            , @RequestParam(value = "searchInfo", required = false) String searchInfo
            , @RequestParam(value = "roleId", required = false) String roleId
            , @RequestParam(value = "include")  Integer include) {
        List<UserDO> userList;
        if (include == 0) {
            userList =  this.userService.getUsersList(deptId, roleId, searchInfo);
        } else {
            //'1','2','3'
            String deptIds = deptService.getChildrenDept(deptId);
            userList = this.userService.getUsersList(deptIds, roleId, searchInfo);

        }
        return resultHandler(ErrorCode.SUCCESS, "success", userList);
    }

}