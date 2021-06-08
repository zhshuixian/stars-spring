package org.stars.spring.beans.factory.config;

/**
 * 这里存储 bean 的 Class，好处是将 Object 的实例化放在容器中完成
 * @author : xian
 */
public class BeanDefinition {
    private final Class<?> beanClass;

    public BeanDefinition(Class<?> beanClass){
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass(){
        return beanClass;
    }

}
