package org.stars.spring.beans.factory.config;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.BeanFactory;

/**
 * Extension of the {@link org.stars.spring.beans.factory.BeanFactory}
 * interface to be implemented by bean factories that are capable of
 * autowiring, provided that they want to expose this functionality for
 * existing bean instances.
 *
 * BeanFactory 的扩展功能，
 *
 * @author : xian
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行 BeanPostProcessor  接口实现类的 postProcessBeforeInitialization 方法
     * @param existingBean    未初始化的 bean 实例
     * @param beanName        bean 名称
     * @return                经过 BeanPostProcessor 接口实现类处理后的 bean 实例
     * @throws BeansException bean 异常
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行 BeanPostProcessor  接口实现类的 postProcessAfterInitialization 方法
     * @param existingBean    初始化完成后 bean 实例
     * @param beanName        bean 名称
     * @return                经过 BeanPostProcessor 接口实现类处理后的 bean 实例
     * @throws BeansException bean 异常
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
