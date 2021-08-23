package org.stars.spring.beans.factory;

import org.stars.spring.beans.BeansException;

/**
 * 就一个获取到 bean 实例的接口
 *
 * @author : xian
 */
public interface BeanFactory {
    /**
     * 获取 bean 的实例
     *
     * @param beanName bean name
     * @return Bean 实例
     * @throws BeansException Bean 的异常
     */
    Object getBean(String beanName) throws BeansException;

    /**
     * 有参数的构造函数，获取实例
     *
     * @param beanName bean name
     * @param args     可能有多个的 bean 构造参数
     * @return Bean 实例
     * @throws BeansException Bean 的异常
     */
    Object getBean(String beanName, Object... args) throws BeansException;

    <T> T getBean(String beanName, Class<T> clazz) throws BeansException;

    <T> T getBean(Class<T> requireType) throws BeansException;
}
