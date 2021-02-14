package com.manager.manager.service;

import com.manager.manager.domain.CityInfo;
import com.manager.manager.mapper.CityInfoMapper;
import com.manager.manager.vo.CityInfoVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 城市相关service
 *
 * @author lsy
 * Created on 2021-01-25
 */
@Service
public class CityService {

    @Autowired
    private CityInfoMapper cityInfoMapper;

    /**
     * 查询城市列表
     * @return
     */
    public List<CityInfoVO> queryList() {
        List<CityInfoVO> resList = new ArrayList<>();
        List<CityInfo> cityInfoList = cityInfoMapper.queryCityList();
        if(CollectionUtils.isEmpty(cityInfoList)) {
            return resList;
        }
        resList = cityInfoList.stream().map(c -> {
            CityInfoVO cityInfo = new CityInfoVO();
            cityInfo.setId(c.getId());
            cityInfo.setCityCode(c.getCityCode());
            cityInfo.setCityName(c.getCityName());
            return cityInfo;
        }).collect(Collectors.toList());
        return resList;
    }
    /**
     * @description: 新增员工信息
     * @author: mengwenyi
     * @date: 2021/2/7 16:44
     */
    public void insertCity(CityInfo cityInfo) {
        cityInfoMapper.insertCity(cityInfo);
    }
    /**
     * @description: 更新员工信息
     * @author: mengwenyi
     * @date: 2021/2/7 16:43
     */
    public void updateCity(CityInfo cityInfo) {
        cityInfoMapper.updateCity(cityInfo);
    }
    /**
     * @description: 删除城市
     * @author: mengwenyi
     * @date: 2021/2/13 20:42
     */
    public void delCity(int id) {
        cityInfoMapper.delCity(id);
    }
    /**
     * @description: 查询城市详情
     * @author: mengwenyi
     * @date: 2021/2/13 21:02
     */
    public CityInfo queryCityById(int id){
        return cityInfoMapper.queryCityById(id);
    }
    /**
     * @description: 查询城市选项列表
     * @author: mengwenyi
     * @date: 2021/2/13 21:30
     */ 
    public List<Map<String, Object>> queryCityOptions() {
        List<Map<String, Object>> resList = new ArrayList<>();
        List<CityInfo> cityInfoList = cityInfoMapper.queryCityList();
        if(CollectionUtils.isEmpty(cityInfoList)) {
            return resList;
        }
        resList = cityInfoList.stream().map(w -> {
            Map<String, Object> tmpMap = new HashMap<>();
            tmpMap.put("label", w.getCityName());
            tmpMap.put("value", w.getCityCode());
            return tmpMap;
        }).collect(Collectors.toList());
        return resList;
    }
}
