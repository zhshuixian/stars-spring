package org.stars.spring.core.converter;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.FactoryBean;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : xian
 */
public class ConverterFactoryBean implements FactoryBean<Set<?>> {
    @Override
    public Set<?> getObject() throws BeansException {
        HashSet<Object> converters = new HashSet<>();
        StringToDateConverter strToDateConverter = new StringToDateConverter("yyyy-MM-dd");
        converters.add(strToDateConverter);

        return converters;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
