package org.stars.spring.context.support;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.ConfigurableListableBeanFactory;
import org.stars.spring.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author : xian
 */
public abstract class AbstractRefreshApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        this.beanFactory = createBeanFactory();
        loadBeanDefinition(this.beanFactory);
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinition(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
