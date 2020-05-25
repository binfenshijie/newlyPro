package com.newly.vas.business.login.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.newly.vas.base.bean.PageCriteriaBean;
import com.newly.vas.base.constant.CommonConstants;
import com.newly.vas.base.constant.IndustryType;
import com.newly.vas.base.utils.DateUtils;
import com.newly.vas.base.utils.ExcelUtils;
import com.newly.vas.business.annotation.Log;
import com.newly.vas.business.dept.service.DepartmentService;
import com.newly.vas.business.login.bean.LoginInfoDO;
import com.newly.vas.business.login.service.CacheManager;
import com.newly.vas.business.login.service.LoginInfoService;
import com.newly.vas.business.login.service.Sessions;
import com.newly.vas.business.userInfo.bean.UserInfoDO;
import com.newly.vas.business.userInfo.service.UserInfoService;
import com.newly.common.controller.BaseController;
import com.newly.common.constant.ErrorCode;
import com.newly.common.utils.CommonUtils;
import com.newly.vas.base.bean.PageCriteriaBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by bingo on 2020/4/5 下午4:41
 */
@CrossOrigin
@RestController
@Api("身份认证模块")
@RequestMapping("/login")
public class LoginController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserInfoService userService;


    @Autowired
    private DepartmentService departmentService;


    @Autowired
    private LoginInfoService loginInfoService;

    /**
     * 前端判断接口是否登录
     *
     * @return
     */
    @GetMapping("/test")
    @ResponseBody
    public Object beLogin(HttpServletRequest request) {
        String userName = Sessions.getCurrentUserAccount(request) != null ?
                Sessions.getCurrentUserAccount(request)
                : null;
        if (userName == null) {
            return resultHandler(ErrorCode.UN_AUTH, "user not login", "redirect to login");
        }
        return this.resultHandler(ErrorCode.SUCCESS, "already login", userName);
    }

    @ApiOperation(value="用户名密码登录", notes="用户名密码登录",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "Newly", required = true, dataType = "String",paramType="query"),
            @ApiImplicitParam(name = "password", value = "e5857b335afdf35ca81a110bc81f38682f8a89892cc597f5398dfef82d42b513", required = true, dataType = "String",paramType="query")
    })
    @PostMapping(value = "/auth")
    @Log("登录系统")
    public Object login(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password,
                        HttpServletRequest request, HttpServletResponse response) {
        UserInfoDO userInfo = userService.getByName(name);
        if ((null != userInfo) && (password.equals(userInfo.getPassword()))) {
            Sessions.login(request, response, name, userInfo.getId(), userInfo.getType(), userInfo.getUserCode(), userInfo.getIszuhu());
            userInfo.setLoginTimes(userInfo.getLoginTimes() + 1);
            userService.updateLoginInfo(userInfo);
            return this.resultHandler(ErrorCode.SUCCESS, "登录成功");
        } else {
            return this.resultHandler(ErrorCode.FAIL, "用户名或密码错误");
        }
    }

    @PostMapping(value = "/modifyAuth")
    public Object login(@RequestParam(value = "password") String password,
                        HttpServletRequest request, HttpServletResponse response) {
        String name = (String) request.getSession().getAttribute(CommonConstants.USER_ACCOUNT);
        if (name == null)
            return this.resultHandler(ErrorCode.FAIL, "请重新登录!!!");
        UserInfoDO userInfo = userService.getByName(name);
        if ((null != userInfo) && (password.equals(userInfo.getPassword()))) {
            Sessions.login(request, response, name, userInfo.getId(), userInfo.getType(), userInfo.getUserCode(), userInfo.getIszuhu());
            userInfo.setLoginTimes(userInfo.getLoginTimes() + 1);
            userService.updateLoginInfo(userInfo);
            return this.resultHandler(ErrorCode.SUCCESS, "Success!!!");
        } else {
            return this.resultHandler(ErrorCode.FAIL, "密码错误");
        }
    }


    @GetMapping(value = "/check")
    public Object login(@RequestParam(value = "phone", required = false) String phone,
                        @RequestParam(value = "name", required = false) String name) {
        if (StringUtils.isNotEmpty(name) && userService.getByName(name) != null) {
            return this.resultHandler(ErrorCode.FAIL, "用户名已存在");
        } else if (StringUtils.isNotEmpty(phone) && userService.getByPhone(phone) != null) {
            return this.resultHandler(ErrorCode.FAIL, "电话号码已注册");
        } else {
            return this.resultHandler(ErrorCode.SUCCESS, "");
        }
    }

    @GetMapping(value = "/industryType")
    public Object industryType() {
        Map<String, Integer> industryType = new HashMap<>();
        for (IndustryType enums : IndustryType.values()) {
            industryType.put(enums.getName(), enums.getValue());
        }
        return this.resultHandler(ErrorCode.SUCCESS, "success", industryType);
    }

    @GetMapping(value = "/logout")
    public Object logout(HttpServletRequest request) {
        //让session过期,并进行跳转
        //String account = Sessions.getCurrentUserAccount(request);
        Sessions.logout(request);
        return this.resultHandler(ErrorCode.SUCCESS, "退出成功");
    }


    @PostMapping(value = "/findPassword")
    public Object changePwd(@RequestParam(value = "phone") String phone
            , @RequestParam(value = "phoneCaptcha") String phoneCaptcha
            , @RequestParam(value = "newPassword") String newPwd
            , @RequestParam(value = "userName") String userName) {
        String msg = CacheManager.getInstance().getPhoneCaptcha(phone);
        UserInfoDO user = userService.getByName(userName);
        if (user != null && !user.getPhone().equals(phone)) {
            return this.resultHandler(ErrorCode.FAIL, "用户绑定手机号码错误");
        } else if (phoneCaptcha.equals(msg)) {
            UserInfoDO userInfoDO = userService.getByPhone(phone);
            userInfoDO.setPassword(newPwd);
            userService.update(userInfoDO);
            return this.resultHandler(ErrorCode.SUCCESS, "修改密码成功");
        } else {
            return this.resultHandler(ErrorCode.FAIL, "手机验证码错误");
        }
    }

    @GetMapping("/checkPhoneMsg")
    @ResponseBody
    public Object checkPhoneMsg(@RequestParam(value = "phone") String telephone, @RequestParam(value = "msg") String msg) {
        if (StringUtils.isBlank(telephone) && StringUtils.isBlank(msg)) {
            return resultHandler(ErrorCode.FAIL, "请填写手机号码及手机验证码");
        }
        String phoneCaptcha = CacheManager.getInstance().getPhoneCaptcha(telephone);
        if (msg.equals(phoneCaptcha)) {
            return resultHandler(ErrorCode.SUCCESS, "success");
        } else {
            return resultHandler(ErrorCode.FAIL, "手机验证码错误或已过期");
        }
    }



    private String createPhoneCode() {
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
        return verifyCode;
    }

    @GetMapping("/sessionID")
    @ResponseBody
    public Object sessionID(HttpServletRequest request, HttpSession session) {
        String userName = Sessions.getCurrentUserAccount(request);
        System.out.println(userName);

        String sessionId = session.getId();
        System.out.println(sessionId);
        return this.resultHandler(ErrorCode.SUCCESS, "success");
    }


    @PostMapping("/saveLoginInfo")
    @ResponseBody
    public Object saveLoginInfo(@RequestBody LoginInfoDO loginInfoDO,
                                HttpServletRequest request, HttpSession session) {
        String userName = Sessions.getCurrentUserAccount(request);

        if (org.apache.commons.lang3.StringUtils.isEmpty(userName)) {
            return resultHandler(ErrorCode.UN_AUTH, "请重新登录!!!");
        }

        String userCode = (String) request.getSession().getAttribute(CommonConstants.USER_CODE);
        String sessionId = session.getId();
        loginInfoDO.setId(CommonUtils.generateRandomUUID());
        loginInfoDO.setUserName(userName);
        loginInfoDO.setUserCode(userCode);
        loginInfoDO.setSessionId(sessionId);
        loginInfoDO.setLoginTime(new Date());
        loginInfoDO.setRemark("登录信息");
        loginInfoService.save(loginInfoDO);
        return this.resultHandler(ErrorCode.SUCCESS, "success");
    }

    @GetMapping("/getLoginInfoList")
    @ResponseBody
    public Object getLoginInfoList(
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime,
            PageCriteriaBean pageCriteriaBean,
            HttpServletRequest request) {
        String userName = Sessions.getCurrentUserAccount(request);
        if (org.apache.commons.lang3.StringUtils.isEmpty(userName)) {
            return resultHandler(ErrorCode.UN_AUTH, "请重新登录!!!");
        }
        String userCode = (String) request.getSession().getAttribute(CommonConstants.USER_CODE);

        int offset = CommonUtils.calcOffset(pageCriteriaBean.getPageNo(), pageCriteriaBean.getPageSize());
        HashMap<String, Object> map = new HashMap<>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("userCode", userCode);
        map.put("userName", userName);
        map.put("offset", offset);
        map.put("pageSize", pageCriteriaBean.getPageSize());
        map.put("orderBy", "LOGIN_TIME");
        map.put("orderType", "DESC");
        List<LoginInfoDO> dos = loginInfoService.getPageList(map);
//        List<LoginInfoVO> vos = new ArrayList<>();
//        dos.forEach(d -> {
//            LoginInfoVO v = new LoginInfoVO();
//            BeanUtil.copyPropertiesIgnoreNull(d, v);
//            vos.add(v);
//        });

        List<Map<String, Object>> vos = new ArrayList<>();
        dos.forEach(d -> {
            Map<String, Object> m = new HashMap<>();
            m.put("ip", d.getIp());
            m.put("address", d.getRegion() + d.getCity() + " " + d.getIsp());
            m.put("loginTime", DateUtils.formatDate(d.getLoginTime(), "yyyy-MM-dd HH:mm:ss"));
            vos.add(m);
        });

        int total = loginInfoService.getPageCount(map);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", vos);
        resultMap.put("total", total);
        resultMap.put("pageNo", pageCriteriaBean.getPageNo());
        return this.resultHandler(ErrorCode.SUCCESS, "success", resultMap);
    }


    @GetMapping("/exportLoginList")
    @ResponseBody
    public void exportLoginInfoList(
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime,
            PageCriteriaBean pageCriteriaBean,
            HttpServletRequest request, HttpServletResponse response) {
        String userName = Sessions.getCurrentUserAccount(request);
        String userCode = (String) request.getSession().getAttribute(CommonConstants.USER_CODE);

        int offset = CommonUtils.calcOffset(pageCriteriaBean.getPageNo(), pageCriteriaBean.getPageSize());
        HashMap<String, Object> map = new HashMap<>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("userCode", userCode);
        map.put("userName", userName);
        map.put("offset", offset);
        map.put("pageSize", pageCriteriaBean.getPageSize());
        map.put("orderBy", "LOGIN_TIME");
        map.put("orderType", "DESC");
        List<LoginInfoDO> dos = loginInfoService.getPageList(map);
        List<Map<String, Object>> vos = new ArrayList<>();
        dos.forEach(d -> {
            Map<String, Object> m = new HashMap<>();
            m.put("ip", d.getIp());
            m.put("address", d.getRegion() + d.getCity() + " " + d.getIsp());
            m.put("loginTime", DateUtils.formatDate(d.getLoginTime(), "yyyy-MM-dd HH:mm:ss"));
            vos.add(m);
        });
        String fileName = "VAAS登录信息列表.xlsx";
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("list", vos);
        TemplateExportParams params = new TemplateExportParams();
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        params.setTemplateUrl(path + "template/loginInfoList.xlsx");
        Workbook book = ExcelExportUtil.exportExcel(params, dataMap);
        ExcelUtils.downloadExcel(fileName, response, book);
    }
}
