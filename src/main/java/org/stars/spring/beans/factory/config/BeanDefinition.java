package org.stars.spring.beans.factory.config;

/**
 * 这里存储 bean 的 Class，好处是将 Object 的实例化放在容器中完成
 *
 * @author : xian
 */
public class BeanDefinition {
    /**
     * 类的 Class
     */
    private Class<?> beanClass;
    /**
     * 这个 Bean 的所有成员属性
     */
    private PropertyValues propertyValues;
    /**
     * bean 属性填充后调用的方法名
     */
    private String initMethodName;
    /**
     * bean 在虚拟机关闭前执行销毁的方法名
     */
    private String destroyMethodName;


    public BeanDefinition(Class<?> beanClass) {
        this(beanClass, null);
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }
}
