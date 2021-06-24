package org.stars.spring.beans.factory.config;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.ConfigurableListableBeanFactory;

/**
 * Allows for custom modification of an application context's bean definitions,
 * adapting the bean property values of the context's underlying bean factory.
 * <p>
 * 允许自定义修改 BeanDefinition 属性的接口，调整 BeanFactory 上下文的 bean 属性值
 *
 * @author : xian
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有 BeanDefinition 加载完成后，实例化之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory bean工厂
     * @throws BeansException bean 异常
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
