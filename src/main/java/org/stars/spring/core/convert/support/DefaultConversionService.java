package org.stars.spring.core.convert.support;

import org.stars.spring.core.convert.converter.ConverterRegistry;

/**
 * @author : xian
 */
public class DefaultConversionService extends GenericConversionService {

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    private void addDefaultConverters(ConverterRegistry converterRegistry) {
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    }

}
