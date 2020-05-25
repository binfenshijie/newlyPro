package com.newly.vas.rs.dora.dto;

import lombok.Data;
import lombok.ToString;
import net.sf.json.JSONObject;

@Data
public class FaceInfoParam {
    private String id;

    @Override
    public String toString() {
        JSONObject object = JSONObject.fromObject(this);
        return object.toString();
    }
}
