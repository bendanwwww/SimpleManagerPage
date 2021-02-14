package com.manager.manager.service;

import com.manager.manager.domain.DailySales;
import com.manager.manager.dto.ShowViewDto;
import com.manager.manager.mapper.DailySalesMapper;
import com.manager.manager.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: mengwenyi
 * @Date: 2021/2/14 20:52
 */
@Service
public class ShowViewService {

    @Autowired
    private DailySalesMapper dailySalesMapper;
    /**
     * @description: 查询个人业绩
     * @author: mengwenyi
     * @date: 2021/2/14 21:09
     */
    public Map queryPersonalData(ShowViewDto showViewDto){
        Map map = new HashMap();
        List<DailySales> list = dailySalesMapper.queryPersonalData(showViewDto);
        List<String> xAxis = new ArrayList<>();
        List<Double> series = new ArrayList<>();

        for(DailySales dailySales : list){
            xAxis.add(dailySales.getWorkerName());
            series.add(dailySales.getProfile());
        }
//        map.put("line", xAxis);
        map.put("line", series);
        return map;
    }
}
