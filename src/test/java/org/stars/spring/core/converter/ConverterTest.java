package org.stars.spring.core.converter;

import org.junit.Test;
import org.stars.spring.beans.IUserDao;
import org.stars.spring.context.support.ClassPathXmlApplicationContext;
import org.stars.spring.core.convert.converter.Converter;
import org.stars.spring.core.convert.support.StringToNumberConverterFactory;
import org.stars.spring.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * @author : xian
 */
@Component
public class ConverterTest implements IUserDao {

    @Test
    public void testStringToInteger() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer num = converter.convert("1234");
        System.out.println(num);

        StringToNumberConverterFactory factory = new StringToNumberConverterFactory();
        Converter<String, Integer> stringIntegerConverter = factory.getConverter(Integer.class);
        System.out.println(stringIntegerConverter.convert("-2345600"));
    }

    @Test
    public void testClass() throws InstantiationException, IllegalAccessException {
        Class<HelloWorld> clazz = HelloWorld.class;
        // 获得这个实现的接口
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        System.out.println(Arrays.toString(genericInterfaces));
        // 获取成员变量的, 成员方法 clazz.getDeclaredMethods()
        System.out.println(Arrays.toString(clazz.getDeclaredFields()));

        // 获得这个接口的的泛型
        ParameterizedType parameterizedType = (ParameterizedType) genericInterfaces[0];
        System.out.println(Arrays.toString(parameterizedType.getActualTypeArguments()));

        // 获得这个类的注解
        System.out.println(clazz.getAnnotation(Component.class));
    }

    @Override
    public String queryUserName(String uId) {
        return null;
    }

    @Component
    private static final class HelloWorld<String, Integer> implements Converter<String, Integer> {

        @Override
        public Integer convert(String source) {
            return null;
        }
    }

    @Test
    public void testContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-converter.xml");
        House house = (House) context.getBean("house");
        System.out.println(house);
    }
}
