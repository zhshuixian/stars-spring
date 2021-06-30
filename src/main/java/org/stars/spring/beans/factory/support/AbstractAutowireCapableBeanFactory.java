package org.stars.spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.*;
import org.stars.spring.beans.factory.config.*;
import org.stars.spring.beans.factory.instantiate.CglibSubclassingInstantiationStrategy;
import org.stars.spring.beans.factory.instantiate.InstantiationStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 初始化 bean 的实例
 *
 * @author : xian
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            // 给 bean 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
            // 执行 bean 的初始化方法和 BeanPostProcessor
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed:" + e);
        }
        // 注册实现了 DisposableBean 或者 配置了 destroy-method 的 bean 对象
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        addSingletonObject(beanName, bean);
        return bean;
    }

    /**
     * 使用 instantiationStrategy 实例化 bean 对象
     *
     * @param beanDefinition bean 的配置
     * @param beanName       bean 名称
     * @param args           bean 的构造方法的参数
     * @return 实例化的 bean 对象
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor<?> constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            if (null != args && constructor.getParameterTypes().length == args.length) {
                constructorToUse = constructor;
            }
        }

        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    /**
     * 给 bean 填充属性
     *
     * @param beanName       bean 名称
     * @param bean           bean 实例
     * @param beanDefinition bean 的配置
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue pv : propertyValues.getPropertyValues()) {
                String pName = pv.getName();
                Object pValue = pv.getValue();
                if (pValue instanceof BeanReference) {
                    // A 依赖 B ，这先实例化 B
                    BeanReference reference = (BeanReference) pValue;
                    pValue = getBean(reference.getBeanName());
                }
                // Hutool 的工具类完成属性填充
                BeanUtil.setFieldValue(bean, pName, pValue);
            }

        } catch (Exception e) {
            throw new BeansException("Error setting property values " + beanName);
        }
    }

    /**
     * 执行 bean 的初始化前置、初始化、后置处理
     */
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // InvokeAwareMethods
        if(bean instanceof Aware){
            if(bean instanceof BeanFactoryAware){
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if(bean instanceof BeanClassLoaderAware){
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
            if(bean instanceof BeanNameAware){
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }

        // 执行 Before 的处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 执行 After 的处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);

        return wrappedBean;
    }

    /**
     * 执行 bean 属性填充后，需要执行的方法
     * 实现了 InitializingBean 或者使用 xml 配置的 初始化前的需要执行的操作
     *
     * @param beanName       bean 名称
     * @param bean           bean 对象
     * @param beanDefinition bean 属性定义
     * @throws Exception 异常
     */
    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        // 如果 Bean 实现了 InitializingBean
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)) {
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if (null == initMethod) {
                throw new Exception("Cloud not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition){
        if(bean instanceof DisposableBean || StrUtil.isNotEmpty((beanDefinition.getDestroyMethodName()))){
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }

        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }

        return result;
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
