package org.stars.spring.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.DisposableBean;
import org.stars.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author : xian
 */
public class DisposableBeanAdapter implements DisposableBean {
    private final Object bean;

    private final String beanName;

    private final String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 实现接口的 DisposableBea
        if(bean instanceof DisposableBean){
            ((DisposableBean) bean).destroy();
        }
        // 使用 destroy-method 配置的, 后面的判断是避免二次执行销毁
        if(StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(destroyMethodName))){
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if(null == destroyMethod){
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName +"' on bean with name + '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
