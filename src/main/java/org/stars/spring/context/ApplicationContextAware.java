package org.stars.spring.context;

import org.stars.spring.beans.factory.Aware;

/**
 * 实现这个接口，可以感知所属的 ApplicationContext
 * @author : xian
 */
public interface ApplicationContextAware extends Aware {
    /**
     * 设置所属的 ApplicationContext
     * @param applicationContext  Application Context
     */
    void setApplicationContext(ApplicationContext applicationContext);
}
