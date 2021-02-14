package com.manager.manager.mapper;

import com.manager.manager.domain.DailySales;
import com.manager.manager.dto.DailySaleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DailySalesMapper {

    DailySales queryDailySales(int workerId);

    void mergeDailySales(DailySales dailySales);

   /**
    * @description:
    * @author: mengwenyi
    * @date: 2021/2/14 11:13
    */ 
    List<DailySales> queryDailySalePage(DailySaleDto dailySaleDto);
    /**
     * @description:
     * @author: mengwenyi
     * @date: 2021/2/7 17:05
     */
    int queryDailySalePageCount(DailySaleDto dailySaleDto);
    /**
     * @description: 添加工单
     * @author: mengwenyi
     * @date: 2021/2/14 11:31
     */
    void insertDailySale(DailySaleDto dailySaleDto);
    /**
     * @description: 修改工单
     * @author: mengwenyi
     * @date: 2021/2/14 11:31
     */
    void updateDailySale(DailySaleDto dailySaleDto);
    /**
     * @description: 查询工单详情
     * @author: mengwenyi
     * @date: 2021/2/14 12:04
     */
    DailySales queryDailySaleById(@Param(value = "id") int id);

}