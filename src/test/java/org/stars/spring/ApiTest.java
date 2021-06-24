package org.stars.spring;

import org.junit.Test;
import org.stars.spring.beans.UserService;
import org.stars.spring.beans.factory.support.DefaultListableBeanFactory;
import org.stars.spring.beans.factory.xml.XmlBeanDefinitionReader;
import org.stars.spring.common.MyBeanFactoryPostProcessor;
import org.stars.spring.common.MyBeanPostProcessor;
import org.stars.spring.context.ApplicationContext;
import org.stars.spring.context.support.ClassPathXmlApplicationContext;

/**
 * @author : xian
 */
public class ApiTest {

    @Test
    public void testBeanFactory() {
        // 不使用 PostProcessor 扩展的
        // BeanFactory 初始化
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 使用 xml 配置 bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("classpath:spring-no-with-processor.xml");
        // 获取 bean
        UserService service = factory.getBean("userService", UserService.class);
        service.queryUserInfo();
    }

    @Test
    public void testPostProcessor() {
        // 手动指定 PostProcessor 的
        // BeanFactory 初始化
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 使用 xml 配置 bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring-no-with-processor.xml");
        // BeanDefinition 记载完成后，使用 BeanFactoryPostProcessor 修改 BeanDefinition 的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        // Bean 实例化，修改 bean 的信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);
        // 获取 bean
        UserService service = beanFactory.getBean("userService", UserService.class);
        service.queryUserInfo();
    }

    @Test
    public void testXmlContext() {
        // 使用应用上下文
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");

        UserService userService = context.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

}
