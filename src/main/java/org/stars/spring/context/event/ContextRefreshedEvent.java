package org.stars.spring.context.event;

/**
 * Spring 实现的事件类：用于监听刷新
 *
 * @author : xian
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {
    /**
     * construct a prototypical Event 继承 EventObject 定义举杯事件功能的抽象类
     *
     * @param source The Object on which Event initially occurred
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
