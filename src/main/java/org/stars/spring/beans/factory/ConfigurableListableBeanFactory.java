package org.stars.spring.beans.factory;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.config.AutowireCapableBeanFactory;
import org.stars.spring.beans.factory.config.BeanDefinition;
import org.stars.spring.beans.factory.config.BeanPostProcessor;
import org.stars.spring.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author : xian
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 根据 bean 名称获取 bean 配置 BeanDefinition
     *
     * @param beanName bean 名称
     * @return bean 对应的配置
     * @throws BeansException bean 的异常
     **/
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 提前实例化 bean
     * @throws BeansException bean 异常
     */
    void preInstantiateSingletons() throws BeansException;

    /**
     * 配置 BeanPostProcessor 接口实现的方法
     * @param beanPostProcessor bean 对象初始化前后的修改扩展功能接口的实现
     */
     void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
