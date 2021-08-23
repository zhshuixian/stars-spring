package org.stars.spring.autowired;

import org.junit.Test;
import org.stars.spring.beans.IUserService;
import org.stars.spring.context.support.ClassPathXmlApplicationContext;

/**
 * @author : xian
 */
public class AutowiredTest {

    @Test
    public void testAutowired() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-autowired.xml");
        IUserService service = (IUserService) context.getBean("userService");
        service.queryUserInfo();
    }
}
