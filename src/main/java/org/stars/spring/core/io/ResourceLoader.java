package org.stars.spring.core.io;

/**
 * @author : xian
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 获取 Resource
     *
     * @param location 位置
     * @return Resource
     */
    Resource getResource(String location);
}
