package com.newly.vas.business.role.controller;

import com.google.common.collect.ImmutableMap;
import com.newly.vas.base.constant.CommonConstants;
import com.newly.vas.business.annotation.Log;
import com.newly.vas.business.role.bean.*;
import com.newly.vas.business.role.service.RoleService;
import com.newly.common.controller.BaseController;
import com.newly.common.bean.CommonKeyBO;
import com.newly.common.constant.ErrorCode;
import com.newly.vas.base.constant.CommonConstants;
import com.newly.vas.business.annotation.Log;
import com.newly.vas.business.login.service.Sessions;
import com.newly.vas.business.role.bean.*;
import com.newly.vas.business.role.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 */
@RestController
@RequestMapping("/role/")
@CrossOrigin
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    private final static Logger log = LoggerFactory.getLogger(RoleController.class);
    /**
     * 角色分页列表
     * @param name 角色名 过滤项
     * @param pageNo 页数
     * @param pageSize 条数
     * @param orderBy 排序字段
     * @param orderType 排序方式 asc desc
     * @return
     */
    @GetMapping("list")
    @ResponseBody
    @Log("角色管理")
    public Object list(HttpServletRequest request,@RequestParam(value = "name",required = false) String name,
                       @RequestParam("pageNo") Integer pageNo,
                       @RequestParam("pageSize") Integer pageSize,
                       @RequestParam(value = "orderBy",required = false,defaultValue = "CREATE_TIME") String orderBy,
                       @RequestParam(value = "orderType",required = false,defaultValue = "desc") String orderType){
        String userCode = (String) request.getSession().getAttribute(CommonConstants.USER_CODE);
        String userId = (String) request.getSession().getAttribute(CommonConstants.USER_ID);
        String userZuhu = (String) request.getSession().getAttribute(CommonConstants.USER_ZUHU);
        if("0".equals(userZuhu)){
            userId="";
        }else{
            userCode="";
        }
        List<RoleDO> roleList = this.roleService.getRoleListByName(name,userCode,pageNo,pageSize,orderBy,orderType,userId);

        int roleCount = this.roleService.getRoleCountByName(name,userCode,userId);

         return resultHandler(ErrorCode.SUCCESS,"success",roleList,roleCount,pageNo);
    }

    /**
     * 添加用户使用的无分页的角色列表
     * @param name 角色名 过滤项
     * @return
     */
    @GetMapping("listAll")
    @ResponseBody
    public Object listAll(HttpServletRequest request,@RequestParam(value = "name",required = false) String name){
        String userCode = (String) request.getSession().getAttribute(CommonConstants.USER_CODE);
        String userId = (String) request.getSession().getAttribute(CommonConstants.USER_ID);
        String userZuhu = (String) request.getSession().getAttribute(CommonConstants.USER_ZUHU);
        if("0".equals(userZuhu)){
            userId="";
        }
        List<RoleDO> roleList = this.roleService.getRoleListByName(name,userCode,userId);
        return resultHandler(ErrorCode.SUCCESS,"success",roleList);
    }

    /**
     * 删除角色
     * @param roleIds
     * @return
     */
    @DeleteMapping("delete")
    @ResponseBody
    @Log("删除角色")
    public Object delete(@RequestBody String[] roleIds){
        boolean flag = this.roleService.deleteList(roleIds);
        return resultHandler(ErrorCode.SUCCESS,flag ? "success" : "fail");
    }

    /**
     * 角色关联用户数量
     * @param roleIds
     * @return
     */
    @PostMapping("relatedUserCount")
    @ResponseBody
    public Object relatedUserCount(@RequestBody String[] roleIds){
        int count = this.roleService.getUserCountByRoleIdList(roleIds);
        return resultHandler(ErrorCode.SUCCESS,"success",count);
    }

    /**
     * 保存/更新角色
     * @param roleBO 角色对象
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    @Log("编辑角色信息")
    public Object edit(@RequestBody RoleBO roleBO, HttpServletRequest request){
        String userId = (String) request.getSession().getAttribute(CommonConstants.USER_ID);
        String userCode = (String) request.getSession().getAttribute(CommonConstants.USER_CODE);
        roleBO.setCreateUserId(userId);//临时处理 后续改为登录用户id
        roleBO.setUserCode(userCode);//临时处理 后续改为登录用户id
        boolean flag = this.roleService.saveOrUpdateRole(roleBO);
        return resultHandler(ErrorCode.SUCCESS,flag ? "success" : "fail");
    }

    @RequestMapping("/getCount")
    @ResponseBody
    public Object getCount(@RequestBody RoleBO roleBO, HttpServletRequest request){
        String userCode = (String) request.getSession().getAttribute(CommonConstants.USER_CODE);
        int count=roleService.getCount(roleBO.getName(),userCode);
        if(count>0){
            return resultHandler(ErrorCode.FAIL,"角色名不能重复！");
        }
        return resultHandler(ErrorCode.SUCCESS,"success");
    }

    /**
     * 获取角色信息
     * @param roleId
     * @return
     */
    @GetMapping("getRole")
    @ResponseBody
    public Object getRole(@RequestParam String roleId){
        //角色
        RoleDO role = this.roleService.getById(roleId);
        return resultHandler(ErrorCode.SUCCESS,"success",role);
    }

    /**
     * 关联用户列表
     * @param roleId
     * @return
     */
    @GetMapping("relatedUser")
    @ResponseBody
    public Object relatedUser(HttpServletRequest request,@RequestParam String roleId){
        String userCode = (String) request.getSession().getAttribute(CommonConstants.USER_CODE);
        //已配置用户
        List<CommonKeyBO> userList = this.roleService.getRelatedUserByRoleId(roleId,userCode);
        for (CommonKeyBO item : userList
             ) {
            System.out.println(item.getId() + " " + item.getName());
        }
        return resultHandler(ErrorCode.SUCCESS,"success",userList);
    }

    /**
     * 关联角色列表
     * @param userId
     * @return
     */
    @GetMapping("relatedRole")
    @ResponseBody
    public Object relatedRole(HttpServletRequest request,@RequestParam String userId){
        String userCode = (String) request.getSession().getAttribute(CommonConstants.USER_CODE);
        //已配置用户
        List<RoleDO> roleList = this.roleService.getRelatedRoleByUserId(userId,userCode);
        return resultHandler(ErrorCode.SUCCESS,"success",roleList);
    }

    /**
     * 功能菜单树形列表
     * @param roleId
     * @return
     */
    @GetMapping("menuList")
    @ResponseBody
    public Object menuList(@RequestParam(required = false) String roleId,HttpServletRequest request){
        String userId =(String) request.getSession().getAttribute(CommonConstants.USER_ID);
        String userCode =(String) request.getSession().getAttribute(CommonConstants.USER_CODE);
        List<MenuTreeBO> menuTreeList = new ArrayList<>();

        //应用列表
        List<AppDO> appList = this.roleService.getListByMaps(new HashMap<String,Object>());

        if(appList != null){
            for(AppDO app : appList){
                MenuTreeBO menuTree = new MenuTreeBO();
                menuTree.setName(app.getAppName());
                menuTree.setAppCode(app.getAppCode());
                if(roleId != null){
                    List<String> roleMenuList = this.roleService.getRelatedMenu(roleId,app.getAppCode());
                    menuTree.setChecked(roleMenuList);
                }

                //全部功能菜单列表
//                List<MenuTreeDetailBO> menuTreeDetailList = this.roleService.getTreeListByAppCodes(app.getAppCode());
                List<MenuTreeDetailBO> menuTreeDetailList =  roleService.getTreeListByAppCodes(userId,userCode,app.getAppCode());;
                menuTree.setData(menuTreeDetailList);
                menuTreeList.add(menuTree);
            }
        }
        return resultHandler(ErrorCode.SUCCESS, "success", menuTreeList);
    }


    @GetMapping("menuListAray")
    public Object menuListAray(HttpServletRequest request,@RequestParam("appCode") String appCode){


        String userIds = Sessions.getCurrentUserAccount(request);
        String userId =(String) request.getSession().getAttribute(CommonConstants.USER_ID);
        String userCode =(String) request.getSession().getAttribute(CommonConstants.USER_CODE);

        List<MenuDTO> menuList = roleService.menuList(userId,userCode,appCode);

        List<String> menuCodeList = roleService.menuCodeList(userId,userCode,appCode);



        Map<String,Object> result = ImmutableMap.<String,Object>builder()
                .put("menuList",menuList)
                .put("menuCodeList",menuCodeList)
                .build();

        return resultHandler(ErrorCode.SUCCESS,"success",result);
    }


    @GetMapping("operatorList")
    public Object operatorList(HttpServletRequest request,@RequestParam("appCode") String appCode,@RequestParam("menuCode") String menuCode){

        String userId = (String) request.getSession().getAttribute(CommonConstants.USER_ID);
        String userCode = (String) request.getSession().getAttribute(CommonConstants.USER_CODE);

        List<MenuDTO> operatorList = roleService.operatorList(userId,userCode,appCode,menuCode);

        List<String> operatorCodeList = roleService.operatorCodeList(userId,userCode,appCode,menuCode);

        Map<String,Object> result = ImmutableMap.<String,Object>builder()
                .put("operatorList",operatorList)
                .put("operatorCodeList",operatorCodeList)
                .build();

        return resultHandler(ErrorCode.SUCCESS,"success",result);
    }


    /**
     * 资源菜单树形列表
     * @param roleId
     * @return
     */
    @GetMapping("resTreeList")
    @ResponseBody
    public Object resTreeList(HttpServletRequest request,@RequestParam(required = false) String roleId){
        List<String> nodeId = roleService.getNodeId(roleId);
        List<String> gateWayId = roleService.getGateWayId(roleId);
        String userId = (String) request.getSession().getAttribute(CommonConstants.USER_ID);
        String userCode =(String) request.getSession().getAttribute(CommonConstants.USER_CODE);
        String iszuhu =(String) request.getSession().getAttribute(CommonConstants.USER_ZUHU);
        String type="0";
        if("1".equals(iszuhu)){
            type="1";
        }else{
            type="0";
        }
        Map<String,Object> result = ImmutableMap.<String,Object>builder()
                .put("nodeId",nodeId)
                .put("gateWayId",gateWayId)
                .build();
        return this.resultHandler(ErrorCode.SUCCESS, "成功", result);
    }

    /**
     * 测试mq发送数据
     * routingKey 队列名
     * @return
     */
//    @GetMapping("sendMessage")
//    @ResponseBody
//    public Object sendMessage(){
//        try {
//            rabbitTemplate.convertAndSend("hello1",MessageBuilder.withBody("张三".getBytes("UTF-8")).build());
//        }catch (Exception e){
//
//        }
//        return this.resultHandler(ErrorCode.SUCCESS, "成功");
//    }

    /**
     * mq接收消息
     * @param message
     */
//    @RabbitListener(queues ="hello1")
//        public void getMessage(byte[] message){
//            try {
//                String msg=new String(message,"UTF-8");
//                System.out.println(msg);
//            }catch (Exception e){
//                e.getMessage();
//            }
//    }

}
