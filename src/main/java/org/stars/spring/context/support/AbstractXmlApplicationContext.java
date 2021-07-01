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

    /**
     * 配置文件所在的路径
     * @return  所有配置文件的路径
     */
    protected abstract String[] getConfigLocations();

}
