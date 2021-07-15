package org.stars.spring.beans.factory.config;

/**
 * Spring 容器的 单例注册接口
 *
 * @author : xian
 */
public interface SingletonBeanRegistry {
    /**
     * 根据 bean name 获取对应的 bean
     *
     * @param beanName bean name
     * @return bean
     */
    Object getSingleton(String beanName);

    /**
     * 缓存单例的 bean
     *
     * @param beanName        bean 名称
     * @param singletonObject 单例对象
     */
    void registerSingleton(String beanName, Object singletonObject);
}
