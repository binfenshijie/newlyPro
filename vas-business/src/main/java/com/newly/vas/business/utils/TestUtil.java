package com.newly.vas.business.utils;

import cn.hutool.crypto.SecureUtil;

//import com.newly.util.Hex;

public class TestUtil {
    public static void main(String[] args) {

        String s = SecureUtil.sha256("abc@123");
        System.out.println(s);

    }
}
