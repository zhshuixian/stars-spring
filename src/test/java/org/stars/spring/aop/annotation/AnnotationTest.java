package org.stars.spring.aop.annotation;

import org.junit.Test;
import org.stars.spring.context.support.ClassPathXmlApplicationContext;

/**
 * @author : xian
 */
public class AnnotationTest {
    @Test
    public void testPrototype() {
        // 测试字符串替换
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-annotation.xml");
        IUserServiceAnnotation userService = context.getBean("userService", IUserServiceAnnotation.class);
        System.out.println(userService);
    }

    @Test
    public void testScanner() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-scaner.xml");
        IUserServiceAnnotation userService = context.getBean("userService", IUserServiceAnnotation.class);
        userService.queryUserInfo();
    }
}
