package org.stars.spring.event;

import org.stars.spring.context.ApplicationEvent;

/**
 * @author : xian
 */
public class CustomEvent extends ApplicationEvent {

    final int id;

    final String message;

    /**
     * construct a prototypical Event 继承 EventObject 定义举杯事件功能的抽象类
     *
     * @param source  The Object on which Event initially occurred
     * @param id      id
     * @param message message
     */
    public CustomEvent(Object source, int id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
