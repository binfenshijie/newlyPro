package com.newly.vas.business.deptInfo.bean;

import lombok.Data;

@Data
public class DeptParentDTO {
    private String deptId;
    private String name;
    private String deptCode;
    private String createUserId;
    private String remark;
    private String parentId;
    private String userCode;
}
