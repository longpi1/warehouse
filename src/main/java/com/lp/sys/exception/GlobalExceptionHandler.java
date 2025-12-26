package com.lp.sys.exception;

import com.lp.sys.common.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author lp
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResultObj handleBusinessException(BusinessException e) {
        log.error("业务异常: {}", e.getMessage(), e);
        return new ResultObj(e.getCode(), e.getMessage());
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultObj handleValidationException(MethodArgumentNotValidException e) {
        log.error("参数校验异常: {}", e.getMessage());
        String errorMessage = e.getBindingResult().getFieldError() != null
                ? e.getBindingResult().getFieldError().getDefaultMessage()
                : "参数校验失败";
        return new ResultObj(-1, errorMessage);
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public ResultObj handleException(Exception e) {
        log.error("系统异常: {}", e.getMessage(), e);
        return new ResultObj(-1, "系统异常，请联系管理员");
    }
}
