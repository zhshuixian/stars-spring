package org.stars.spring.beans.factory.support;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.config.BeanDefinition;

/**
 * 初始化 bean 的实例
 * @author : xian
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition definition) throws BeansException {
        Object bean = null;
        try{
            bean = definition.getBean().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        addSingletonObject(beanName, bean);
        return bean;
    }
}
