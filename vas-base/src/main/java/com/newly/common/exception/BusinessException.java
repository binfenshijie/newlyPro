package com.newly.common.exception;


/**
 * 业务异常类，获取异常码code、异常信息message和父类的异常原因cause
 * Created by bingo on 2020/4/27 下午2:54.
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -9065661397121333085L;

    private int code;
    private String msg;

    /**
     * @param code 异常码
     * @param message 异常信息
     */
    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    /**
     * @param code 异常码
     * @param message 异常信息
     * @param cause 异常原因
     */
    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.msg = message;
    }

    /**
     * 实例化异常
     * @param code 异常码
     * @param message 异常信息
     * @return
     */
    public static com.newly.common.exception.BusinessException newInstance(int code, String message) {
        return new com.newly.common.exception.BusinessException(code, message);
    }
}
