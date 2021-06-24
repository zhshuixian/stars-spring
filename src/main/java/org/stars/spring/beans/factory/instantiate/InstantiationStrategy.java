package org.stars.spring.beans.factory.instantiate;

import org.stars.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 定义实例化的策略接口
 *
 * @author : xian
 */
public interface InstantiationStrategy {
    /**
     * 实例化接口
     *
     * @param beanDefinition bean 的 class 类信息
     * @param beanName       bean name
     * @param constructor    bean 的构造函数
     * @param args           构造函数的参数
     * @return bean 的实例
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object[] args);
}
