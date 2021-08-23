package org.stars.spring.beans.factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.BeanFactory;
import org.stars.spring.beans.factory.BeanFactoryAware;
import org.stars.spring.beans.factory.ConfigurableListableBeanFactory;
import org.stars.spring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.stars.spring.beans.factory.config.PropertyValues;
import org.stars.spring.util.ClassUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author : xian
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues propertyValues, Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        beanClass = ClassUtils.isCglibProxyClass(beanClass) ? beanClass.getSuperclass() : beanClass;
        Field[] fields = beanClass.getDeclaredFields();

        for (Field field : fields) {
            // 处理 @Value 注解
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (valueAnnotation != null) {
                String value = beanFactory.resolveEmbeddedValue(valueAnnotation.value());
                BeanUtil.setFieldValue(bean, field.getName(), value);
            }
            // 处理 @Autowired
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if (autowiredAnnotation != null) {
                Class<?> fieldType = field.getType();
                String dependentName = "";
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                Object dependentBean = null;
                if (qualifierAnnotation != null) {
                    dependentName = qualifierAnnotation.value();
                    dependentBean = beanFactory.getBean(dependentName, fieldType);
                } else {
                    dependentBean = beanFactory.getBean(fieldType);
                }
                makeAccessible(field);
                try {
                    // 使用反射给字段赋值
                    field.set(bean, dependentBean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                // BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
            }
        }
        return propertyValues;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }


    public void makeAccessible(Field field) {
        if ((!Modifier.isPublic(field.getModifiers()) ||
                !Modifier.isPublic(field.getDeclaringClass().getModifiers()) ||
                Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
            field.setAccessible(true);
        }
    }
}
