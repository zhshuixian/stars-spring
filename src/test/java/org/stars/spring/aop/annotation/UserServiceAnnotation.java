package org.stars.spring.aop.annotation;

import org.stars.spring.stereotype.Component;

/**
 * @author : xian
 */
@Component("userService")
public class UserServiceAnnotation implements IUserServiceAnnotation {

    private String token;

    @Override
    public void queryUserInfo() {
        System.out.println("蓝色空间：1001：太阳系");
    }

    @Override
    public String toString() {
        return "UserServiceAnnotation#token = " + token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
