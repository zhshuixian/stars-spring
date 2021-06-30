package org.stars.spring.beans.factory;


/**
 * 实现此接口，可以感知所属的 ClassLoader
 * @author : xian
 */
public interface BeanClassLoaderAware extends Aware{
    /**
     * 设置 Bean 的 ClassLoader
     * @param classLoader 加载器
     */
    void setBeanClassLoader(ClassLoader classLoader);
}
