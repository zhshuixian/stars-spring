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

    /**
     * 这个 bean 名称是否已经注册过了
     * @param beaName   bean 名
     * @return          已经注册过了则返回 true
     */
    Boolean containsBeanDefinition(String beaName);
}
