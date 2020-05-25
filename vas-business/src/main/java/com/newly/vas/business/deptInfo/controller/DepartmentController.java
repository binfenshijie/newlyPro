package com.newly.vas.business.deptInfo.controller;

import com.google.common.collect.Lists;
import com.newly.vas.business.deptInfo.bean.DepartInfoBO;
import com.newly.vas.business.deptInfo.bean.DepartInfoDO;
import com.newly.vas.business.deptInfo.bean.DeptParentDTO;
import com.newly.vas.business.deptInfo.bean.UserTree;
import com.newly.vas.business.deptInfo.service.DepartInfoService;
import com.newly.common.controller.BaseController;
import com.newly.common.constant.ErrorCode;
import com.newly.common.utils.BeanUtils;
import com.newly.common.utils.CommonUtils;
import com.newly.vas.base.constant.CommonConstants;
import com.newly.vas.business.annotation.Log;
import com.newly.vas.business.deptInfo.bean.DepartInfoBO;
import com.newly.vas.business.deptInfo.bean.DepartInfoDO;
import com.newly.vas.business.deptInfo.bean.DeptParentDTO;
import com.newly.vas.business.deptInfo.bean.UserTree;
import com.newly.vas.business.deptInfo.service.DepartInfoService;
import com.newly.vas.business.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bingo on 2020/4/16 上午10:34
 */
@Controller
@RequestMapping("/dept")
@CrossOrigin
public class DepartmentController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartInfoService deptService;

    @Autowired
    private UserService userService;

    /**
     * 部门详情
     *
     * @param deptId
     * @return
     */
    @GetMapping(value = "/detail/{deptId}")
    @ResponseBody
    public Object deptDetail(@PathVariable("deptId") String deptId) {
        DepartInfoDO dept = deptService.getById(deptId);
        DepartInfoBO departmentBO = new DepartInfoBO();
        BeanUtils.copy(dept, departmentBO);
        String deptIds = deptService.getChildrenDept(deptId);
        departmentBO.setUserNum(userService.getUsersCount(deptIds, ""));
        return this.resultHandler(ErrorCode.SUCCESS, "success", departmentBO);
    }

    @RequestMapping("/getCount")
    @ResponseBody
    public Object getCount(@RequestBody DepartInfoDO dept, HttpServletRequest request) {
        int count = deptService.getCount(dept.getName());
        if (count > 0) {
            return this.resultHandler(ErrorCode.FAIL, "部门名称重复");
        }
        return resultHandler(ErrorCode.SUCCESS, "success");
    }

    /**
     * 增加部门
     *
     * @param dept
     * @param request
     * @return
     */
    @PostMapping(value = "/add")
    @ResponseBody
    @Log("添加部门")
    public Object deptAdd(@RequestBody DepartInfoDO dept, HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute(CommonConstants.USER_ID);
        String userCode = (String) request.getSession().getAttribute(CommonConstants.USER_CODE);
        if (dept != null) {
            dept.setUserCode(userCode);
            dept.setCreateUserId(userId);
            dept.setId(CommonUtils.generateRandomUUID());
            try {
                deptService.save(dept);
            } catch (DuplicateKeyException e) {
                return this.resultHandler(ErrorCode.FAIL, "部门编号重复");
            }
        }
        return this.resultHandler(ErrorCode.SUCCESS, "添加部门成功");
    }

    /**
     * 新增父部门
     * @param dept
     * @param request
     * @return
     */
    @PostMapping(value = "/addPrent")
    @ResponseBody
    public Object addPrent(@RequestBody DeptParentDTO dept, HttpServletRequest request){
        String userCode = (String) request.getSession().getAttribute(CommonConstants.USER_CODE);
        String userId = (String) request.getSession().getAttribute(CommonConstants.USER_ID);
        dept.setUserCode(userCode);
        dept.setCreateUserId(userId);
        dept.setDeptId(CommonUtils.generateRandomUUID());
        deptService.saveParent(dept);
        return this.resultHandler(ErrorCode.SUCCESS, "添加部门成功");
    }

    /**
     * 更新部门
     * @param dept
     * @return
     */
    @PostMapping(value = "/update")
    @ResponseBody
    @Log("编辑部门")
    public Object deptUpdate(@RequestBody DepartInfoDO dept) {
        if (StringUtils.isEmpty(dept.getId())) {
            return this.resultHandler(ErrorCode.FAIL, "参数错误");
        }
        try{
            deptService.update(dept);
        }catch (DuplicateKeyException e) {
            return this.resultHandler(ErrorCode.FAIL, "部门编号重复");
        }

        return this.resultHandler(ErrorCode.SUCCESS, "编辑部门成功");
    }


    /**
     * 搜索部门
     * @param search
     * @return
     */
    @GetMapping(value = "/search")
    @ResponseBody
    public Object deptSearch(@RequestParam(value = "search") String search){
        List<DepartInfoDO> data = deptService.getByNameOrDeptCode(search);
        return this.resultHandler(ErrorCode.SUCCESS, "", data);
    }

    /**
     * 删除部门
     * @param deptId
     * @return
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    @Log("删除部门")
    public Object deptUpdate(@RequestParam(value = "deptId") String deptId) {
        if (userService.getUsersCount(deptId, null) > 0) {
            return this.resultHandler(ErrorCode.FAIL, "部门存在用户不允许删除");
        }
        deptService.delete(deptId);
        return this.resultHandler(ErrorCode.SUCCESS, "删除部门成功");
    }

    /**
     * 部门树
     * @param deptId
     * @param search
     * @return
     */
    @GetMapping(value = "/tree")
    @ResponseBody
    public Object deptTree(HttpServletRequest request,@RequestParam(value = "deptId") String deptId, @RequestParam(value = "searchInfo") String search){
           String userId = (String) request.getSession().getAttribute(CommonConstants.USER_ID);
        String userCode = (String) request.getSession().getAttribute(CommonConstants.USER_CODE);
        UserTree userTree=deptService.getUserTree(userId);
//        if("0".equals(userTree.getIszuhu())){
            deptId=deptService.getDeptId(userCode);
//        }else{
//            deptId=
//        }
        List<DepartInfoDO> deptDOs = deptService.getListByMaps(userCode);
        List<DepartInfoBO> copyDeptDOs = Lists.newArrayList();
        List<DepartInfoBO> result = Lists.newArrayList();
        List<DepartInfoBO> topNodes = null;

        deptDOs.stream().forEach(source -> {
                    DepartInfoBO target = new DepartInfoBO();
                    BeanUtils.copy(source, target);
                    copyDeptDOs.add(target); }
        );

        for (int i = 0; i <deptDOs.size() ; i++) {
            if ("0".equals(deptDOs.get(i).getIsParent())){
                String finalDeptId = deptId;
                topNodes = copyDeptDOs.stream().filter(temp -> finalDeptId.equals(temp.getId()))
                        .collect(Collectors.toList());
            }
        }
    if (topNodes!=null){
        Iterator<DepartInfoBO> iterator = topNodes.iterator();
        while (iterator.hasNext()) {
            DepartInfoBO temp = iterator.next();
            temp.setOpen(true);
            List<DepartInfoBO> childrenTreeNodes = deptService.buildDeptTree(temp, copyDeptDOs, search);
            if (childrenTreeNodes.size() == 0) {
                temp.setLeaf(true);
                temp.setChildren(null);
            } else {
                temp.setLeaf(false);
                temp.setChildren(childrenTreeNodes);
            }
            result.add(temp);
        }
    }
        return this.resultHandler(ErrorCode.SUCCESS, "", result);
    }




}
