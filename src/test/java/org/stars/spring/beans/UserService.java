package org.stars.spring.beans;

/**
 * @author : xian
 */
public class UserService {
    private  String uId;

    private UserDao userDao;

    private String company;

    private String location;

    public void queryUserInfo(){
        System.out.println("userDao = " + userDao.queryUserName(uId) + " ," + this);
    }

    @Override
    public String toString() {
        return "uId='" + uId + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' ;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
