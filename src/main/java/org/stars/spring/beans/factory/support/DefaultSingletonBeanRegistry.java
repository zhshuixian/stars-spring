package org.stars.spring.beans.factory.support;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.DisposableBean;
import org.stars.spring.beans.factory.ObjectFactory;
import org.stars.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
     */
    protected static final Object NULL_OBJECT = new Object();

    /**
     * 一级缓存: 成品对象，一级初始化完成的普通对象
     * Cache of singleton objects: bean name --> bean instance
     */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();
    /**
     * 二级缓存：半成品对象，没有完全实例化的对象
     * Cache of early singleton objects: bean name --> bean instance
     */
    private final Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>();

    /**
     * 三级缓存，存放代理对象
     * Cache of singleton factories: bean name --> ObjectFactory
     */
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        Object singletonObject = singletonObjects.get(beanName);
        if (singletonObject == null) {
            // 一级缓存没有则去二级缓存找
            singletonObject = earlySingletonObjects.get(beanName);
            if (singletonObject == null) {
                // 二级缓存没有则去三级缓存找
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if (singletonFactory != null) {
                    singletonObject = singletonFactory.getObject();
                    // 三级缓存的代理对象的真实对象取出，放入二级缓存
                    earlySingletonObjects.put(beanName, singletonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singletonObject;
    }

    /**
     * 将实例添加到 singletonObjects
     */
    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singleFactory) {
        if (!this.singletonObjects.containsKey(beanName)) {
            this.singletonFactories.put(beanName, singleFactory);
            this.earlySingletonObjects.remove(beanName);
        }
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        this.disposableBeans.put(beanName, bean);
    }

    /**
     * 执行 disposableBeans 的销毁方法
     */
    public void destroySingletons() {
        String[] disposableBeanNames = this.disposableBeans.keySet().toArray(new String[0]);
        for (String disposableBeanName : disposableBeanNames) {
            destroySingleton(disposableBeanName);
        }
    }

    public void destroySingleton(String beanName) {
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
