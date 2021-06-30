package org.stars.spring.beans;

import org.stars.spring.beans.factory.BeanClassLoaderAware;
import org.stars.spring.beans.factory.BeanFactory;
import org.stars.spring.beans.factory.BeanFactoryAware;
import org.stars.spring.beans.factory.BeanNameAware;
import org.stars.spring.context.ApplicationContext;
import org.stars.spring.context.ApplicationContextAware;

/**
 * @author : xian
 */
public class UserService implements BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    private ClassLoader classLoader;

    private String beanName;

    private String uId;

    private UserDao userDao;

    private String company;

    private String location;

    public void queryUserInfo() {
        System.out.println("userDao = " + userDao.queryUserName(uId) + " ," + this);
    }

    @Override
    public String toString() {
        return "uId='" + uId + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'';
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

    public void initMethod(){
        System.out.println("UserService.intiMethod()");
    }

    public void destroyMethod(){
        System.out.println("UserService.destroyMethod()");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
        System.out.println("感知 Ware： UserService.setBeanClassLoader");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        System.out.println("感知 Ware： UserService.setBeanFactory");
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
        System.out.println("感知 Ware： UserService.setBeanName");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("感知 Ware： UserService.setApplicationContext");
        this.applicationContext = applicationContext;
    }
}
