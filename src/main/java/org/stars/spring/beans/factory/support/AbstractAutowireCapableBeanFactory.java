package org.stars.spring.beans.factory.support;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.config.BeanDefinition;
import org.stars.spring.beans.factory.instantiate.CglibSubclassingInstantiationStrategy;
import org.stars.spring.beans.factory.instantiate.InstantiationStrategy;

import java.lang.reflect.Constructor;

/**
 * 初始化 bean 的实例
 * @author : xian
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition definition, Object[] args) throws BeansException {
        Object bean = createBeanInstance(definition, beanName, args);
        addSingletonObject(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args){
        Constructor<?> constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for(Constructor<?> constructor: declaredConstructors){
            if(null != args && constructor.getParameterTypes().length == args.length){
                constructorToUse = constructor;
            }
        }

        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
