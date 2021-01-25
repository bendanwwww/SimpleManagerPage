//package com.manager.conf.db;
//
///**
// * 线程环境容器
// *
// * @author zhangyt
// */
//public class DynamicDataSourceContextHolder {
//
//    /*
//    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>() {
//        @Override
//        protected String initialValue() {
//            return "masterDataSource";
//        }
//    };
//    */
//
//    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
//
//    private static final ThreadLocal<String> currentMethodHolder = new ThreadLocal<String>();
//
//    public static String getDataSourceKey() {
//        return contextHolder.get();
//    }
//
//    public static void setDataSourceKey(String key) {
//        contextHolder.set(key);
//    }
//
//    public static void clearDataSourceKey() {
//        contextHolder.remove();
//    }
//
//    public static void setCurrentMethod(String method) {
//        currentMethodHolder.set(method);
//    }
//
//    public static String getCurrentMethod() {
//        return currentMethodHolder.get();
//    }
//
//    public static void clearCurrentMethod() {
//        currentMethodHolder.remove();
//    }
//
//}
