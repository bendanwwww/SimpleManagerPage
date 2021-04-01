package com.manager.manager.mapper;

import com.manager.manager.common.Page;
import com.manager.manager.domain.Worker;
import com.manager.manager.dto.WorkerDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WorkerMapper {

    List<Worker> queryWorkerList(@Param(value = "id") Integer id,
                                 @Param(value = "cityCode") String cityCode,
                                 @Param(value = "sysName") String sysName,
                                 @Param(value = "startNo") Integer startNo,
                                 @Param(value = "pageSize") Integer pageSize);

    List<Worker> queryWorkerBySysName(@Param(value = "sysName") String sysName);

    Worker queryWorkerByWX(@Param(value = "wxCard") String wxCard);

    void insertWorker(Worker worker);

    void updateWorker(Worker worker);

    void updatePassword(Worker worker);

    void delWorker(@Param(value = "id") int id);
    /**
     * @description: 通过id查询员工信息
     * @author: mengwenyi
     * @date: 2021/2/7 15:45
     */
    Worker queryWorkerById(@Param(value = "id") int id);
    /**
     * @description:
     * @author: mengwenyi
     * @date: 2021/2/7 16:52
     */
    List<Worker> queryWorkerPage(WorkerDto workerDto);
    /**
     * @description:
     * @author: mengwenyi
     * @date: 2021/2/7 17:05
     */ 
    int queryWorkerPageCount(WorkerDto workerDto);
    /**
     * @description: 查询员工列表
     * @author: mengwenyi
     * @date: 2021/2/18 19:38
     */
    List<Worker> queryWorkerByIdList(@Param(value = "idList") List idList);
    /**
     * @description: 查询全部员工列表
     * @author: mengwenyi
     * @date: 2021/4/1 17:52
     */
    List<Worker> queryAllWorkerList();

}