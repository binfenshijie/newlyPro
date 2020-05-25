package com.newly.vas.business.role.bean;

import lombok.Data;

@Data
public class RoleResTreeDO {

    public RoleResTreeDO() {
    }

    public RoleResTreeDO(String roleId, String treeNode) {
        this.roleId = roleId;
        this.treeNode = treeNode;
    }

    private String roleId;
    private String treeNode;

}
