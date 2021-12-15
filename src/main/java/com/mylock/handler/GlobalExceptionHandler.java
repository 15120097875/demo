package com.mylock.handler;

import com.mylock.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(CheckException.class)
    @ResponseBody
    public R<?> handleException(CheckException e){
//        log.error("GloableHandleException", e);
        log.error("GloableHandleException: {}", e.getMessage());
        return R.failed(null, e.getMessage());
    }

    /**
     * 参数校验
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public R<?> handleException(MethodArgumentNotValidException e){
        BindingResult exceptions = e.getBindingResult();
        if (exceptions.hasErrors()){
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()){
                FieldError fieldError = (FieldError) errors.get(0);
                return R.failed(null, fieldError.getDefaultMessage());
            }
        }
        log.error("GloableHandleException", e);
        return R.failed(null, "参数异常");
    }

    /**
     * 系统异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R handleException(Exception e){
        log.error("GloableHandleException", e);
        return R.failed(null, "系统异常");
    }

}
