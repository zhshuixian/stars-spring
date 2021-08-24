package org.stars.spring.beans.factory;

import org.stars.spring.beans.BeansException;

/**
 * @author : xian
 */
public interface ObjectFactory<T> {
    T getObject() throws BeansException;
}
