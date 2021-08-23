package org.stars.spring.beans.factory.support;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.FactoryBean;
import org.stars.spring.beans.factory.config.BeanDefinition;
import org.stars.spring.beans.factory.config.BeanPostProcessor;
import org.stars.spring.beans.factory.config.ConfigurableBeanFactory;
import org.stars.spring.util.ClassUtils;
import org.stars.spring.util.StringValueResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象的模板方法
 *
 * @author : xian
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegisterSupport implements ConfigurableBeanFactory {

    /**
     * BeanPostProcessor：bean 初始化前后的扩展接口实现
     */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    /**
     * beanClassLoader
     */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    /**
     * String resolvers to apply e.g. to annotation attribute values
     */
    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();

    @Override
    public Object getBean(String beanName) throws BeansException {
        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return doGetBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> clazz) throws BeansException {
        return doGetBean(beanName, null);
    }

    @SuppressWarnings("unchecked")
    protected <T> T doGetBean(final String beanName, final Object[] args) {
        Object sharedInstance = getSingleton(beanName);
        if (sharedInstance != null) {
            return (T) getObjectForBeanInstance(sharedInstance, beanName);
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        Object bean = createBean(beanName, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, beanName);
    }

    /**
     * 如果是 FactoryBean, 则需要调用 {@link FactoryBean#getObject()}
     *
     * @param beanInstance bean 实例
     * @param beanName     bean name
     * @return bean or FactoryBean#getObject()
     * @see FactoryBeanRegisterSupport#doGetObjectFromFactoryBean(FactoryBean, String)
     */
    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }
        return getObjectFromFactoryBean((FactoryBean<?>) beanInstance, beanName);
    }

    /**
     * 根据 bean name 获取到  BeanDefinition
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 创建 bean
     */
    protected abstract Object createBean(String beanName, BeanDefinition definition, Object[] args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * Return the list of BeanPostProcessors that will get applied
     * to beans created with this factory.
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : embeddedValueResolvers) {
            result = resolver.resolveStringValue(result);
        }
        return result;
    }
}
