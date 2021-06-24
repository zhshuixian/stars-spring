package org.stars.spring.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import org.stars.spring.beans.factory.DisposableBean;
import org.stars.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 实现 Bean 的单例的接口，讲 bean 进行缓存和通过 bean name 获取
 * 主要是管理 bean 实例的
 *
 * @author : xian
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new HashMap<>();

    private final Map<String, Object> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 将实例添加到 singletonObjects
     */
    public void addSingletonObject(String beanName, Object singleton) {
        singletonObjects.put(beanName, singleton);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        this.disposableBeans.put(beanName, bean);
    }

    public void destroySingletons(){
        String[] disposableBeanNames = this.disposableBeans.keySet().toArray(new String[0]);
        for (String disposableBeanName : disposableBeanNames) {
            destroySingleton(disposableBeanName);
        }
    }

    public void destroySingleton(String beanName){
        DisposableBean disposableBean = (DisposableBean) this.disposableBeans.remove(beanName);
        destroyBean(beanName, disposableBean);
    }

    protected void destroyBean(String beanName, DisposableBean bean){
        if(bean != null){
            try {
                bean.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
