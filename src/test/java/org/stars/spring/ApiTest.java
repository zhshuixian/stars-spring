package org.stars.spring;

import org.junit.Test;
import org.stars.spring.beans.factory.config.BeanDefinition;
import org.stars.spring.beans.factory.support.DefaultListableBeanFactory;
import org.stars.spring.beans.UserService;

/**
 * @author : xian
 */
public class ApiTest {

    @Test
    public void testBeanFactory(){
        // BeanFactory 初始化
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 注入 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        factory.registryBeanDefinition("userService", beanDefinition);
        // 获取 bean
        UserService service = (UserService) factory.getBean("userService", "猪八戒");
        service.queryUserInfo();


    }

}
