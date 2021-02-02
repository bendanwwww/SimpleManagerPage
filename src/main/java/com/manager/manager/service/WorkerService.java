package com.manager.manager.service;

import com.manager.manager.domain.CityInfo;
import com.manager.manager.domain.Worker;
import com.manager.manager.mapper.CityInfoMapper;
import com.manager.manager.mapper.WorkerMapper;
import com.manager.manager.vo.CityInfoVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    private WorkerMapper workerMapper;

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

    public void insertWorker(Worker worker) {
        workerMapper.insertWorker(worker);
    }

    public void updateWorker(Worker worker) {
        workerMapper.updateWorker(worker);
    }

    public void delWorker(int id) {
        workerMapper.delWorker(id);
    }
}
