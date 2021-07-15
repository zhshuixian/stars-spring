package org.stars.spring.context.event;

import org.stars.spring.context.ApplicationEvent;

/**
 * 事件广播器：实现这个接口，用于管理一些{@link ApplicationListener}对象和发布一些事件
 * Interface to be implemented by objects that can manage a number of
 * {@link ApplicationListener} objects, and publish events to them.
 *
 * @author : xian
 */
public interface ApplicationEventMulticaster {
    /**
     * 增加事件监听器
     *
     * @param listeners 需要增加的事件监听器
     */
    void addApplicationListener(ApplicationListener<?> listeners);

    /**
     * 移除事件监听器
     *
     * @param listeners 需要移除的事件监听器
     */
    void removeApplicationListener(ApplicationListener<?> listeners);

    /**
     * 事件广播：将应用事件广播给所有的事件监听器
     *
     * @param event 需要广播的事件
     */
    void multicastEvent(ApplicationEvent event);
}
