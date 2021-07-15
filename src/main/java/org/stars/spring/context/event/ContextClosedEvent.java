package org.stars.spring.context.event;

/**
 * Spring 实现的事件类：用于监听关闭
 *
 * @author : xian
 */
public class ContextClosedEvent extends ApplicationContextEvent {
    /**
     * construct a prototypical Event 继承 EventObject 定义举杯事件功能的抽象类
     *
     * @param source The Object on which Event initially occurred
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
