package org.stars.spring.context.support;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.ConfigurableListableBeanFactory;
import org.stars.spring.beans.factory.config.BeanFactoryPostProcessor;
import org.stars.spring.beans.factory.config.BeanPostProcessor;
import org.stars.spring.context.ApplicationEvent;
import org.stars.spring.context.ConfigurableApplicationContext;
import org.stars.spring.context.event.*;
import org.stars.spring.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * @author : xian
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";
    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        // 创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();
        // 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 由于 ApplicationContext 并不能在创建 bean 的时候拿到
        // 所以实现了 ApplicationContextAware bean 通过这个 BeanPostProcessor 进行设置
        // 这样子 Bean 对象就可以感知到所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 在 Bean 实例化之前，执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);

        // BeanPostProcessor 需要在其他 bean 对象实例化之前执行注册操作
        registerBeanProcessor(beanFactory);
        // 初始化事件广播器
        registerApplicationEventMulticast();
        // 注册事件监听器
        registerListeners();

        // 提前实例化 bean
        beanFactory.preInstantiateSingletons();

        // Context 刷新后，发布 ContextRefreshedEvent
        finishRefresh();
    }

    /**
     * 初始化广播器
     */
    private void registerApplicationEventMulticast() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    /**
     * 注册事件监听器
     */
    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    /**
     * 创建 BeanFactory 实例
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 获取 BeanFactory
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 处理 BeanFactoryPostProcessor
     */
    private void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);

        beanFactoryPostProcessorMap.values().forEach(processor -> {
            processor.postProcessBeanFactory(beanFactory);
        });
    }

    /**
     * 注册 BeanPostProcessor
     */
    private void registerBeanProcessor(ConfigurableListableBeanFactory beanFactory) {
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

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        // 关闭前发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));
        getBeanFactory().destroySingletons();
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }
}
