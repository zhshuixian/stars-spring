package org.stars.spring.common;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.UserService;
import org.stars.spring.beans.factory.config.BeanPostProcessor;

/**
 * @author : xian
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("userService".equals(beanName)){
            UserService userService = (UserService) bean;
            userService.setLocation("改为：太空");
        }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
