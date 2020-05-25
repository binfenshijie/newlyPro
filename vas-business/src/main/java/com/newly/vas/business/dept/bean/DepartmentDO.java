package com.newly.vas.business.dept.bean;

import com.newly.common.bean.BaseDO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bingo on 2020/4/13 上午11:00
 */
@Data
@ToString
public class DepartmentDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 324982095890614951L;

    /**
     * 部门id
     */
    private String id;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 部门编码
     */
    private String deptCode;
    /**
     * 父节点id
     */
    private String parentId;
    /**
     * 部门名称首字母缩写
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
     * 备注信息
     */
    private String remark;
    /**
     * 所有用户
     */
  // private List<UserDO> users;

}
