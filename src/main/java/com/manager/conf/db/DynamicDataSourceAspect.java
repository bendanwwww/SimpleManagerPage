package com.manager.conf.db;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * data source aspect
 *
 * @author zhangyt
 */
@Aspect
@Order(-1)
@Component
@ConfigurationProperties(prefix = "application.db.dbroute")
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(com.manager.conf.db.DynamicDataSourceAspect.class);

    private static final String DBROUTE_TYPE_TX = "tx";
    private static final String DBROUTE_TYPE_NAME = "name";
    private static final String[] QUERY_PREFIXS = new String[]{"select", "query", "get"};

    private String dbrouteType;

    @Pointcut("execution( * com.xes.cloudlearn.*.*.service..*.*(..))")
    public void serviceAspect() {
    }

    @Before("serviceAspect()")
    public void switchDataSource(JoinPoint point) {

        if (DynamicDataSourceContextHolder.getCurrentMethod() == null) {
            DynamicDataSourceContextHolder.setCurrentMethod(point.getSignature().toString());
        } else {
            return;
        }

        if (dbrouteType == null || dbrouteType.equals("")) {
            dbrouteType = DBROUTE_TYPE_TX;
        }

        if (dbrouteType.equals(DBROUTE_TYPE_TX)) {
            routeByTransactionalAnotation(point);
        } else if (dbrouteType.equals(DBROUTE_TYPE_NAME)) {
            routeByMethodName(point);
        }

    }

    private void routeByTransactionalAnotation(JoinPoint point) {

        Transactional transactional = ((MethodSignature) point.getSignature()).getMethod().getAnnotation(Transactional.class);

        if (transactional != null) {
            if (transactional.propagation() == Propagation.REQUIRED || transactional.propagation() == Propagation.NOT_SUPPORTED) {
                DynamicDataSourceContextHolder.setDataSourceKey("masterDataSource");
                logger.debug("Set DataSource to [{}] in Method [{}]",
                        DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
            } else {
                DynamicDataSourceContextHolder.setDataSourceKey("slaveDataSource");
                logger.debug("Set DataSource to [{}] in Method [{}]",
                        DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
            }
        } else {
            DynamicDataSourceContextHolder.setDataSourceKey("slaveDataSource");
            logger.debug("Set DataSource to [{}] in Method [{}]",
                    DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
        }

    }

    private void routeByMethodName(JoinPoint point) {
        String methodName = point.getSignature().getName();
        boolean slaveSetted = false;
        for (String prefix : QUERY_PREFIXS) {
            if (methodName.startsWith(prefix)) {
                DynamicDataSourceContextHolder.setDataSourceKey("slaveDataSource");
                logger.debug("Set DataSource to [{}] in Method [{}]",
                        DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
                slaveSetted = true;
                break;
            }
        }

        if (!slaveSetted) {
            DynamicDataSourceContextHolder.setDataSourceKey("masterDataSource");
            logger.debug("Set DataSource to [{}] in Method [{}]",
                    DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
        }
    }

    @After("serviceAspect()")
    public void clearDataSource(JoinPoint point) {

        if (!point.getSignature().toString().equals(DynamicDataSourceContextHolder.getCurrentMethod())) {
            return;
        }
        DynamicDataSourceContextHolder.clearCurrentMethod();

        String datasourceKey = DynamicDataSourceContextHolder.getDataSourceKey();
        if (datasourceKey != null) {
            logger.debug("clear current data source key: {}", datasourceKey);
            DynamicDataSourceContextHolder.clearDataSourceKey();
        }
    }

    public String getDbrouteType() {
        return dbrouteType;
    }

    public void setDbrouteType(String dbrouteType) {
        this.dbrouteType = dbrouteType;
    }
}
