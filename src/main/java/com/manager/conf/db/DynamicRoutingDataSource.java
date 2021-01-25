//package com.manager.conf.db;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//
///**
// * 动态数据源路由
// *
// * @author zhangyt
// */
//public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
//
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Override
//    protected Object determineCurrentLookupKey() {
//        logger.debug("Current DataSource is [{}]", com.manager.conf.db.DynamicDataSourceContextHolder.getDataSourceKey());
//        return com.manager.conf.db.DynamicDataSourceContextHolder.getDataSourceKey();
//    }
//
//}
