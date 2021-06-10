package org.stars.spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.config.BeanDefinition;
import org.stars.spring.beans.factory.config.BeanReference;
import org.stars.spring.beans.factory.config.PropertyValue;
import org.stars.spring.beans.factory.config.PropertyValues;
import org.stars.spring.beans.factory.instantiate.CglibSubclassingInstantiationStrategy;
import org.stars.spring.beans.factory.instantiate.InstantiationStrategy;

import java.lang.reflect.Constructor;
import java.time.temporal.ValueRange;

/**
 * 初始化 bean 的实例
 * @author : xian
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition definition, Object[] args) throws BeansException {
        Object bean = createBeanInstance(definition, beanName, args);
        addSingletonObject(beanName, bean);
        applyPropertyValues(beanName, bean, definition);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args){
        Constructor<?> constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for(Constructor<?> constructor: declaredConstructors){
            if(null != args && constructor.getParameterTypes().length == args.length){
                constructorToUse = constructor;
            }
        }

        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
        try{
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for(PropertyValue pv: propertyValues.getPropertyValues()){
                String pName = pv.getName();
                Object pValue = pv.getValue();
                if(pValue instanceof BeanReference){
                    // A 依赖 B ，这先实例化 B
                    BeanReference reference = (BeanReference) pValue;
                    pValue = getBean(reference.getBeanName());
                }
                // Hutool 的工具类完成属性填充
                BeanUtil.setFieldValue(bean, pName, pValue);
            }

        }catch (Exception e){
            throw new BeansException("Error setting property values " + beanName );
        }
    }


    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
