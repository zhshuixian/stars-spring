package org.stars.spring.context.annotation;

import cn.hutool.core.util.ClassUtil;
import org.stars.spring.beans.factory.config.BeanDefinition;
import org.stars.spring.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 用来扫描所有 Component 注解的 bean
 *
 * @author : xian
 */
public class ClassPathScanningCandidateComponentProvider {
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
