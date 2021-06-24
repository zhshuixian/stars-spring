package org.stars.spring.beans.factory.config;

/**
 * Bean 的引用类型
 *
 * @author : xian
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
