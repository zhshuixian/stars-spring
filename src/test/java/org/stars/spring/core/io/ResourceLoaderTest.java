package org.stars.spring.core.io;

import cn.hutool.core.io.IoUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * @author : xian
 */
public class ResourceLoaderTest {
    private DefaultResourceLoader resourceLoader;

    @Before
    public void init(){
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void testClassPath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:spring.properties");
        doTestPrint(resource);
    }

    @Test
    public void testFileSystem() throws IOException {
        Resource resource = resourceLoader.getResource("C:\\spring.properties");
        doTestPrint(resource);
    }

    @Test
    public void testUrl() throws IOException {
        Resource resource = resourceLoader.getResource("https://www.baidu.com");
        doTestPrint(resource);
    }

    private void doTestPrint(Resource resource) throws IOException {
        InputStream inputStream = resource.getInputStream();
        String text = IoUtil.readUtf8(inputStream);
        System.out.println(text);
    }
}