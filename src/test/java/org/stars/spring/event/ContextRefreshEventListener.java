package org.stars.spring.event;

import org.stars.spring.context.event.ApplicationListener;
import org.stars.spring.context.event.ContextRefreshedEvent;

/**
 * @author : xian
 */
public class ContextRefreshEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("容器刷新事件" + this.getClass().getName());
    }
}
