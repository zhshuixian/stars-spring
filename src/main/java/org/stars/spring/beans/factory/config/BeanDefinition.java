package org.stars.spring.beans.factory.config;

/**
 * 这里存储 bean 的 Class，好处是将 Object 的实例化放在容器中完成
 * @author : xian
 */
public class BeanDefinition {
    /** 类的 Class */
    private final Class<?> beanClass;
    /** 这个 Bean 的所有成员属性 */
    private final PropertyValues propertyValues;

    public BeanDefinition(Class<?> beanClass){
        this(beanClass, null);
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class<?> getBeanClass(){
        return beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }
}
