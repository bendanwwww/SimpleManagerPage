package com.manager.manager.mapper;

import com.manager.manager.domain.Worker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WorkerMapper {

    List<Worker> queryWorkerList(@Param(value = "id") String id,
                                 @Param(value = "cityCode") String cityCode,
                                 @Param(value = "sysName") String sysName,
                                 @Param(value = "startNo") Integer startNo,
                                 @Param(value = "pageSize") Integer pageSize);

    Worker queryWorkerByWX(@Param(value = "wxCard") String wxCard);

    void insertWorker(Worker worker);

    void updateWorker(Worker worker);

    void delWorker(@Param(value = "id") String id);
}