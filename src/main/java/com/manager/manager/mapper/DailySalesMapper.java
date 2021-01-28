package com.manager.manager.mapper;

import com.manager.manager.domain.DailySales;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DailySalesMapper {

    DailySales queryDailySales(int workerId);

    void mergeDailySales(DailySales dailySales);
}