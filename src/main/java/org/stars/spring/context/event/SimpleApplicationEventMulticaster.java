package org.stars.spring.context.event;

import org.stars.spring.beans.factory.BeanFactory;
import org.stars.spring.context.ApplicationEvent;

/**
 * @author : xian
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory factory) {
        setBeanFactory(factory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void multicastEvent(ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
