package org.stars.spring.aop;

import org.stars.spring.beans.factory.annotation.Value;
import org.stars.spring.stereotype.Component;

import java.util.Random;

/**
 * @author : xian
 */
@Component("aopService")
public class AopService implements IAopService {
    @Value("${token}")
    private String username;

    @Override
    public String query() {
        try {
            System.out.println("执行 query 方法");
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Stars Spring, 10001, 深圳," + username;
    }

    @Override
    public String register(String name) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "注册用户：" + name + " 成功";
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
