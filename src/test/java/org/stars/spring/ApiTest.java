package org.stars.spring;

import org.junit.Test;
import org.stars.spring.beans.UserService;
import org.stars.spring.beans.factory.support.DefaultListableBeanFactory;
import org.stars.spring.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author : xian
 */
public class ApiTest {

    @Test
    public void testBeanFactory(){
        // BeanFactory 初始化
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 使用 xml 配置 bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("classpath:spring.xml");


        // 获取 bean
        UserService service = factory.getBean("userService", UserService.class);
        service.queryUserInfo();


    }

}
