package org.stars.spring.beans.factory;

import org.stars.spring.beans.BeansException;

/**
 * @author : xian
 */
public interface BeanFactory {
    /** 获取 bean 的实例 */
    Object getBean(String beanName) throws BeansException;
}
