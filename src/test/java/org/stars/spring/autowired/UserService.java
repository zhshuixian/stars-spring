package org.stars.spring.autowired;

import org.stars.spring.beans.IUserDao;
import org.stars.spring.beans.IUserService;
import org.stars.spring.beans.factory.annotation.Autowired;
import org.stars.spring.beans.factory.annotation.Qualifier;
import org.stars.spring.beans.factory.annotation.Value;
import org.stars.spring.stereotype.Component;

/**
 * @author : xian
 */
@Component("userService")
public class UserService implements IUserService {
    @Value("${username}")
    private String username;

    @Autowired
    @Qualifier("org.stars.spring.autowired.UserDao")
    private IUserDao dao;

    @Override
    public void queryUserInfo() {
        System.out.println(dao.queryUserName(username));
    }
}
