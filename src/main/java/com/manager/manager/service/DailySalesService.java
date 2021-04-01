package com.manager.manager.service;

import com.manager.manager.common.Page;
import com.manager.manager.common.ResultVo;
import com.manager.manager.domain.CityInfo;
import com.manager.manager.domain.DailySales;
import com.manager.manager.domain.Worker;
import com.manager.manager.dto.DailySaleDto;
import com.manager.manager.dto.ShowViewDto;
import com.manager.manager.dto.WorkerDto;
import com.manager.manager.mapper.CityInfoMapper;
import com.manager.manager.mapper.DailySalesMapper;
import com.manager.manager.mapper.WorkerMapper;
import com.manager.manager.util.DateUtils;
import com.manager.manager.util.ListUtils;
import com.manager.manager.vo.DailySalesVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.Int;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.manager.manager.util.DateUtils.*;

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
    @Autowired
    private CityInfoMapper cityInfoMapper;

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
        CityInfo cityInfo = cityInfoMapper.queryCityById(worker.getCityId());
        dailySaleDto.setCityId(cityInfo.getId());
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
        CityInfo cityInfo = cityInfoMapper.queryCityById(worker.getCityId());
        dailySaleDto.setCityId(cityInfo.getId());
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
        WorkerDto workerDto = new WorkerDto();
        workerDto.setRealName(showViewDto.getWorkerName());
        if(showViewDto.getCityId() != null){
            workerDto.setCityId(showViewDto.getCityId());
        }
        List<Worker> allWorkerList = workerMapper.queryAllWorkerList(workerDto);
        if(ListUtils.isEmpty(allWorkerList)){
            return ResultVo.build(() -> dailySalesVoList);
        }
        packageTime(showViewDto);
        List<DailySales> list = dailySalesMapper.queryPersonalData(showViewDto);
        showViewDto.setStartTime(null);
        showViewDto.setEndTime(null);
        showViewDto.setIsShowNow(1);
        List<DailySales> dailySales = dailySalesMapper.queryPersonalData(showViewDto);
        for(Worker worker : allWorkerList){
            DailySalesVo dailySalesVo = new DailySalesVo();
            dailySalesVo.setWorkAge(worker.getWorkAge());
            dailySalesVo.setWorkerId(worker.getId());
            dailySalesVo.setWorkerName(worker.getRealName());
            dailySalesVo.setAge(worker.getAge());
            dailySalesVo.setCityName(worker.getCityName());
            dailySalesVo.setDailySaleAmount(0);
            dailySalesVo.setDailyProfile(0);
            dailySalesVo.setSaleAmount(0);
            dailySalesVo.setProfile(0);
            dailySalesVo.setNowTime(DateUtils.changeToString(new Date(), FORMATE_2));
            DailySales monthDailySales = ListUtils.findEntityByFieldValue(list, "workerId",  worker.getId() + "");
            if(monthDailySales != null){
                dailySalesVo.setSaleAmount(monthDailySales.getSaleAmount());
                dailySalesVo.setProfile(monthDailySales.getProfile());
            }
            DailySales dailySale = ListUtils.findEntityByFieldValue(dailySales, "workerId",  worker.getId() + "");
            if(dailySale != null){
                dailySalesVo.setDailySaleAmount(dailySale.getSaleAmount());
                dailySalesVo.setDailyProfile(dailySale.getProfile());
            }
            dailySalesVoList.add(dailySalesVo);
        }
        sortDailySales(dailySalesVoList);
        for (int i = 0; i < dailySalesVoList.size(); i++) {
            dailySalesVoList.get(i).setIndex(i + 1);
        }
        return ResultVo.build(() -> dailySalesVoList);

    }
    public void packageTime(ShowViewDto showViewDto){
        if(showViewDto.getMonth() != null){
            int year = Integer.parseInt(showViewDto.getMonth().split("-")[0]);
            int month = Integer.parseInt(showViewDto.getMonth().split("-")[1]);
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month-1);
            c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
            String monthfirst = DateUtils.changeToString(c.getTime(), FORMATE_2);
            monthfirst += " 00:00:00";
            Date monthfirstTime = DateUtils.changeToDate(monthfirst, FORMATE_1);
            // 获取当前月最后一天
            Calendar ca = Calendar.getInstance();
            //设置年份
            ca.set(Calendar.YEAR,year);
            //设置月份
            ca.set(Calendar.MONTH, month-1);
            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
            String monthlast = DateUtils.changeToString(ca.getTime(), FORMATE_2);
            monthlast += " 24:00:00";
            Date monthlastTime = DateUtils.changeToDate(monthlast, FORMATE_1);
            showViewDto.setStartTime(monthfirstTime);
            showViewDto.setEndTime(monthlastTime);
        }else{

            // 获取当前月第一天：
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, 0);
            c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
            String monthfirst = DateUtils.changeToString(c.getTime(), FORMATE_2);
            monthfirst += " 00:00:00";
            Date monthfirstTime = DateUtils.changeToDate(monthfirst, FORMATE_1);
            // 获取当前月最后一天
            Calendar ca = Calendar.getInstance();
            c.add(Calendar.MONTH, 0);
            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
            String monthlast = DateUtils.changeToString(ca.getTime(), FORMATE_2);
            monthlast += " 24:00:00";
            Date monthlastTime = DateUtils.changeToDate(monthlast, FORMATE_1);
            showViewDto.setStartTime(monthfirstTime);
            showViewDto.setEndTime(monthlastTime);
        }

    }
    /**
     * @description: 排序
     * @author: mengwenyi
     * @date: 2021/4/1 18:13
     */
    public void sortDailySales(List<DailySalesVo> dailySalesVoList){
        Collections.sort(dailySalesVoList, new Comparator<DailySalesVo>() {
            @Override
            public int compare(DailySalesVo o1, DailySalesVo o2) {
                if(o1.getDailySaleAmount() - o2.getDailySaleAmount() < 0){
                    return 1;
                } else if(o1.getDailySaleAmount() - o2.getDailySaleAmount() == 0){
                    if(o1.getSaleAmount() - o2.getSaleAmount() < 0){
                        return 1;
                    } else if(o1.getSaleAmount() - o2.getSaleAmount() == 0){
                        return 0;
                    } else{
                        return -1;
                    }
                } else{
                    return -1;
                }
            }
        });
    }
    public static void main(String[] args) {
        // 获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String monthfirst = DateUtils.changeToString(c.getTime(), FORMATE_2);
        monthfirst += " 00:00:00";
        Date monthfirstTime = DateUtils.changeToDate(monthfirst, FORMATE_1);
        // 获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.add(Calendar.MONTH, 0);
        String monthlast = DateUtils.changeToString(ca.getTime(), FORMATE_2);
        monthlast += " 24:00:00";
        Date monthlastTime = DateUtils.changeToDate(monthlast, FORMATE_1);
        System.out.println();
    }

    /**
     * 查询数据渲染(按月统计)
     *
     * @return
     */
    public ResultVo queryOrderDataByMonth(ShowViewDto showViewDto) {
        List<DailySalesVo> dailySalesVoList = new ArrayList<>();
        WorkerDto workerDto = new WorkerDto();
        if(showViewDto.getCityId() != null){
            workerDto.setCityId(showViewDto.getCityId());
        }
        List<Worker> allWorkerList = workerMapper.queryAllWorkerList(workerDto);
        if(ListUtils.isEmpty(allWorkerList)){
            return ResultVo.build(() -> dailySalesVoList);
        }
        packageTime(showViewDto);
        List<DailySales> list = dailySalesMapper.queryOrderDataByMonth(showViewDto);
        for(Worker worker : allWorkerList){
            DailySalesVo dailySalesVo = new DailySalesVo();
            dailySalesVo.setWorkAge(worker.getWorkAge());
            dailySalesVo.setWorkerId(worker.getId());
            dailySalesVo.setWorkerName(worker.getRealName());
            dailySalesVo.setAge(worker.getAge());
            dailySalesVo.setCityName(worker.getCityName());
            dailySalesVo.setDailySaleAmount(0);
            dailySalesVo.setDailyProfile(0);
            dailySalesVo.setSaleAmount(0);
            dailySalesVo.setProfile(0);
            dailySalesVo.setNowTime(DateUtils.changeToString(new Date(), FORMATE_4));
            DailySales monthDailySales = ListUtils.findEntityByFieldValue(list, "workerId",  worker.getId() + "");
            if(monthDailySales != null){
                dailySalesVo.setSaleAmount(monthDailySales.getSaleAmount());
                dailySalesVo.setProfile(monthDailySales.getProfile());
                dailySalesVo.setDailySaleAmount(monthDailySales.getSaleAmount());
                dailySalesVo.setDailyProfile(monthDailySales.getProfile());
            }
            dailySalesVoList.add(dailySalesVo);
        }
        sortDailySales(dailySalesVoList);
        for (int i = 0; i < dailySalesVoList.size(); i++) {
            dailySalesVoList.get(i).setIndex(i + 1);
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
