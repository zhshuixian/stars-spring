package org.stars.spring.beans.factory;

import org.stars.spring.beans.BeansException;

/**
 * 就一个获取到 bean 实例的接口
 * @author : xian
 */
public interface BeanFactory {
    /** 获取 bean 的实例 */
    Object getBean(String beanName) throws BeansException;
}
