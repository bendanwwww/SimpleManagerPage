package com.manager.manager.service;

import com.manager.manager.common.Page;
import com.manager.manager.common.ResultVo;
import com.manager.manager.domain.CityInfo;
import com.manager.manager.domain.Worker;
import com.manager.manager.dto.WorkerDto;
import com.manager.manager.mapper.CityInfoMapper;
import com.manager.manager.mapper.WorkerMapper;
import com.manager.manager.vo.CityInfoVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 职工相关service
 *
 * @author lsy
 * Created on 2021-01-25
 */
@Service
public class WorkerService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private WorkerMapper workerMapper;
    @Autowired
    private CityInfoMapper cityInfoMapper;

    /**
     * 查询职工列表
     * @param id
     * @param cityCode
     * @param sysName
     * @param startNo
     * @param pageSize
     * @return
     */
    public List<Worker> queryList(Integer id, String cityCode, String sysName, Integer startNo, Integer pageSize) {
        return workerMapper.queryWorkerList(id, cityCode, sysName, startNo, pageSize);
    }

    /**
     * 查询职工列表
     * @param sysName
     * @return
     */
    public Worker queryWorkerBySysName(String sysName) {
        List<Worker> workers = workerMapper.queryWorkerBySysName(sysName);
        if(CollectionUtils.isEmpty(workers)) {
            return new Worker();
        }
        return workers.get(0);
    }

    /**
     * 修改员工密码
     * @param sysName
     * @return
     */
    public boolean changePassword(String sysName, String password, String newPassword) {
        List<Worker> workers = workerMapper.queryWorkerBySysName(sysName);
        if(CollectionUtils.isEmpty(workers)) {
            return false;
        }
        Worker worker = workers.get(0);
        if(!passwordEncoder.matches(password, worker.getPassword())) {
            return false;
        }
        worker.setPassword(passwordEncoder.encode(newPassword));
        workerMapper.updatePassword(worker);
        return true;
    }

    /**
     * @description: 新增员工信息
     * @author: mengwenyi
     * @date: 2021/2/7 16:44
     */
    public void insertWorker(Worker worker) {
        CityInfo cityInfo = cityInfoMapper.queryCityByCityCode(worker.getCityCode());
        if(cityInfo != null){
            worker.setCityName(cityInfo.getCityName());
        }
        // 初始化员工密码
        worker.setPassword(passwordEncoder.encode("123456"));
        workerMapper.insertWorker(worker);
    }
    /**
     * @description: 更新员工信息
     * @author: mengwenyi
     * @date: 2021/2/7 16:43
     */
    public void updateWorker(Worker worker) {
        CityInfo cityInfo = cityInfoMapper.queryCityByCityCode(worker.getCityCode());
        if(cityInfo != null){
            worker.setCityName(cityInfo.getCityName());
        }
        workerMapper.updateWorker(worker);
    }
    /**
     * @description: 删除员工
     * @author: mengwenyi
     * @date: 2021/2/7 16:43
     */
    public void delWorker(int id) {
        workerMapper.delWorker(id);
    }
    /**
     * @description: 查询员工详情
     * @author: mengwenyi
     * @date: 2021/2/7 16:50
     */
    public Worker queryWorkerById(int id){
        return workerMapper.queryWorkerById(id);
    }
    /**
     * @description:
     * @author: mengwenyi
     * @date: 2021/2/7 16:50
     */ 
    public ResultVo queryWorkerPage(WorkerDto workerDto) {
        int count = workerMapper.queryWorkerPageCount(workerDto);
        Page page = new Page();
        if(count <= 0){
            page.setRows(new ArrayList<>());
            page.setTotal(count);
            return ResultVo.build(() -> page);
        }else{
            List<Worker> list = workerMapper.queryWorkerPage(workerDto);
            page.setRows(list);
            page.setTotal(count);
            return ResultVo.build(() -> page);
        }

    }

    public List<Map<String, Object>> queryWorkerOptions() {
        List<Map<String, Object>> resList = new ArrayList<>();
        List<Worker> workerList = workerMapper.queryWorkerList(null, null, null, null, null);
        if(CollectionUtils.isEmpty(workerList)) {
            return resList;
        }
        resList = workerList.stream().map(w -> {
            Map<String, Object> tmpMap = new HashMap<>();
            tmpMap.put("label", w.getRealName());
            tmpMap.put("value", w.getId());
            return tmpMap;
        }).collect(Collectors.toList());
        return resList;
    }
}
