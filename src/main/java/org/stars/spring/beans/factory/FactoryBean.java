package org.stars.spring.beans.factory;

import org.stars.spring.beans.BeansException;

/**
 * @author : xian
 */
public interface FactoryBean<T> {
    /**
     * 获取 bean
     * @return bean
     * @throws BeansException bean异常
     */
    T getObject() throws BeansException;

    /**
     * T 的 Class
     * @return Class
     */
    Class<?> getObjectType();

    /**
     * 是否为单例模式
     * @return  单例 true
     */
    boolean isSingleton();
}
