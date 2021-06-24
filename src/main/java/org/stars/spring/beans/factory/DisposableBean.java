package org.stars.spring.beans.factory;

/**
 * @author : xian
 */
public interface DisposableBean {
    /**
     * 在虚拟机关闭前，执行销毁操作的函数
     *
     * @throws Exception 异常
     */
    void destroy() throws Exception;
}
