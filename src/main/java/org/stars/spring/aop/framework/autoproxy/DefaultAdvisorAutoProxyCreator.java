package org.stars.spring.aop.framework.autoproxy;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.stars.spring.aop.*;
import org.stars.spring.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.stars.spring.aop.framework.ProxyFactory;
import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.BeanFactory;
import org.stars.spring.beans.factory.BeanFactoryAware;
import org.stars.spring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.stars.spring.beans.factory.config.PropertyValues;
import org.stars.spring.beans.factory.support.DefaultListableBeanFactory;
import org.stars.spring.util.ClassUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : xian
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {
    private DefaultListableBeanFactory beanFactory;

    private final Set<Object> earlyProxyObjectReferences = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues propertyValues, Object bean, String beanName) throws BeansException {
        return propertyValues;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!earlyProxyObjectReferences.contains(beanName)) {
            return warpIfNecessary(bean, beanName);
        }
        return bean;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) {
        earlyProxyObjectReferences.add(beanName);
        return warpIfNecessary(bean, beanName);
    }

    protected Object warpIfNecessary(Object bean, String beanName) {
        if (isInfrastructureClass(bean.getClass())) return bean;

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            // 看 class filter 是否匹配当前的类
            if (classFilter.matches(bean.getClass())) {
                AdvisedSupport advisedSupport = new AdvisedSupport();
                Class<?> beanClass = bean.getClass();
                ClassUtils.isCglibProxyClass(beanClass);
                // 这里判断
                TargetSource targetSource = new TargetSource(bean);

                advisedSupport.setTargetSource(targetSource);

                //
                advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
                advisedSupport.setProxyTargetClass(true);

                return new ProxyFactory(advisedSupport).getProxy();
            }

        }

        return bean;
    }
}
