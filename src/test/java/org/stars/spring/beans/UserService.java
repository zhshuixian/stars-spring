package org.stars.spring.beans;

/**
 * @author : xian
 */
public class UserService {
    private  String uId;

    private UserDao userDao;

    public void queryUserInfo(){
        System.out.println("哈哈哈哈，  " + userDao.queryUserName(uId));
    }

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
