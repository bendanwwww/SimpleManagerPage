package com.manager.manager.controller;

import com.manager.manager.common.ResultVo;
import com.manager.manager.service.CityService;
import com.manager.manager.vo.CityInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("city")
public class CityController {

	@Autowired
	private CityService cityService;

	/**
	 * 查询城市列表
	 *
	 * @return
	 */
	@RequestMapping(value ="queryList", method = RequestMethod.GET)
	public ResultVo<List<CityInfoVO>> queryList() {
		return ResultVo.build(() -> cityService.queryList());
	}

}