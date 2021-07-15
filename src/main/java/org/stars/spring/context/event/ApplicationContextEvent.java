package org.stars.spring.context.event;

import org.stars.spring.context.ApplicationContext;
import org.stars.spring.context.ApplicationEvent;

/**
 * 定义事件的抽象类，所有事件包括关闭、刷新，以及用户自己实现的事件，都需要继承这个类
 *
 * @author : xian
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * construct a prototypical Event 继承 EventObject 定义举杯事件功能的抽象类
     *
     * @param source The Object on which Event initially occurred
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * 获取触发事件的 ApplicationContext
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
