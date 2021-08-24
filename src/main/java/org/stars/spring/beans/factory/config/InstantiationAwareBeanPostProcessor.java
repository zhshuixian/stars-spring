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
    default Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }


    /**
     * Perform operations after the bean has been instantiated, via a constructor or factory method,
     * but before Spring property population (from explicit properties or autowiring) occurs.
     * <p>
     * This is the ideal callback for performing field injection on the given bean instance.
     * See Spring's own {@link org.stars.spring.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor}
     * for a typical example.
     * <p>
     * 在 bean 对象初始化方法后执行此方法
     *
     * @param bean     bean 对象
     * @param beanName bean 名称
     * @return 修改后的 bean
     * @throws BeansException 可能出现的异常
     */
    default boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

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


    /**
     * 在 Spring 中由 SmartInstantiationAwareBeanPostProcessor#getEarlyBeanReference 提供
     *
     * @param bean
     * @param beanName
     * @return
     */
    default Object getEarlyBeanReference(Object bean, String beanName) {
        return bean;
    }
}
