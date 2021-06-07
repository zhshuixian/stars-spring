package org.stars.spring;

/**
 * @author : xian
 */
public class BeanDefinition {
    private final Object bean;

    public BeanDefinition(Object bean){
        this.bean = bean;
    }

    public Object getBean(){
        return bean;
    }

}
