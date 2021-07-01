package org.stars.spring.beans.factory.config;

import org.stars.spring.beans.factory.HierarchicalBeanFactory;

/**
 * Configuration interface to be implemented by most bean factories. Provides
 * facilities to configure a bean factory, in addition to the bean factory
 * client methods in the {@link org.stars.spring.beans.factory.BeanFactory}
 * interface.
 * <p>
 * 工厂的配置接口
 *
 * @author xian
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    /** 单例模式 */
    String SCOPE_SINGLETON = "singleton";
    /** 原型模式 */
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 配置 BeanPostProcessor 接口实现的方法
     *
     * @param beanPostProcessor bean 对象初始化前后的修改扩展功能接口的实现
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * Destroy all singleton beans in this factory, including inner beans that have
     * been registered as disposable. To be called on shutdown of a factory.
     * <p>Any exception that arises during destruction should be caught
     * and logged instead of propagated to the caller of this method.
     */
    void destroySingletons();

    /**
     * 获取bean 的 class Loader
     * @return ClassLoader
     */
    ClassLoader getBeanClassLoader();
}
