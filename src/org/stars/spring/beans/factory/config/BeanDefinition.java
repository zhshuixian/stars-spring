package org.stars.spring.beans.factory.config;

/**
 * 这里存储 bean 的 Class
 * @author : xian
 */
public class BeanDefinition {
    private final Class beanClass;

    public BeanDefinition(Class beanClass){
        this.beanClass = beanClass;
    }

    public Class getBean(){
        return beanClass;
    }

}
