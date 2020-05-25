package com.newly.vas.rs.atlab.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class TestDTO extends QiniuRestResult  {
    private TotalDTO total;
    private HashMap<String,CeshiDTO> streams;

}
