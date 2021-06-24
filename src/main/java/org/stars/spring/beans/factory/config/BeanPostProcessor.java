package org.stars.spring.beans.factory.config;

import org.stars.spring.beans.BeansException;

/**
 * Factory hook that allows for custom modification of new bean instances,
 * e.g. checking for marker interfaces or wrapping them with proxies.
 * <p>
 * bean 对象初始化前后的修改扩展点。例如可以标记接口或者用代理包装这些对象
 *
 * @author : xian
 */
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化前，执行此方法
     *
     * @param bean     bean 对象
     * @param beanName bean 名称
     * @return 初始化处理之前的 bean 对象
     * @throws BeansException bean 异常
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在 bean 对象初始化之后执行的操作
     *
     * @param bean     bean 对象
     * @param beanName bean 名称
     * @return 初始化处理之前的 bean 对象
     * @throws BeansException bean 异常
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}
