package org.stars.spring.context.support;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.config.BeanPostProcessor;
import org.stars.spring.context.ApplicationContext;
import org.stars.spring.context.ApplicationContextAware;

/**
 * @author : xian
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
