package com.newly.common.utils;

import java.util.UUID;

public class CommonUtils {

    /**
     * <P>
     * Type:String
     * </p>
     * <p>
     * Description: 生成UUID
     * </p>
     *
     * @author:bingo
     * @date 2018年7月13日
     */
    public static String generateRandomUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    /**
     * 根据pageNo和pageSize计算偏移量
     * @return
     */
    public static int calcOffset(int pageNo,int pageSize){
        return (pageNo - 1) * pageSize;
    }

}
