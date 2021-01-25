package com.manager.manager.common;

/**
 * 抛异方式上层应用的基础对象
 *
 * @author zhangyt
 */
public class ResultException extends RuntimeException {

    /** 错误码 */
    private ResultCode code;
    /** 异常信息 */
    private String message;
    /** 传递给ResultVo的指令，异常信息是否需要显示 */
    private Boolean showMessage;

    public ResultException(){}

    public ResultException(ResultCode code) {
        this.code = code;
    }

    public ResultException(String message) {
        super(message);
    }

    public ResultException(ResultCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultException(ResultCode code, String message, Boolean showMessage) {
        this.code = code;
        this.message = message;
        this.showMessage = showMessage;
    }

    public ResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultCode getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getShowMessage() {
        return showMessage;
    }

    public void setShowMessage(Boolean showMessage) {
        this.showMessage = showMessage;
    }
}
