package org.stars.spring.context.support;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.FactoryBean;
import org.stars.spring.beans.factory.InitializingBean;
import org.stars.spring.core.convert.ConversionService;
import org.stars.spring.core.convert.converter.Converter;
import org.stars.spring.core.convert.converter.ConverterFactory;
import org.stars.spring.core.convert.converter.ConverterRegistry;
import org.stars.spring.core.convert.converter.GenericConverter;
import org.stars.spring.core.convert.support.DefaultConversionService;
import org.stars.spring.core.convert.support.GenericConversionService;

import java.util.Objects;
import java.util.Set;

/**
 * @author : xian
 */
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {
    private Set<?> converters;

    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws BeansException {
        return conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.conversionService = new DefaultConversionService();
        registerConverters(converters, conversionService);
    }

    private void registerConverters(Set<?> converters, ConverterRegistry registry) {
        if (Objects.nonNull(converters)) {
            for (Object converter : converters) {
                if (converter instanceof GenericConverter) {
                    registry.addConverter((GenericConverter) converter);
                } else if (converter instanceof Converter<?, ?>) {
                    registry.addConverter((Converter<?, ?>) converter);
                } else if (converter instanceof ConverterFactory<?, ?>) {
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                } else {
                    throw new IllegalArgumentException("Each converter object must implement one of the" +
                            "GenericConverter、 Converter<?, ?>、ConverterFactory<?, ?>");
                }
            }
        }
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }
}
