package org.stars.spring.context;

/**
 * Interface that encapsulates event publication functionality.
 * Serves as super-interface for ApplicationContext.
 * 事件发布器
 *
 * @author : xian
 */
public interface ApplicationEventPublisher {
    /**
     * 发布事件
     * Notify all listeners registered with this application of an application
     * event. Events may be framework events (such as RequestHandledEvent)
     * or application-specific events.
     *
     * @param event 发布的事件
     */
    void publishEvent(ApplicationEvent event);
}
