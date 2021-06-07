package org.stars.spring.beans.factory.support;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.BeanFactory;
import org.stars.spring.beans.factory.config.BeanDefinition;

/**
 * @author : xian
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object bean = getSingleton(beanName);
        if(bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);

        return createBean(beanName, beanDefinition);
    }

    /** 根据 bean name 获取到  BeanDefinition */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /** 创建 bean */
    protected abstract Object createBean(String beanName, BeanDefinition definition) throws BeansException;

}
