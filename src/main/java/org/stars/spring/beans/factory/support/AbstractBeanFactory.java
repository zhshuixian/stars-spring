package org.stars.spring.beans.factory.support;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.BeanFactory;
import org.stars.spring.beans.factory.config.BeanDefinition;

/**
 * 抽象的模板方法
 * @author : xian
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeansException {
        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return doGetBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> clazz) throws BeansException {
        return doGetBean(beanName, null);
    }

    protected <T> T doGetBean(final String beanName, final Object[] args){
        Object bean = getSingleton(beanName);
        if(bean != null){
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return (T) createBean(beanName, beanDefinition, args);
    }

    /** 根据 bean name 获取到  BeanDefinition */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /** 创建 bean */
    protected abstract Object createBean(String beanName, BeanDefinition definition, Object[] args) throws BeansException;
}
