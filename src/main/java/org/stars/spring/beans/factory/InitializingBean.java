package org.stars.spring.beans.factory;

/**
 * @author : xian
 */
public interface InitializingBean {
    /**
     * Bean 处理了属性填充后调用
     *
     * @throws Exception 异常
     */
    void afterPropertiesSet() throws Exception;
}
