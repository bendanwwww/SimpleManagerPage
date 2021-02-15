package com.manager.manager.service;

import com.manager.manager.domain.DailySales;
import com.manager.manager.dto.ShowViewDto;
import com.manager.manager.mapper.DailySalesMapper;
import com.manager.manager.util.ListUtils;
import com.manager.manager.vo.ShowViewVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public ShowViewVo queryPersonalData(ShowViewDto showViewDto){
        List<DailySales> list = dailySalesMapper.queryPersonalData(showViewDto);
        List<String> xAxisList = new ArrayList<>();
        List<Double> seriesList = new ArrayList<>();

        for(DailySales dailySales : list){
            xAxisList.add(dailySales.getWorkerName());
            seriesList.add(dailySales.getProfile());
        }
        ShowViewVo showViewVo = new ShowViewVo();
        Map title = new HashMap();
        title.put("text", "销量");
        showViewVo.setTitle(title);

        Map tooltip = new HashMap();
        showViewVo.setTooltip(tooltip);

        Map legend = new HashMap();
        legend.put("data", new ArrayList<>(Arrays.asList("销量")));
        showViewVo.setLegend(legend);

        Map xAxis = new HashMap();
        xAxis.put("data", xAxisList);
        showViewVo.setxAxis(xAxis);

        Map yAxis = new HashMap();
        showViewVo.setyAxis(yAxis);

        List<Map> series = new ArrayList<>();
        Map seriesMap = new HashMap();
        seriesMap.put("name", "销量");
        seriesMap.put("type", "bar");
        seriesMap.put("data", seriesList);
        series.add(seriesMap);
        showViewVo.setSeries(series);
        return showViewVo;
    }
}
