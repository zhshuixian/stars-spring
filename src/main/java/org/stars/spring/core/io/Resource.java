package org.stars.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Resource 输入流的接口定义
 *
 * @author : xian
 */
public interface Resource {
    /**
     * 获取 Resource 的输入流
     *
     * @return Resource 的输入流
     * @throws IOException IO 异常
     */
    InputStream getInputStream() throws IOException;
}
