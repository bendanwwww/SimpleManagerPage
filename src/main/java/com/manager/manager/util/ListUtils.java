package com.manager.manager.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @description: 集合处理工具类
 * @author: sxy
 * @date: 2018/4/25 上午9:48
 */
public class ListUtils {
    /**
     * @description: 提取集合对象中的某一个属性
     * @param: [lists, fieldName]
     * @return: java.util.List<E>
     * @author: sxy
     * @date: 2018/4/25 上午9:48
     * @throws:
     */
    public static <T, E> List<E> fetchFieldValue(List<T> lists, String fieldName) {
        if (isEmpty(lists)) return null;
        List<E> results = new ArrayList<>();
        Iterator<T> iterator = lists.iterator();
        T entity = null;
        while (iterator.hasNext()) {
            entity = iterator.next();
            E value = (E) getProperty(entity, fieldName);
            if (value != null) {
                results.add(value);
            }
        }
        return results;
    }

    /**
     * @description: 提取集合对象中的某一个不重复属性
     * @param: [lists, fieldName]
     * @return: java.util.List<E>
     * @author: sxy
     * @date: 2018/9/17 下午7:17
     * @throws:
     */
    public static <T, E> List<E> fetchDiffFieldValue(List<T> lists, String fieldName) {
        if (isEmpty(lists)) return null;
        Set<E> results = new HashSet<>();
        Iterator<T> iterator = lists.iterator();
        T entity = null;
        while (iterator.hasNext()) {
            entity = iterator.next();
            E value = (E) getProperty(entity, fieldName);
            if (value != null) {
                results.add(value);
            }
        }
        return new ArrayList<>(results);
    }

    /**
     * @description: 根据属性字段名和属性value值返回model
     * @param: [lists, fieldName, value]
     * @return: T
     * @author: sxy
     * @date: 2018/4/25 上午9:49
     * @throws:
     */
    public static <T> T findEntityByFieldValue(List<T> lists, String fieldName, String value) {
        if (isEmpty(lists)) return null;
        Iterator<T> iterator = lists.iterator();
        T entity = null;
        while (iterator.hasNext()) {
            entity = iterator.next();
            Object object = null;
            object = getProperty(entity, fieldName);
            if (object != null && object.toString().equals(value)) {

                return entity;
            }
        }
        return null;
    }

    /**
     * @throws:
     * @description: 通过实例的字段获取value值, 通过value值去集合中进行查询相应的数据
     * @param: [list, entity, fieldNames]
     * @return: T
     * @author: sxy
     * @date: 2018/4/25 上午9:49
     */
    public static <T> T findEntityByFieldValue(List<T> list, T
            entity, String... fieldNames) {
        String[] values = new String[fieldNames.length];
        for (int index = 0; index < fieldNames.length; index++) {
            values[index] = (String) ListUtils.getProperty(entity, fieldNames[index]);
        }
        return ListUtils.findEntityByFieldValue(list, fieldNames,
                values);
    }

    /**
     * @throws:
     * @description: 根据属性字段名和属性value值返回model
     * @param: [lists, fieldNames, values]
     * @return: T
     * @author: sxy
     * @date: 2018/4/25 上午9:50
     */
    public static <T> T findEntityByFieldValue(List<T> lists, String[] fieldNames, String[] values) {
        if (isEmpty(lists)) return null;
        Iterator<T> iterator = lists.iterator();
        T entity = null;
        while (iterator.hasNext()) {
            entity = iterator.next();
            Object object = null;
            boolean flag = true;
            for (int i = 0; i < fieldNames.length; i++) {
                String fieldName = fieldNames[i];
                String value = values[i];
                object = getProperty(entity, fieldName);
                if (object == null || !object.toString().equals(value)) {
                    flag = false;
                    break;
                }
            }
            if (flag) return entity;
        }
        return null;
    }

    /**
     * @description: 根据属性字段名和属性value值返回list
     * @param: [lists, fieldName, value]
     * @return: java.util.List<T>
     * @author: sxy
     * @date: 2018/4/25 上午9:50
     * @throws:
     */
    public static <T> List<T> findListByFieldValue(List<T> lists, String fieldName, String value) {
        List<T> result = new ArrayList<>();
        if (isEmpty(lists)) {
            return result;
        }
        Iterator<T> iterator = lists.iterator();
        while (iterator.hasNext()) {
            T entity = iterator.next();
            Object object = getProperty(entity, fieldName);
            if (object != null && object.toString().equals(value)) {
                result.add(entity);
            }
        }
        return result;
    }

    /**
     * @description: 根据属性字段名和属性value集合值返回list
     * @param: [lists, fieldName, values]
     * @return: java.util.List<T>
     * @author: sxy
     * @date: 2018/5/31 上午10:01
     * @throws:
     */
    public static <T> List<T> findListByFieldValues(List<T> lists, String fieldName, String[] values) {
        List<T> result = new ArrayList<>();
        if (isEmpty(lists)) {
            return result;
        }
        Iterator<T> iterator = lists.iterator();
        while (iterator.hasNext()) {
            T entity = iterator.next();
            Object object = getProperty(entity, fieldName);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                if (object != null && object.toString().equals(value)) {
                    result.add(entity);
                }
            }
        }
        return result;
    }

    /**
     * @description: 过滤不包含的属性
     * @param: [lists, fieldName, value]
     * @return: java.util.List<T>
     * @author: sxy
     * @date: 2018/4/25 上午10:03
     * @throws:
     */
    public static <T> List<T> excludeFieldValue(List<T> lists, String fieldName, String value) {
        List<T> result = new ArrayList<>();
        if (isEmpty(lists)) {
            return result;
        }
        Iterator<T> iterator = lists.iterator();
        while (iterator.hasNext()) {
            T entity = iterator.next();
            Object object = getProperty(entity, fieldName);
            if (object == null || !object.toString().equals(value)) {
                result.add(entity);
            }
        }
        return result;
    }

    /**
     * @description: 提取集合对象中的某一个属性 根据属性字段名
     * @param: [lists, fieldName]
     * @return: java.util.List<E>
     * @author: sxy
     * @date: 2018/4/25 上午9:55
     * @throws:
     */
    public static <T, E> List<E> findListByFieldName(List<T> lists, String fieldName) {
        List<E> result = new ArrayList<>();
        if (isEmpty(lists)) {
            return result;
        }
        Iterator<T> iterator = lists.iterator();
        while (iterator.hasNext()) {
            T entity = iterator.next();
            Object object = getProperty(entity, fieldName);
            if (object != null && object instanceof List) {
                List<E> childList = (List<E>) object;
                result.addAll(childList);
            }
        }
        return result;
    }

    /**
     * @description: copy list中的对象到一个新对象中
     * @param: [lists, targetClass]
     * @return: java.util.List<T>
     * @author: sxy
     * @date: 2018/4/25 上午9:54
     * @throws:
     */
    public static <S, T> List<T> copyList(List<S> lists, Class<T> targetClass) {
        try {
            List<T> copys = new ArrayList<>();
            if (isEmpty(lists)) {
                return copys;
            }
            for (S sourceClass : lists) {
                T target = targetClass.newInstance();
                BeanUtils.copyBean(sourceClass, target);
                copys.add(target);
            }
            return copys;
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    /**
     * @description: 通过属性判断A集合的数据是否存在于B集合中
     * @param: [sources, targets, fieldName]
     * @return: java.util.List<T>
     * @author: sxy
     * @date: 2018/4/25 上午9:54
     * @throws:
     */
    public static <T> List<T> findDiffEntityByFieldValue(List<T> sources, List<T> targets, String fieldName) {
        List<T> diffs = new ArrayList<>();
        for (T source : sources) {
            T entity = ListUtils.findEntityByFieldValue(targets, fieldName, (String) getProperty(source, fieldName));
            if (entity == null) {
                diffs.add(source);
            }
        }
        return diffs;
    }

    /**
     * @description: 通过反射获取value值
     * @param: [entity, fieldName]
     * @return: java.lang.Object
     * @author: sxy
     * @date: 2018/4/25 上午9:54
     * @throws:
     */
    public static <T> Object getProperty(T entity, String fieldName) {
        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, entity.getClass());
            Method method = propertyDescriptor.getReadMethod();
            Object object = method.invoke(entity);
            return object;
        } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
            return null;
        }
    }

    /**
     * @description: 判断集合是否不为null
     * @param: [collection]
     * @return: boolean
     * @author: sxy
     * @date: 2018/4/25 上午9:53
     * @throws:
     */
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * @description: 将list按照指定的分隔符转为字符串
     * @param: [list, separator]
     * @return: String
     * @author: sxy
     * @date: 2018/4/22 下午3:49
     * @throws:
     */
    public static String listToString(List list, String separator) {
        if (isEmpty(list) || separator == null) return null;
        StringBuffer buffer = new StringBuffer();
        buffer.append(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            buffer.append(separator + list.get(i));
        }
        return buffer.toString();
    }

    /**
     * @description: 根据属性名和属性值移除某个元素
     * @param: [lists, fieldName, value]
     * @return: void
     * @author: sxy
     * @date: 2018/5/1 下午4:40
     * @throws:
     */
    public static <T> void removeEntityByFieldValue(List<T> lists, String fieldName, String value) {
        if (isEmpty(lists)) {
            return;
        }
        Iterator<T> iterator = lists.iterator();
        while (iterator.hasNext()) {
            T entity = iterator.next();
            Object object = getProperty(entity, fieldName);
            if (object != null && object.toString().equals(value)) {
                iterator.remove();
            }
        }
    }

    // 删除ArrayList中重复元素，保持顺序
    public static void removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
    }

    public static <T> List<T> removeSameEntityByFieldValue(List<T> sources, List<T> targets, String fieldName) {

        Iterator<T> it = sources.iterator();

        while(it.hasNext()) {
            T source = it.next();
            T entity = findEntityByFieldValue(targets, fieldName, (String)getProperty(source, fieldName));
            if(entity != null) {
                targets.remove(entity);
            }
        }

        return targets;
    }

}
