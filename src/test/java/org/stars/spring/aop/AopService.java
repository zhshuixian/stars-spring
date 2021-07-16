package org.stars.spring.aop;

import java.util.Random;

/**
 * @author : xian
 */
public class AopService implements IAopService {
    @Override
    public String query() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Stars Spring, 10001, 深圳";
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
}
