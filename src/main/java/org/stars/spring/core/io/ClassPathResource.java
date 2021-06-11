package org.stars.spring.core.io;

import cn.hutool.core.lang.Assert;
import org.stars.spring.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author : xian
 */
public class ClassPathResource implements Resource{
    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }



    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must be not null");
        this.path = path;
        this.classLoader = classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if(is == null){
            throw new FileNotFoundException(path + " cannot be opened because it does not exist");
        }

        return is;
    }
}
