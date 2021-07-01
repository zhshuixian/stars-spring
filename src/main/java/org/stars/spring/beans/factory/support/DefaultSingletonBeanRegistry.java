package org.stars.spring.beans.factory.support;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.DisposableBean;
import org.stars.spring.beans.factory.config.SingletonBeanRegistry;

/**
 * 实现 Bean 的单例的接口，讲 bean 进行缓存和通过 bean name 获取
 * 主要是管理 bean 实例的
 *
 * @author : xian
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    /**
     * Internal marker for a null singleton object:
     * used as marker value for concurrent Maps (which don't support null values).
     * */
    protected static final Object NULL_OBJECT = new Object();

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        Object object = singletonObjects.get(beanName);
        return (object != NULL_OBJECT ? object : null);
    }

    /**
     * 将实例添加到 singletonObjects
     */
    public void addSingletonObject(String beanName, Object singleton) {
        singletonObjects.put(beanName, (singleton != null ? singleton : NULL_OBJECT));
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        this.disposableBeans.put(beanName, bean);
    }

    /** 执行 disposableBeans 的销毁方法 */
    public void destroySingletons(){
        String[] disposableBeanNames = this.disposableBeans.keySet().toArray(new String[0]);
        for (String disposableBeanName : disposableBeanNames) {
            destroySingleton(disposableBeanName);
        }
    }

    public void destroySingleton(String beanName){
        DisposableBean disposableBean = this.disposableBeans.remove(beanName);
        destroyBean(beanName, disposableBean);
    }

    protected void destroyBean(String beanName, DisposableBean bean){
        if(bean != null){
            try {
                bean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' throw an exception", e);
            }
        }
    }
}
