package org.stars.spring;

import org.junit.Before;
import org.junit.Test;
import org.stars.spring.beans.UserService;
import org.stars.spring.context.support.ClassPathXmlApplicationContext;
import org.stars.spring.event.CustomEvent;

/**
 * @author : xian
 */
public class ApiTest {
    ClassPathXmlApplicationContext context;

    @Before
    public void init() {
        // 使用应用上下文
        context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();
    }

    @Test
    public void testXmlContext() {
        //  init();
        UserService userService = context.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void testPrototype() {
        // init();
        UserService userService1 = context.getBean("userService", UserService.class);
        UserService userService2 = context.getBean("userService", UserService.class);

        System.out.println(userService1);
        System.out.println(userService2);

        // 十六进制哈希
        System.out.println(userService1 + " 十六进制哈希：" + Integer.toHexString(userService1.hashCode()));
        System.out.println(userService2 + " 十六进制哈希：" + Integer.toHexString(userService2.hashCode()));
    }

    @Test
    public void testEvent() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:event.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1396, "你个憨憨"));
        applicationContext.registerShutdownHook();
    }

}
