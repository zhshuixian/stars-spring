package org.stars.spring.context;

import org.stars.spring.beans.factory.HierarchicalBeanFactory;
import org.stars.spring.beans.factory.ListableBeanFactory;
import org.stars.spring.core.io.ResourceLoader;

/**
 * @author : xian
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
