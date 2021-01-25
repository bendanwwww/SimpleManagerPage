package com.manager.conf;

import com.manager.manager.common.ResultVo;
import com.manager.manager.common.ResultCode;
import com.manager.manager.common.ResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 统一异常处理
 * 重点处理参数校验的两个exception
 * @author wangxiao
 * @date 2018-08-21
 */
@ControllerAdvice
public class MyExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);

    // 前端统一错误文案
    private static final String ERROR_MESSAGE = "系统开小差了，请稍后再试~";
    // 网络异常错误文案
    private static final String SOCKET_ERRORMESSAGE = "请求超时，请稍后重试吧~";

    /**
     * 处理exception
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultVo exceptionHandler(Exception ex) {
        log.error("捕获到Exception异常", ex);
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultCode.ERROR.getCode());
        resultVo.setMessage(ex.getMessage());
        return resultVo;
    }

    /**
     * 处理ResultException
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ResultException.class)
    public ResultVo resultExceptionHandler(ResultException ex) {
        log.error("捕获到ResultException异常", ex);
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ex.getCode().getCode());
        resultVo.setMessage(ex.getMessage());
        return resultVo;
    }
    
    /**
     * 处理httpMessageNotReadableException
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResultVo httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
        log.error("捕获到HttpMessageNotReadableException异常", ex);
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultCode.ERROR_PARAMETER.getCode());
        resultVo.setMessage(ResultCode.ERROR_PARAMETER.getMessage());
        return resultVo;
    }
    
    /**
     * 处理servletRequestBindingExceptionHandler
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ServletRequestBindingException.class)
    public ResultVo servletRequestBindingExceptionHandler(ServletRequestBindingException ex) {
        log.error("捕获到ServletRequestBindingException异常", ex);
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultCode.ERROR_PARAMETER.getCode());
        resultVo.setMessage(ResultCode.ERROR_PARAMETER.getMessage());
        return resultVo;
    }

    /**
     * 单个传参校验异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResultVo constraintViolationExceptionHandler(ConstraintViolationException ex) {
        log.error("捕获到ConstraintViolationException异常");
        String errorMsg = "";
        Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) ex).getConstraintViolations();
        for (ConstraintViolation<?> constraintViolationItem : constraintViolations) {
            errorMsg =  constraintViolationItem.getMessageTemplate();
            break;
        }
        log.error("异常信息:{}",errorMsg);
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultCode.ERROR_PARAMETER.getCode());
        resultVo.setMessage(errorMsg);
        return resultVo;
    }

    /**
     * json形式传参校验异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultVo methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        log.error("捕获到MethodArgumentNotValidException异常");
        String errorMsg = "";
        FieldError error = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldError();
        errorMsg = error.getDefaultMessage();
        log.error("异常信息:{}",errorMsg);
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultCode.ERROR_PARAMETER.getCode());
        resultVo.setMessage(errorMsg);
        return resultVo;
    }
}