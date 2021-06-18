package org.stars.spring.context;

import org.stars.spring.beans.BeansException;

/**
 * @author : xian
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     * @throws BeansException bean 异常
     */
    void refresh() throws BeansException;
}
