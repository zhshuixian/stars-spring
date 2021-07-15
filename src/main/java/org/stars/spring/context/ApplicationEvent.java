package org.stars.spring.context;

import java.util.EventObject;

/**
 * @author : xian
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * construct a prototypical Event 继承 EventObject 定义举杯事件功能的抽象类
     *
     * @param source The Object on which Event initially occurred
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
