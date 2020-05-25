package com.newly.vas.business.dept.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by bingo on 2020/4/19 下午4:07
 */
@Data
@ToString
public class DepartmentBO extends DepartmentDO {
    /**
     * 用户数
     */
    private Integer userNum;

    /**
     * 节点是否展开
     */
    private Boolean open;

    /**
     * 是否叶子
     */
    private Boolean leaf;

    /**
     * 父节点
     */
    @JsonIgnore
    private DepartmentBO parent;

    /**
     * 搜索内容是否查找到
     */
    private Boolean find;

    /**
     * 子部门
     */
    private List<DepartmentBO> children;

    /*
     * 递归设置父节点展开
     */
    public void setTreeNodeOpen(){
        setTreeNodeOpen(parent);
    }

    private void setTreeNodeOpen(DepartmentBO parent) {
        if (null != parent) {
            parent.setOpen(true);
            setTreeNodeOpen(parent.getParent());
        }
    }
}
