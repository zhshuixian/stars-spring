package org.stars.spring.context.support;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.ConfigurableListableBeanFactory;
import org.stars.spring.beans.factory.config.BeanFactoryPostProcessor;
import org.stars.spring.beans.factory.config.BeanPostProcessor;
import org.stars.spring.context.ConfigurableApplicationContext;
import org.stars.spring.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author : xian
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {
        // 创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();
        // 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 在 Bean 实例化之前，执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);

        // BeanPostProcessor 需要在其他 bean 对象实例化之前执行注册操作
        registerBeanProcessor(beanFactory);
        // 提前实例化 bean
        beanFactory.preInstantiateSingletons();
    }

    /** 创建 BeanFactory 实例 */
    protected abstract void refreshBeanFactory() throws BeansException;
    /** 获取 BeanFactory  */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /** 处理 BeanFactoryPostProcessor */
    private void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);

        beanFactoryPostProcessorMap.values().forEach(processor -> {
            processor.postProcessBeanFactory(beanFactory);
        });
    }

    /** 注册 BeanPostProcessor */
    private void registerBeanProcessor(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);

        beanPostProcessorMap.values().forEach(beanFactory::addBeanPostProcessor);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> clazz) throws BeansException {
        return getBeanFactory().getBean(beanName, clazz);
    }
}
