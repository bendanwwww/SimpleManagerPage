package com.manager.manager.controller;

import com.manager.manager.common.ResultVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
public class SendOrdinaryEmailController {
	@RequestMapping(value ="send", method = RequestMethod.POST)
	public ResultVo<Boolean> emailSend() {
		return ResultVo.build(() -> true);
	}

}