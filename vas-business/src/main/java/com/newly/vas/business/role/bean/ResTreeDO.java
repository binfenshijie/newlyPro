package com.newly.vas.business.role.bean;

import com.newly.common.bean.BaseDO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * resTreeDO
 */
@Data
public class ResTreeDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = -1;

    private int id;
    private String treeNode;
    private String name;
    private String parentTreeNode;
    private Date createTime;
    private Date updateTime;
    private String remark;
    private String org_num;//后加组织编号
    private List<ResTreeDO> children;  
}
