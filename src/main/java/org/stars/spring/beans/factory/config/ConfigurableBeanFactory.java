package org.stars.spring.beans.factory.config;

import org.stars.spring.beans.factory.HierarchicalBeanFactory;

/**
 * Configuration interface to be implemented by most bean factories. Provides
 * facilities to configure a bean factory, in addition to the bean factory
 * client methods in the {@link org.stars.spring.beans.factory.BeanFactory}
 * interface.
 *
 * 工厂的配置接口
 * @author as190
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 配置 BeanPostProcessor 接口实现的方法
     * @param beanPostProcessor bean 对象初始化前后的修改扩展功能接口的实现
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
