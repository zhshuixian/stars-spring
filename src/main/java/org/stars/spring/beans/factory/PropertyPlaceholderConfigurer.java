package org.stars.spring.beans.factory;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.config.BeanDefinition;
import org.stars.spring.beans.factory.config.BeanFactoryPostProcessor;
import org.stars.spring.beans.factory.config.PropertyValue;
import org.stars.spring.beans.factory.config.PropertyValues;
import org.stars.spring.core.io.DefaultResourceLoader;
import org.stars.spring.core.io.Resource;
import org.stars.spring.util.StringValueResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * 处理 BeanDefinition 的占位符的替换
 *
 * @author : xian
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    /**
     * 默认的占位符 字首 {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";
    /**
     * 默认的占位符 字尾 {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();

            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) {
                        continue;
                    }
                    value = resolvePlaceholder((String) value, properties);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), value));
                }
            }
            // 向容器中添加字符串解析器，供解析@Value注解使用
            PlaceholderResolvingStringValueResolver stringValueResolver = new PlaceholderResolvingStringValueResolver(properties);
            beanFactory.addEmbeddedValueResolver(stringValueResolver);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String resolvePlaceholder(String value, Properties properties) {
        StringBuilder builder = new StringBuilder(value);
        int startIdx = value.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int endIdx = value.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        // value 是否为 ${开头， }结尾的字符串
        if (startIdx != -1 && endIdx != -1 && startIdx < endIdx) {
            // properties 的 key 也就是 ${key}
            String proKey = value.substring(startIdx + 2, endIdx);
            String proValue = properties.getProperty(proKey);
            builder.replace(startIdx, endIdx + 1, proValue);
        }
        return builder.toString();
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private class PlaceholderResolvingStringValueResolver implements StringValueResolver {

        private final Properties properties;

        private PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolveStringValue(String strVal) {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strVal, properties);
        }
    }
}
