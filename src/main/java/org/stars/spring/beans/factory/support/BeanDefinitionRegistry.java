package org.stars.spring.beans.factory.support;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.config.BeanDefinition;

/**
 * 注册 BeanDefinition
 *
 * @author : xian
 */
public interface BeanDefinitionRegistry {
    /**
     * 注册 BeanDefinition
     *
     * @param beanName       bean name
     * @param beanDefinition beanDefinition
     */
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 根据 bean 名称获取 bean 配置 BeanDefinition
     *
     * @param beanName bean 名称
     * @return bean 对应的配置
     * @throws BeansException bean 的异常
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 这个 bean 名称是否已经注册过了
     *
     * @param beaName bean 名
     * @return 已经注册过了则返回 true
     */
    boolean containsBeanDefinition(String beaName);

    /**
     * 返回所有注册的 bean 名称
     *
     * @return 所有注册的 bean 名称
     */
    String[] getBeanDefinitionNames();
}
