package org.stars.spring.beans.factory.config;

/**
 * 定义 Bean 的成员属性
 * @author : xian
 */
public class PropertyValue {
    /** 属性名 */
    private final String name;
    /** 属性值 */
    private final Object value;


    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
