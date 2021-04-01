package com.manager.manager.mapper;

import com.manager.manager.domain.DailySales;
import com.manager.manager.dto.DailySaleDto;
import com.manager.manager.dto.ShowViewDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
    /**
     * @description: 查询个人业绩
     * @author: mengwenyi
     * @date: 2021/2/14 21:12
     */
    List<DailySales> queryPersonalData(ShowViewDto showViewDto);

    /**
     * @description: 查询个人业绩
     * @author: mengwenyi
     * @date: 2021/2/14 21:12
     */
    List<DailySales> queryOrderDataByMonth(@Param(value = "startTime") Date startTime, @Param(value = "endTime") Date endTime);

    /**
     * @description: 按时间查询销量
     * @author: mengwenyi
     * @date: 2021/2/15 15:56
     */
    List<DailySales> queryByDate(ShowViewDto showViewDto);
    /**
     * @description:
     * @author: mengwenyi
     * @date: 2021/2/26 13:49
     */
    List<DailySales> queryDailySaleList(DailySaleDto dailySaleDto);
    /**
     * @description: 删除工单
     * @author: mengwenyi
     * @date: 2021/2/26 15:00
     */
    void delDailySales(@Param(value = "id") int id);
}