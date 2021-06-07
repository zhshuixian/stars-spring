package org.stars.spring;

import org.stars.spring.test.UserService;

/**
 * @author : xian
 */
public class ApiTest {
    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(new UserService());

        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService)beanFactory.getBean("userService");

        userService.queryUserInfo();
    }
}
