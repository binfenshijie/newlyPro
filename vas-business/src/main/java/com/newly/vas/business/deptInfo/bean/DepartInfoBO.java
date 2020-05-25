package com.newly.vas.business.deptInfo.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by bingo on 2020/4/19 下午4:07
 */
@Data
@ToString
public class DepartInfoBO extends DepartInfoDO {
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
    private DepartInfoBO parent;

    /**
     * 搜索内容是否查找到
     */
    private Boolean find;

    /**
     * 子部门
     */
    private List<DepartInfoBO> children;

    /*
     * 递归设置父节点展开
     */
    public void setTreeNodeOpen(){
        setTreeNodeOpen(parent);
    }

    private void setTreeNodeOpen(DepartInfoBO parent) {
        if (null != parent) {
            parent.setOpen(true);
            setTreeNodeOpen(parent.getParent());
        }
    }
}
