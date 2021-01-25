package com.manager.manager.util;

import com.alibaba.fastjson.JSONObject;
import com.manager.manager.common.ResultCode;
import com.manager.manager.common.ResultException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Iterator;
import java.util.Set;

/**
 * Copyright (C), 2003-2019, 好未来集团
 * FileName: ValidateUtil
 * Author:   xes-lq
 * Date:     2019/1/25 15:37
 * Description: 参数验证常用类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名         修改时间           版本号            描述
 *
 * @author xes-lq
 * @create 2019/1/25
 * @since 1.0.0
 */
public class ValidateUtil {

	private final static Logger logger = LoggerFactory.getLogger(ValidateUtil.class);

	/**
	 * Description：检查参数有效性，相应的抛出异常
	 *
	 * @param requestObject
	 */
	public static void checkRequestParameter(Object requestObject){
		if(requestObject == null){
			logger.error("[ValidateUtil.checkRequestParameter] input parameter is null!");
			throw new ResultException(ResultCode.ERROR_PARAMETER);
		}

		logger.info("[ValidateUtil.checkRequestParameter] input parameter is [{}].", JSONObject.toJSONString(requestObject));

		Set<ConstraintViolation<Object>> validResult = Validation.buildDefaultValidatorFactory().getValidator().validate(requestObject);
		if(CollectionUtils.isNotEmpty(validResult)){
			Iterator<ConstraintViolation<Object>> iterator = validResult.iterator();
			if(iterator != null && iterator.hasNext()){
				ConstraintViolation<Object> constraintViolation = iterator.next();
				String errMessage = StringUtils.isBlank(constraintViolation.getMessage())?"request parameter is illegal.":constraintViolation.getMessage();
				logger.error("[ValidateUtil.checkRequestParameter] input data is [{}], parameter check msg is [{}].", JSONObject.toJSONString(requestObject), errMessage);
				throw new ResultException(ResultCode.INPUT_PARAM_INVALID, errMessage);
			}
		}
	}
}