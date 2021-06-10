package org.stars.spring;

import org.junit.Test;
import org.stars.spring.beans.UserDao;
import org.stars.spring.beans.factory.config.BeanDefinition;
import org.stars.spring.beans.factory.config.BeanReference;
import org.stars.spring.beans.factory.config.PropertyValue;
import org.stars.spring.beans.factory.config.PropertyValues;
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
        // 注册 UserDao
        factory.registryBeanDefinition("userDao", new BeanDefinition(UserDao.class));
        // 注册 UserService
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "1001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        factory.registryBeanDefinition("userService", beanDefinition);

        // 获取 bean
        UserService service = (UserService) factory.getBean("userService");
        service.queryUserInfo();


    }

}
