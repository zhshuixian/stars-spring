package org.stars.spring.beans.factory;

import org.stars.spring.beans.BeansException;

import java.util.Map;

/**
 * @author : xian
 */
public interface ListableBeanFactory extends BeanFactory {
    /**
     * 根据 class 类型返回 Bean 实例
     *
     * @param type 类型
     * @param <T>  泛型
     * @return 所有同类型的 Bean 集合
     * @throws BeansException beans 异常
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回所有注册的 bean 名称
     *
     * @return 所有注册的 bean 名称
     */
    String[] getBeanDefinitionNames();
}
