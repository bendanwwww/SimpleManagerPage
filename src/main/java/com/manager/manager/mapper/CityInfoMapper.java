package com.manager.manager.mapper;

import com.manager.manager.domain.CityInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CityInfoMapper {
    /**
     * @description: 查询城市列表
     * @author: mengwenyi
     * @date: 2021/2/13 20:55
     */
    List<CityInfo> queryCityList();
    /**
     * @description: 添加城市
     * @author: mengwenyi
     * @date: 2021/2/13 20:55
     */
    void insertCity(CityInfo cityInfo);
    /**
     * @description: 更新城市
     * @author: mengwenyi
     * @date: 2021/2/13 20:55
     */
    void updateCity(CityInfo cityInfo);
    /**
     * @description: 删除城市
     * @author: mengwenyi
     * @date: 2021/2/13 20:55
     */
    void delCity(@Param(value = "id") int id);
    /**
     * @description: 查询城市
     * @author: mengwenyi
     * @date: 2021/2/13 21:02
     */
    CityInfo queryCityById(@Param(value = "id") int id);


}