package org.stars.spring.beans.factory.config;

import org.stars.spring.beans.BeansException;

/**
 * @author : xian
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * Apply this BeanPostProcessor <i>before the target bean gets instantiated</i>.
     * The returned bean object may be a proxy to use instead of the target bean,
     * effectively suppressing default instantiation of the target bean.
     * <p>
     * 在 Bean 对象执行初始化方法之前，执行此方法
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    /**
     * <p>
     * Post-process the given property values before the factory applies them
     * to the given bean. Allows for checking whether all dependencies have been
     * satisfied, for example based on a "Required" annotation on bean property setters.
     * </p>
     * 在 Bean 对象实例化完成后，设置属性操作之前执行此方法
     *
     * @param propertyValues 原始的 bean PropertyValues
     * @param bean           bean 实例
     * @param beanName       bean 名称
     * @return 修改后 的PropertyValues
     * @throws BeansException 可能的 Bean 异常
     */
    PropertyValues postProcessPropertyValues(PropertyValues propertyValues, Object bean, String beanName) throws BeansException;

}
