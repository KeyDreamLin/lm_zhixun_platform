package com.lm.common.ex;

/**
 * 统一异常管理bean类
 */
public class ErrorHandler {
    // ErrorHandler === R 答案：不想破坏R类。
    // 异常的状态码，从枚举中获得
    private Integer status;
    // 异常的消息，写用户看得懂的异常，从枚举中得到
    private String message;
    // 异常的名字
    private String exception;

    private ErrorHandler() {
    }

    public ErrorHandler(Integer status, String message, String exception) {
        this.status = status;
        this.message = message;
        this.exception = exception;
    }

    public static ErrorHandler error(Integer status, String message, Throwable e) {
        ErrorHandler errorHandler = new ErrorHandler(status,message,e.getClass().getName());
//        this.status = status;
//        this.message = message;
//        this.exception = exception;
        return errorHandler;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
