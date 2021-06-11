package org.stars.spring.beans.factory.support;

import org.stars.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现 Bean 的单例的接口，讲 bean 进行缓存和通过 bean name 获取
 * 主要是管理 bean 实例的
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
