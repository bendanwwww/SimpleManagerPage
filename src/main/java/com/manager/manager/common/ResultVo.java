package com.manager.manager.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 标注viewbean
 *
 * @author zhangyt
 */
public class ResultVo<T extends Object> implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(ResultVo.class);

    /** 标志是否成功 */
    private boolean rlt;
    /** 返回消息 */
    private String message;
    /** 状态码 */
    private String code;
    /** 返回附加信息, java对象。可以是String、List、JSON、Map 类型 */
    private T data;

    /** 生成数据的处理器 */
    public interface BusinessProcessor<D> { D process(); }

    /** 结果构建 */
    public static <D> ResultVo<D> build(BusinessProcessor<D> processor) {

        ResultVo<D> resultVo = new ResultVo<>();
        try {
            D data = processor.process();
            resultVo.setRlt(true);
            resultVo.setCode(ResultCode.SUCCESS.getCode());
            resultVo.setMessage("ok");
            resultVo.setData(data);
        } catch (ResultException re) {
            log.error(re.getMessage(), re);
            resultVo.setRlt(false);
            resultVo.setCode(re.getCode() == null ? ResultCode.ERROR.getCode() : re.getCode().getCode());
            resultVo.setMessage(re.getMessage());
            if(re.getShowMessage() != null) {
                resultVo.setMessage(re.getMessage()); //指示客户都是否显示异常 message 信息
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            resultVo.setRlt(false);
            resultVo.setCode(ResultCode.ERROR.getCode());
            resultVo.setMessage("system exception");
        }

        return resultVo;

    }

    public boolean isRlt() {
        return rlt;
    }

    public void setRlt(boolean rlt) {
        this.rlt = rlt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
