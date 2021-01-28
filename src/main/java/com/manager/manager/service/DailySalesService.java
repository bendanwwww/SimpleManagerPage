package com.manager.manager.service;

import com.manager.manager.domain.DailySales;
import com.manager.manager.domain.Worker;
import com.manager.manager.mapper.DailySalesMapper;
import com.manager.manager.mapper.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * 职工业绩相关service
 *
 * @author lsy
 * Created on 2021-01-25
 */
@Service
public class DailySalesService {

    @Autowired
    private WorkerMapper workerMapper;
    @Autowired
    private DailySalesMapper dailySalesMapper;

    /**
     * 更新员工每日业绩
     * @return
     */
    public void updateDailySales(String wxCard, double dailyProfit, double dailyAmount, int type) {
        // 根据微信号查询员工信息
        Worker worker = workerMapper.queryWorkerByWX(wxCard);
        if(Objects.nonNull(worker)) {
            int workerId = worker.getId();
            // 查询员工当日业绩
            DailySales dailySales = dailySalesMapper.queryDailySales(workerId);
            if(Objects.nonNull(dailySales)) {
                if(type == 0) {
                    return;
                }
            }else {
                dailySales = new DailySales();
                dailySales.setWorkerId(workerId);
                dailySales.setWorkerName(worker.getSysName());
                dailySales.setCreateTime(new Date());
            }
            dailySales.setDailyProfit(dailyProfit);
            dailySales.setDailyAmount(dailyAmount);
            dailySales.setRecordDate(new Date());
            dailySales.setUpdateTime(new Date());
            dailySalesMapper.mergeDailySales(dailySales);
        }
    }

}
