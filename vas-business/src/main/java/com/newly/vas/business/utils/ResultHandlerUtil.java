package com.newly.vas.business.utils;

import com.newly.common.constant.ErrorCode;
import com.newly.vas.rs.video.GateRestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultHandlerUtil {
    /**
     * 封装返回
     *
     * @param data
     * @return
     */
    public static GateRestResult result(GateRestResult data) {
        if (data != null) {
            if (data.getCode() == 0) {
                data.setCode(ErrorCode.SUCCESS);
                data.setMsg("Invoke Success!!!");
            } else {
                data.setCode(ErrorCode.FAIL);
                data.setMsg(data.getMsg());
            }
        } else {
            data = new GateRestResult();
            data.setCode(ErrorCode.ERROR);
            data.setMsg("调用 VGS 异常!!!");
        }
        return data;
    }


    public static boolean isThisTime(String param) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String now = sdf.format(new Date());//当前时间
        Date date=sdf.parse(param);
        String params=sdf.format(date);
        if (params.equals(now)) {
            return true;
        }
        return false;
    }
}
