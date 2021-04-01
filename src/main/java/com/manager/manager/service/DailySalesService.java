package com.manager.manager.service;

import com.manager.manager.common.Page;
import com.manager.manager.common.ResultVo;
import com.manager.manager.domain.DailySales;
import com.manager.manager.domain.Worker;
import com.manager.manager.dto.DailySaleDto;
import com.manager.manager.dto.ShowViewDto;
import com.manager.manager.mapper.DailySalesMapper;
import com.manager.manager.mapper.WorkerMapper;
import com.manager.manager.util.DateUtils;
import com.manager.manager.util.ListUtils;
import com.manager.manager.vo.DailySalesVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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
     *
     * @return
     */
    public void updateDailySales(String wxCard, double dailyProfit, double dailyAmount, int type) {
        // 根据微信号查询员工信息
        Worker worker = workerMapper.queryWorkerByWX(wxCard);
        if (Objects.nonNull(worker)) {
            int workerId = worker.getId();
            // 查询员工当日业绩
            DailySales dailySales = dailySalesMapper.queryDailySales(workerId);
            if (Objects.nonNull(dailySales)) {
                if (type == 0) {
                    return;
                }
            } else {
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
        if (count <= 0) {
            page.setRows(new ArrayList<>());
            page.setTotal(count);
            return ResultVo.build(() -> page);
        } else {
            List<DailySales> list = dailySalesMapper.queryDailySalePage(orderDto);
            page.setRows(list);
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
    public DailySales queryOrderById(int id) {
        return dailySalesMapper.queryDailySaleById(id);
    }

    /**
     * @description:
     * @author: mengwenyi
     * @date: 2021/2/18 19:31
     */
    public ResultVo queryOrderData(ShowViewDto showViewDto) {
        List<DailySalesVo> dailySalesVoList = new ArrayList<>();
        List<DailySales> list = dailySalesMapper.queryPersonalData(showViewDto);
        if(ListUtils.isEmpty(list)){
            return ResultVo.build(() -> dailySalesVoList);
        }
        List<Integer> workerIdList = ListUtils.fetchFieldValue(list, "workerId");
        List<Worker> workerList = workerMapper.queryWorkerByIdList(workerIdList);
        showViewDto.setIsShowNow(1);
        List<DailySales> dailySales = dailySalesMapper.queryPersonalData(showViewDto);
        for (int i = 0; i < list.size(); i++) {
            DailySalesVo dailySalesVo = new DailySalesVo();
            BeanUtils.copyProperties(list.get(i), dailySalesVo);
            dailySalesVo.setNowTime(DateUtils.changeToString(new Date(), DateUtils.FORMATE_2));
            Worker worker = ListUtils.findEntityByFieldValue(workerList, "id", dailySalesVo.getWorkerId() + "");
            if(worker != null){
                dailySalesVo.setWorkAge(worker.getWorkAge());
                dailySalesVo.setAge(worker.getAge());
                dailySalesVo.setCityName(worker.getCityName());
            }
            dailySalesVo.setDailySaleAmount(0);
            dailySalesVo.setDailyProfile(0);
            DailySales dailySales1 = ListUtils.findEntityByFieldValue(dailySales, "workerId", dailySalesVo.getWorkerId() + "");
            if(dailySales1 != null){
                dailySalesVo.setDailySaleAmount(dailySales1.getSaleAmount());
                dailySalesVo.setDailyProfile(dailySales1.getProfile());
            }
            dailySalesVoList.add(dailySalesVo);
        }
        Collections.sort(dailySalesVoList, new Comparator<DailySalesVo>() {
            @Override
            public int compare(DailySalesVo o1, DailySalesVo o2) {
                if(o1.getDailySaleAmount() - o2.getDailySaleAmount() < 0){
                    return 1;
                } else if(o1.getDailySaleAmount() - o2.getDailySaleAmount() == 0){
                    return 0;
                } else{
                    return -1;
                }
            }
        });
        for (int i = 0; i < dailySalesVoList.size(); i++) {
            dailySalesVoList.get(i).setIndex(i + 1);
        }
        return ResultVo.build(() -> dailySalesVoList);

    }

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String monthfirst = format.format(c.getTime());
        System.out.println("===============nowfirst:" + monthfirst);

        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String monthlast = format.format(ca.getTime());
        System.out.println("===============last:" + monthlast);
    }

    /**
     * 查询数据渲染(按月统计)
     *
     * @return
     */
    public ResultVo queryOrderDataByMonth() {
        List<DailySalesVo> dailySalesVoList = new ArrayList<>();
        // 获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        Date monthfirst = c.getTime();
        // 获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date monthlast = ca.getTime();

        List<DailySales> list = dailySalesMapper.queryOrderDataByMonth(monthfirst, monthlast);
        if(ListUtils.isEmpty(list)){
            return ResultVo.build(() -> dailySalesVoList);
        }
        List<Integer> workerIdList = ListUtils.fetchFieldValue(list, "workerId");
        List<Worker> workerList = workerMapper.queryWorkerByIdList(workerIdList);
        List<DailySales> dailySales = dailySalesMapper.queryOrderDataByMonth(monthfirst, monthlast);
        for (int i = 0; i < list.size(); i++) {
            DailySalesVo dailySalesVo = new DailySalesVo();
            BeanUtils.copyProperties(list.get(i), dailySalesVo);
            dailySalesVo.setNowTime(DateUtils.changeToString(new Date(), DateUtils.FORMATE_2));
            Worker worker = ListUtils.findEntityByFieldValue(workerList, "id", dailySalesVo.getWorkerId() + "");
            if(worker != null){
                dailySalesVo.setWorkAge(worker.getWorkAge());
                dailySalesVo.setAge(worker.getAge());
                dailySalesVo.setCityName(worker.getCityName());
            }
            dailySalesVo.setDailySaleAmount(0);
            dailySalesVo.setDailyProfile(0);
            DailySales dailySales1 = ListUtils.findEntityByFieldValue(dailySales, "workerId", dailySalesVo.getWorkerId() + "");
            if(dailySales1 != null){
                dailySalesVo.setDailySaleAmount(dailySales1.getSaleAmount());
                dailySalesVo.setDailyProfile(dailySales1.getProfile());
            }
            dailySalesVoList.add(dailySalesVo);
        }
        Collections.sort(dailySalesVoList, new Comparator<DailySalesVo>() {
            @Override
            public int compare(DailySalesVo o1, DailySalesVo o2) {
                if(o1.getDailySaleAmount() - o2.getDailySaleAmount() < 0){
                    return 1;
                } else if(o1.getDailySaleAmount() - o2.getDailySaleAmount() == 0){
                    return 0;
                } else{
                    return -1;
                }
            }
        });
        for (int i = 0; i < dailySalesVoList.size(); i++) {
            dailySalesVoList.get(i).setIndex(i + 1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            dailySalesVoList.get(i).setNowTime(sdf.format(System.currentTimeMillis()));
        }
        return ResultVo.build(() -> dailySalesVoList);

    }

    /**
     * @description: 删除工单
     * @author: mengwenyi
     * @date: 2021/2/7 16:43
     */
    public void delDailySales(int id) {
        dailySalesMapper.delDailySales(id);
    }
}
