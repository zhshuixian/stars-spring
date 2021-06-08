package org.stars.spring.beans;

/**
 * @author : xian
 */
public class UserService {
    private final String name;

    public UserService(String name){
        this.name = name;
    }

    public void queryUserInfo(){
        System.out.println("哈哈哈哈，  " + name);
    }
}
