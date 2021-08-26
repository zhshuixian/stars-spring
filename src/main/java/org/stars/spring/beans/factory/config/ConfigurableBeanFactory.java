package org.stars.spring.beans.factory.config;

import org.stars.spring.beans.factory.HierarchicalBeanFactory;
import org.stars.spring.core.convert.ConversionService;
import org.stars.spring.util.StringValueResolver;

/**
 * Configuration interface to be implemented by most bean factories. Provides
 * facilities to configure a bean factory, in addition to the bean factory
 * client methods in the {@link org.stars.spring.beans.factory.BeanFactory}
 * interface.
 * <p>
 * 工厂的配置接口
 *
 * @author xian
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    /** 单例模式 */
    String SCOPE_SINGLETON = "singleton";
    /** 原型模式 */
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 配置 BeanPostProcessor 接口实现的方法
     *
     * @param beanPostProcessor bean 对象初始化前后的修改扩展功能接口的实现
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * Destroy all singleton beans in this factory, including inner beans that have
     * been registered as disposable. To be called on shutdown of a factory.
     * <p>Any exception that arises during destruction should be caught
     * and logged instead of propagated to the caller of this method.
     */
    void destroySingletons();

    /**
     * 获取bean 的 class Loader
     *
     * @return ClassLoader
     */
    ClassLoader getBeanClassLoader();

    /**
     * Add a String resolver for embedded values such as annotation attributes. 字符串解析器
     *
     * @param valueResolver the String resolver to apply to embedded values
     */
    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * Resolve the given embedded value, e.g. an annotation attribute. 使用字符串解析器解析原始 value
     *
     * @param value the value to resolve
     * @return the resolved value (may be the original value as-is)
     */
    String resolveEmbeddedValue(String value);

    /**
     * property values 的转换器
     *
     * @param conversionService 转换器
     */
    void setConversionService(ConversionService conversionService);

    /**
     * 返回 ConversionService
     *
     * @return ConversionService
     */
    ConversionService getConversionService();
}
