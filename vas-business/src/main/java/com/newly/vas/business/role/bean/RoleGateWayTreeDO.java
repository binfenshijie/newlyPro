package com.newly.vas.business.role.bean;

import lombok.Data;

@Data
public class RoleGateWayTreeDO {

    private String roleId;
    private String gateWayId;

    public RoleGateWayTreeDO(String roleId, String gateWayId) {
        this.roleId = roleId;
        this.gateWayId = gateWayId;
    }
}
