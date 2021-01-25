package com.manager.manager.service;

import com.manager.manager.domain.CityInfo;
import com.manager.manager.mapper.CityInfoMapper;
import com.manager.manager.vo.CityInfoVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

}
