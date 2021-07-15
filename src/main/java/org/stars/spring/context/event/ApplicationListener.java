package org.stars.spring.context.event;

import org.stars.spring.context.ApplicationEvent;

import java.util.EventListener;

/**
 * 事件监听器：应用的事件监听器需要实现这个接口
 * Interface to be implemented by application event listeners.
 * Based on the standard <code>java.util.EventListener</code> interface
 * for the Observer design pattern.
 *
 * @author : xian
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    /**
     * 处理应用的事件
     *
     * @param event 事件
     */
    void onApplicationEvent(E event);
}
