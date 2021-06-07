package org.stars.spring;

import org.stars.spring.beans.factory.config.BeanDefinition;
import org.stars.spring.beans.factory.support.DefaultListableBeanFactory;
import org.stars.spring.test.UserService;

/**
 * @author : xian
 */
public class ApiTest {
    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        factory.registryBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService)factory.getBean("userService");
        userService.queryUserInfo();
    }
}
