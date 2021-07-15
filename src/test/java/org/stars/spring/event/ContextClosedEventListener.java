package org.stars.spring.event;

import org.stars.spring.context.event.ApplicationListener;
import org.stars.spring.context.event.ContextClosedEvent;

/**
 * @author : xian
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("容器关闭事件" + this.getClass().getName());
    }
}
