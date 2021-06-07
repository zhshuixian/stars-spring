package org.stars.spring.beans.factory.config;

/**
 * @author : xian
 */
public interface SingletonBeanRegistry {
    /**
     *  根据 bean name 获取对应的 bean
     * @param beanName  bean name
     * @return  bean
     */
    Object getSingleton(String beanName);
}
