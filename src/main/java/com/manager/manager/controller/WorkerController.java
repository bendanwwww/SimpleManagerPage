package com.manager.manager.controller;

import com.alibaba.fastjson.JSON;
import com.manager.manager.common.ResultVo;
import com.manager.manager.domain.Worker;
import com.manager.manager.dto.WorkerDto;
import com.manager.manager.service.WorkerService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    /**
     * 搜索职工列表
     *
     * @return
     */
    @RequestMapping(value = "searchList")
    public ResultVo<List<Map<String, Object>>> searchList(@RequestParam(value = "sysName", required = false) String sysName) {
        if (StringUtils.isBlank(sysName)) {
            return ResultVo.build(() -> new ArrayList<>());
        }
        List<Worker> workers = workerService.queryList(null, null, sysName, null, null);
        if (CollectionUtils.isEmpty(workers)) {
            return ResultVo.build(() -> new ArrayList<>());
        }
        List<Map<String, Object>> resList = workers.stream().map(w -> {
            Map<String, Object> tmpMap = new HashMap<>();
            tmpMap.put("label", w.getCityName() + " " + w.getSysName());
            tmpMap.put("value", w.getId());
            return tmpMap;
        }).collect(Collectors.toList());
        return ResultVo.build(() -> resList);
    }

    /**
     * 查询职工列表
     *
     * @return
     */
    @RequestMapping(value = "queryList")
    public ResultVo<List<Worker>> queryList(@RequestParam(value = "id", required = false) Integer id,
                                            @RequestParam(value = "cityCode", required = false) String cityCode,
                                            @RequestParam(value = "sysName", required = false) String sysName,
                                            @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return ResultVo.build(() -> workerService.queryList(id, cityCode, sysName, pageNo, pageSize));
    }

    /**
     * 新增职工
     *
     * @param worker
     * @return
     */
    @RequestMapping(value = "insertWorker", method = RequestMethod.POST)
    public ResultVo<String> insertWorker(@RequestBody Worker worker) {
        workerService.insertWorker(worker);
        return ResultVo.build(() -> "success");
    }

    /**
     * 更新职工
     *
     * @param worker
     * @return
     */
    @RequestMapping(value = "updateWorker", method = RequestMethod.POST)
    public ResultVo<String> updateWorker(@RequestBody Worker worker) {
        workerService.updateWorker(worker);
        return ResultVo.build(() -> "success");
    }

    /**
     * 删除职工
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delWorker", method = RequestMethod.POST)
    public ResultVo<String> delWorker(@RequestParam(value = "id") int id) {
        workerService.delWorker(id);
        return ResultVo.build(() -> "success");
    }

    /**
     * @description: 查询员工详细
     * @author: mengwenyi
     * @date: 2021/2/7 15:43
     */
    @RequestMapping(value = "queryWorkerById")
    public ResultVo<Worker> queryWorkerById(@RequestParam(value = "id") int id) {
        return ResultVo.build(() -> workerService.queryWorkerById(id));
    }

    /**
     * @description: 查询员工分页列表
     * @author: mengwenyi
     * @date: 2021/2/7 17:00
     */
    @RequestMapping(value = "queryWorkerPage")
    public ResultVo queryWorkerPage(WorkerDto workerDto) {
        return workerService.queryWorkerPage(workerDto);
    }
    /**
     * @description: 查询下拉选项
     * @author: mengwenyi
     * @date: 2021/2/13 21:29
     */
    @RequestMapping(value = "queryWorkerOptions", method = RequestMethod.GET)
    public ResultVo<Map> queryCityOptions() {
        Map map = new HashMap();
        map.put("options", workerService.queryWorkerOptions());
        return ResultVo.build(() -> map);
    }
}