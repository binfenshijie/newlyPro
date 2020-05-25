package com.newly.vas.base.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageCriteriaBean {

    private String orderBy;
    private String orderType;
    private Integer pageNo;
    private Integer pageSize;

}
