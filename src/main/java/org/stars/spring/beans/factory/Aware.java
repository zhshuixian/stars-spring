package org.stars.spring.beans.factory;

/**
 * Marker superinterface indicating that a bean is eligible to be
 * notified by the Spring container of a particular framework object
 * through a callback-style method.  Actual method signature is
 * determined by individual subinterfaces, but should typically
 * consist of just one void-returning method that accepts a single
 * argument.
 *
 * 标记类接口，实现该接口的可以被 Spring 容器感知
 *
 * 这种接口就是一种类似于标签一样，可以方便摘取出属于此类的接口实现类，通常配合 instanceof 一起使用
 *
 * @author : xian
 */
public interface Aware {
}
