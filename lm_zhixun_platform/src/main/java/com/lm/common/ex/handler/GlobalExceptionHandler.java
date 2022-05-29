package com.lm.common.ex.handler;

import com.lm.common.ex.ErrorHandler;
import com.lm.common.ex.lthrow.UserExceptionThrow;
import com.lm.common.ex.lthrow.ValidatorExceptionThrow;
import com.lm.common.r.ServerErrorResultEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常管理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局运行异常捕获
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public ErrorHandler makeException(Throwable e, HttpServletRequest request) {
        // 1: 一定要加下面这行代码。打印异常堆栈信息，方便程序员去根据异常排查错误 --服务开发者
        e.printStackTrace();
        // 2: 出现异常，统一返回固定的状态---服务用户
        ErrorHandler errorHandler =  ErrorHandler.error(
                ServerErrorResultEnum.SERVER_ERROR.getCode(),
                ServerErrorResultEnum.SERVER_ERROR.getMsg(),e);
        // 3: 最后返回
        return  errorHandler;
    }

    /**
     * 全局运行异常捕获
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(UserExceptionThrow.class)
    public ErrorHandler UserException(UserExceptionThrow e, HttpServletRequest request) {
        // 1: 一定要加下面这行代码。打印异常堆栈信息，方便程序员去根据异常排查错误 --服务开发者
        //自定义的异常，看情况加下面这行代码也行
        e.printStackTrace();
        // 2: 出现异常，统一返回固定的状态---服务用户
        ErrorHandler errorHandler =  ErrorHandler.error(
                e.getCode(),
                e.getMsg(),e);
        // 3: 最后返回
        return  errorHandler;
    }

    /**
     * 验证器抛出异常捕获
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(ValidatorExceptionThrow.class)
    public ErrorHandler ValidatorException(ValidatorExceptionThrow e, HttpServletRequest request) {
        // 1: 一定要加下面这行代码。打印异常堆栈信息，方便程序员去根据异常排查错误 --服务开发者
        //自定义的异常，看情况加下面这行代码也行
        e.printStackTrace();
        // 2: 出现异常，统一返回固定的状态---服务用户
        ErrorHandler errorHandler =  ErrorHandler.error(
                e.getCode(),
                e.getMsg(),e);
        // 3: 最后返回
        return  errorHandler;
    }

}
