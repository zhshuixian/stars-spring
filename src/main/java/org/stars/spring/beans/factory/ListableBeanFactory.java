package org.stars.spring.beans.factory;

import org.stars.spring.beans.BeansException;

import java.util.Map;

/**
 * BeanFactory 的扩展，可以枚举所有 bean 实例的工厂，那些需要预加载所有
 * 的 BeanDefinition（例如基于 XML 的工厂） 的 BeanFactory 可能会实现这个接口
 * @author : xian
 */
public interface ListableBeanFactory extends BeanFactory{
    /**
     * 根据 class 类型返回 Bean 实例
     * @param type 类型
     * @param <T>  泛型
     * @return     所有同类型的 Bean 集合
     * @throws BeansException  beans 异常
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回所有注册的 bean 名称
     * @return  所有注册的 bean 名称
     */
    String[] getBeanDefinitionNames();
}
