package org.stars.spring.util;

import cn.hutool.core.util.ClassUtil;

/**
 * @author : xian
 */
public class ClassUtils {
    public static ClassLoader getDefaultClassLoader(){
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        }catch (Throwable ex){
            //
        }
        if(classLoader == null){
            classLoader = ClassUtil.class.getClassLoader();
        }
        return classLoader;
    }
}
