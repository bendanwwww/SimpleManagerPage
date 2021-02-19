package com.manager.manager.controller;

import com.manager.manager.common.ResultVo;
import com.manager.manager.domain.DailySales;
import com.manager.manager.dto.DailySaleDto;
import com.manager.manager.dto.ShowViewDto;
import com.manager.manager.service.DailySalesService;
import com.manager.manager.vo.DailySalesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	/**
	 * @description: 查询工单列表
	 * @author: mengwenyi
	 * @date: 2021/2/14 11:05
	 */
	/**
	 * @description: 查询员工分页列表
	 * @author: mengwenyi
	 * @date: 2021/2/7 17:00
	 */
	@RequestMapping(value = "queryOrderPage")
	public ResultVo queryWorkerPage(DailySaleDto orderDto) {
		return dailySalesService.queryOrderPage(orderDto);
	}
	/**
	 * @description: 添加工单
	 * @author: mengwenyi
	 * @date: 2021/2/14 11:37
	 */
	@RequestMapping(value = "insertOrder", method = RequestMethod.POST)
	public ResultVo<String> insertWorker(@RequestBody DailySaleDto dailySaleDto) {
		dailySalesService.insertDailySale(dailySaleDto);
		return ResultVo.build(() -> "success");
	}

	/**
	 * @description: 更新工单
	 * @author: mengwenyi
	 * @date: 2021/2/14 11:37
	 */
	@RequestMapping(value = "updateOrder", method = RequestMethod.POST)
	public ResultVo<String> updateOrder(@RequestBody DailySaleDto dailySaleDto) {
		dailySalesService.updateDailySale(dailySaleDto);
		return ResultVo.build(() -> "success");
	}
	/**
	 * @description: 查询工单详情
	 * @author: mengwenyi
	 * @date: 2021/2/14 12:02
	 */
	@RequestMapping(value = "queryOrderById")
	public ResultVo<DailySales> queryOrderById(@RequestParam(value = "id") int id) {
		return ResultVo.build(() -> dailySalesService.queryOrderById(id));
	}
	/**
	 * @description: 查询数据渲染
	 * @author: mengwenyi
	 * @date: 2021/2/18 19:45
	 */
	@RequestMapping(value = "queryOrderData")
	public ResultVo<DailySalesVo> queryOrderData(ShowViewDto showViewDto) {
		return dailySalesService.queryOrderData(showViewDto);
	}

}