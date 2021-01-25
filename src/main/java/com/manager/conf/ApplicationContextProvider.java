package com.manager.conf;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Description
 *
 * @author tangyuxin
 * @date 2019/1/28 13:19
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ApplicationContextProvider  implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * Description：根据name获取Bean对象
     *
     * @param name
     * @return
     */
    public Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * Description：根据类型获取Bean对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * Description：根据name和类型或缺Bean对象
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }
}
