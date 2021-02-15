package com.manager.manager.controller;

import com.manager.manager.common.ResultVo;
import com.manager.manager.dto.ShowViewDto;
import com.manager.manager.service.ShowViewService;
import com.manager.manager.vo.ShowViewVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: mengwenyi
 * @Date: 2021/2/14 20:47
 */
@RestController
@RequestMapping("show")
public class ShowViewController {
    @Autowired
    private ShowViewService showViewService;

    /**
     * @description: 查询销量列表
     * @author: mengwenyi
     * @date: 2021/2/14 20:53
     */
    @RequestMapping(value = "queryPersonalData", method = RequestMethod.GET)
    public ResultVo<ShowViewVo> queryPersonalData(ShowViewDto showViewDto) {
        ShowViewVo showViewVo =  showViewService.queryPersonalData(showViewDto);
        return ResultVo.build(() -> showViewVo);
    }
    /**
     * @description: 按时间查询销量
     * @author: mengwenyi
     * @date: 2021/2/15 15:52
     */
    @RequestMapping(value = "queryByDate", method = RequestMethod.GET)
    public ResultVo<ShowViewVo> queryByDate(ShowViewDto showViewDto) {
        ShowViewVo showViewVo =  showViewService.queryByDate(showViewDto);
        return ResultVo.build(() -> showViewVo);
    }
}
