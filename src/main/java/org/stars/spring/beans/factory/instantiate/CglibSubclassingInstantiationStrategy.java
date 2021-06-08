package org.stars.spring.beans.factory.instantiate;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.stars.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 使用 Cglib 实例化 bean
 * @author : xian
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });

        if(null == constructor) {
            return enhancer.create();
        }

        return enhancer.create(constructor.getParameterTypes(), args);
    }
}
