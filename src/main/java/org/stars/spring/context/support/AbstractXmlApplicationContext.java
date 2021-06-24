package org.stars.spring.context.support;

import org.stars.spring.beans.factory.support.DefaultListableBeanFactory;
import org.stars.spring.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author : xian
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshApplicationContext {

    @Override
    protected void loadBeanDefinition(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();

}
