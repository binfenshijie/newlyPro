package com.newly.vas.business.user.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.newly.vas.business.deptInfo.bean.DepartInfoDO;
import com.newly.common.bean.BaseDO;
import com.newly.vas.business.deptInfo.bean.DepartInfoDO;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * Created by bingo on 2020/4/20 下午5:00.
 */
@Data
@ToString
public class UserDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 8642244968209695242L;

    /**
     * 用户ID
     */
    private String id;
    /**
     * 部门id
     */
    private String deptId;
    /**
     * 用户名
     */
    @Excel(name = "用户名*",  width = 128)
    @NotNull
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */
    @Excel(name= "真实姓名*", width = 128)
    @NotNull
    private String realName;
    /**
     * 用户类型
     * 1：admin， 0：用户自建用户
     */
    private Integer type;
    /**
     * 用户编号，用于警员编号
     */
    private String policeCode;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 电话
     */
    @Excel(name= "手机号码")
    private String phone;
    /**
     * 邮箱
     */
    @Excel(name= "邮箱")
    private String email;
    /**
     * 登录次数
     */
    private Integer loginTimes;
    /**
     * 真实姓名缩写
     */
    private String shortLetter;
    /**
     * 创建用户id
     */
    private String createUserId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 上次登录时间
     */
    private Date lastLoginTime;
    /**
     * 备注
     */
    @Excel(name= "备注")
    private String remark;
    /**
     * 用户所在部门
     */
    private DepartInfoDO department;
    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 所属租户
     */
    private String userCode;

    private String parentId;

}

