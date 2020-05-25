package com.newly.common.constant;

public interface ErrorCode {

    /**
     * 成功
     */
    int SUCCESS = 0;

    /**
     * 失败：外部参数错误导致系统异常
     */
    int ILLEGAL_PARAM = -1;

    /**
     * 错误：系统内部未处理/异常
     */
    int ERROR = -2;

    /**
     * 失败：未授权的请求
     */
    int UN_AUTH = -3;

    /**
     * 失败：
     */
    int FAIL = -4;



}
