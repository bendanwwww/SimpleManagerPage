package com.manager.manager.common;

/**
 * 结果代码枚举类
 *
 * <pre>
 *     迁移自原项目的依赖包中的EResultCode类，增加message属性。并用只用到的原有code数据。
 * </pre>
 *
 * @author zhangyt
 */
public enum ResultCode {

    /** 成功 */
    SUCCESS("000000"),
	/** 业务重复 **/
	INPUT_BIZ_REPEAT("800001"),
    /** login模块错误码 */
    LOGIN_LOWER_VERSION("800002"),
    /** 输入参数无效 **/
    INPUT_PARAM_INVALID("999997"),
    /** 参数异常 */
    ERROR_PARAMETER("999998", "参数异常"),
    /** 系统异常 */
    ERROR("999999");

    /** code */
    private String code;
    /** 信息 */
    private String message;

    ResultCode(String code) {
        this.code = code;
    }
    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
