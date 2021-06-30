package org.stars.spring.beans.factory;

import org.stars.spring.beans.BeansException;

/**
 * @author : xian
 */
public interface BeanFactoryAware extends Aware{
    /**
     * interface to be implemented by beans that wish to be aware of their owning {@link BeanFactory}.
     * 实现此接口，可以感知到所属的 BeanFactory
     * @param beanFactory     bean 工厂
     * @throws BeansException bean 异常
     */
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
