package org.stars.spring.context.annotation;

import cn.hutool.core.util.StrUtil;
import org.stars.spring.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.stars.spring.beans.factory.config.BeanDefinition;
import org.stars.spring.beans.factory.support.BeanDefinitionRegistry;
import org.stars.spring.stereotype.Component;

import java.util.Set;

/**
 * @author : xian
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {
    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidates) {
                String beanScope = resolveBeanScope(beanDefinition);
                if (StrUtil.isNotEmpty(beanScope)) {
                    beanDefinition.setScope(beanScope);
                }
                registry.registryBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }
        // 注册处理注解的 BeanPostProcessor @Autowired @Value
        registry.registryBeanDefinition("org.stars.spring.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor",
                new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }


    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        return null != scope ? scope.value() : StrUtil.EMPTY;
    }

    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isEmpty(value)) {
            value = StrUtil.lowerFirst(beanClass.getName());
        }
        return value;
    }

}
