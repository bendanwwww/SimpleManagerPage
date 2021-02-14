package com.manager.manager.controller;

import com.manager.manager.common.ResultVo;
import com.manager.manager.domain.CityInfo;
import com.manager.manager.service.CityService;
import com.manager.manager.vo.CityInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "queryList", method = RequestMethod.GET)
    public ResultVo<List<CityInfoVO>> queryList() {
        return ResultVo.build(() -> cityService.queryList());
    }

    /**
     * @description: 添加城市
     * @author: mengwenyi
     * @date: 2021/2/13 20:52
     */
    @RequestMapping(value = "insertCity", method = RequestMethod.POST)
    public ResultVo<String> insertWorker(@RequestBody CityInfo cityInfo) {
        cityService.insertCity(cityInfo);
        return ResultVo.build(() -> "success");
    }

    /**
     * @description: 更新城市
     * @author: mengwenyi
     * @date: 2021/2/13 20:53
     */
    @RequestMapping(value = "updateCity", method = RequestMethod.POST)
    public ResultVo<String> updateWorker(@RequestBody CityInfo cityInfo) {
        cityService.updateCity(cityInfo);
        return ResultVo.build(() -> "success");
    }

    /**
     * @description: 删除城市
     * @author: mengwenyi
     * @date: 2021/2/13 20:40
     */
    @RequestMapping(value = "delCity", method = RequestMethod.POST)
    public ResultVo<String> delWorker(@RequestParam(value = "id") int id) {
        cityService.delCity(id);
        return ResultVo.build(() -> "success");
    }

    /**
     * @description: 查询城市详情
     * @author: mengwenyi
     * @date: 2021/2/13 21:01
     */
    @RequestMapping(value = "queryCityById")
    public ResultVo<CityInfo> queryWorkerById(@RequestParam(value = "id") int id) {
        return ResultVo.build(() -> cityService.queryCityById(id));
    }
    /**
     * @description: 查询下拉选项
     * @author: mengwenyi
     * @date: 2021/2/13 21:29
     */ 
	@RequestMapping(value = "queryCityOptions", method = RequestMethod.GET)
	public ResultVo<Map> queryCityOptions() {
		Map map = new HashMap();
		map.put("options", cityService.queryCityOptions());
		return ResultVo.build(() -> map);
	}
}