package com.manager.manager.controller;

import com.manager.manager.common.ResultVo;
import com.manager.manager.service.DailySalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dailySales")
public class DailySalesController {

	@Autowired
	private DailySalesService dailySalesService;

	/**
	 * 更新员工业绩
	 *
	 * @param wxCard
	 * @param dailyProfit
	 * @param dailyAmount
	 * @param type
	 * @return
	 */
	@RequestMapping(value ="updateDailySales", method = RequestMethod.POST)
	public ResultVo<String> updateDailySales(@RequestParam("wxCard") String wxCard,
											 @RequestParam("dailyProfit") double dailyProfit,
											 @RequestParam("dailyAmount") double dailyAmount,
											 @RequestParam("type") int type) {
		dailySalesService.updateDailySales(wxCard, dailyProfit, dailyAmount, type);
		return ResultVo.build(() -> "success");
	}

}