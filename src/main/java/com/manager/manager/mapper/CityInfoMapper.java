package com.manager.manager.mapper;

import com.manager.manager.domain.CityInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CityInfoMapper {
    List<CityInfo> queryCityList();
}