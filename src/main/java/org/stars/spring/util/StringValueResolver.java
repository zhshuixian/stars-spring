package org.stars.spring.util;

/**
 * Simple strategy interface for resolving a String value.
 *
 * @author : xian
 */
public interface StringValueResolver {
    String resolveStringValue(String strVal);
}
