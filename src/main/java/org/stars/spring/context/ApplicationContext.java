package org.stars.spring.context;

import org.stars.spring.beans.factory.ListableBeanFactory;

/**
 * 应用上下文接口，在应用运行的时候应该是只读的，如果实现支持的话可能会被重新加载
 *
 * @author : xian
 */
public interface ApplicationContext extends ListableBeanFactory {
}
