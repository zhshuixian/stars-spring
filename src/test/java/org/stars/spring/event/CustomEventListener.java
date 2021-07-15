package org.stars.spring.event;

import org.stars.spring.context.event.ApplicationListener;

import java.util.Date;

/**
 * @author : xian
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到" + event.getSource() + "消息，时间" + new Date());
        System.out.println("id=" + event.getId() + ",message=" + event.getMessage());
    }
}
