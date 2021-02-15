package com.manager.manager.service;

import com.manager.manager.common.Page;
import com.manager.manager.common.ResultVo;
import com.manager.manager.domain.DailySales;
import com.manager.manager.domain.Worker;
import com.manager.manager.dto.DailySaleDto;
import com.manager.manager.mapper.DailySalesMapper;
import com.manager.manager.mapper.WorkerMapper;
import com.manager.manager.util.CommonUtil;
import com.manager.manager.vo.DailySalesVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            dailySales.setRecordDate(new Date());
            dailySales.setUpdateTime(new Date());
            dailySalesMapper.mergeDailySales(dailySales);
        }
    }
    /**
     * @description: 查询工单分页列表
     * @author: mengwenyi
     * @date: 2021/2/14 11:09
     */
    public ResultVo queryOrderPage(DailySaleDto orderDto) {
        int count = dailySalesMapper.queryDailySalePageCount(orderDto);
        Page page = new Page();
        if(count <= 0){
            page.setRows(new ArrayList<>());
            page.setTotal(count);
            return ResultVo.build(() -> page);
        }else{
            List<DailySales> list = dailySalesMapper.queryDailySalePage(orderDto);
            List<DailySalesVo> returnList = new ArrayList<>();
            for(DailySales dailySales : list){
                DailySalesVo dailySalesVo = new DailySalesVo();
                BeanUtils.copyProperties(dailySales, dailySalesVo);
                dailySalesVo.setSteelTypeName(CommonUtil.getSteelTypeNameById(dailySalesVo.getSteelType()));
                returnList.add(dailySalesVo);
            }
            page.setRows(returnList);
            page.setTotal(count);
            return ResultVo.build(() -> page);
        }

    }
    /**
     * @description: 新增员工信息
     * @author: mengwenyi
     * @date: 2021/2/7 16:44
     */
    public void insertDailySale(DailySaleDto dailySaleDto) {
        dailySaleDto.setCreateTime(new Date());
        Worker worker = workerMapper.queryWorkerById(dailySaleDto.getWorkerId());
        dailySaleDto.setWorkerName(worker.getRealName());
        dailySalesMapper.insertDailySale(dailySaleDto);
    }
    /**
     * @description: 更新员工信息
     * @author: mengwenyi
     * @date: 2021/2/7 16:43
     */
    public void updateDailySale(DailySaleDto dailySaleDto) {
        dailySaleDto.setUpdateTime(new Date());
        Worker worker = workerMapper.queryWorkerById(dailySaleDto.getWorkerId());
        dailySaleDto.setWorkerName(worker.getRealName());
        dailySalesMapper.updateDailySale(dailySaleDto);
    }
    /**
     * @description: 查询工单详情
     * @author: mengwenyi
     * @date: 2021/2/14 12:03
     */
    public DailySales queryOrderById(int id){
        return dailySalesMapper.queryDailySaleById(id);
    }

}
