package com.manager.manager.util;


import org.springframework.cglib.beans.BeanCopier;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhangx
 * @date: 2018/1/31 10:54
 * @description:bean 工具类
 */
public class BeanUtils {



    /**
     * @throws
     * @description:对象转map
     * @param: [instance]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: zhangx
     * @date: 2018/1/31 10:54
     */
    public static <T> Map<String, Object> bean2Map(T instance) {
        Map<String, Object> body = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(instance.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : descriptors) {
                String propertyName = descriptor.getName();
                if (!"class".equals(propertyName)) {
                    Method method = descriptor.getReadMethod();
                    Object result = method.invoke(instance);
                    if (result != null) body.put(propertyName, result);
                }
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return body;
    }

    /**
     * @throws
     * @description: 对象copy 通过字节码进行对象复制
     * @param: [source, target]
     * @return: void
     * @author: zhangx
     * @date: 2018/1/31 10:55
     */
    public static void copyBean(Object source, Object target) {
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
    }



    public static String getObjectMethodName(String code, String property) {
        String methodName = property.substring(0, 1).toUpperCase() + property.substring(1);
        return code + methodName;
    }

}
