package com.newly.vas.api.v1.dto;

import lombok.Data;

@Data
public class BasicResult {
    private Integer code = 0;
    private String msg = "Success!";
}
