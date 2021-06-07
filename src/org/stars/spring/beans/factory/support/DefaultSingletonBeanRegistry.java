package org.stars.spring.beans.factory.support;

import org.stars.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册 Bean 的实例
 * @author : xian
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /** 将实例添加到 singletonObjects */
    public void addSingletonObject(String beanName, Object singleton){
        singletonObjects.put(beanName, singleton);
    }
}
