package org.stars.spring.beans.factory.support;

import org.stars.spring.beans.factory.config.BeanDefinition;

/**
 * 注册 BeanDefinition
 * @author : xian
 */
public interface BeanDefinitionRegistry {
    /**
     * 注册 BeanDefinition
     * @param beanName       bean name
     * @param beanDefinition beanDefinition
     */
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
