package com.newly.vas.rs.video;

import com.newly.common.bean.BaseData;
import lombok.Data;

@Data
public class GateRestResult<T> extends BaseData {

    private T data;

    private Integer status;

}