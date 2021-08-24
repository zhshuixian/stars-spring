package org.stars.spring.loopdepend;

import org.junit.Test;
import org.stars.spring.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : xian
 */
public class LoopDependentTest {
    private final static Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    private static <T> T getBean(Class<T> beanClass) throws InstantiationException, IllegalAccessException {
        String beanName = beanClass.getSimpleName().toLowerCase();
        if (singletonObjects.containsKey(beanName)) {
            return (T) singletonObjects.get(beanName);
        }
        Object o = beanClass.newInstance();
        singletonObjects.put(beanName, o);
        // 属性填充
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            Class<?> fieldType = field.getType();
            field.setAccessible(true);
            field.set(o, getBean(fieldType));
            field.setAccessible(false);
        }

        return (T) o;
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        System.out.println(getBean(A.class).getB());
        System.out.println(getBean(B.class).getA());
        System.out.println(getBean(A.class));
    }

    private static class A {
        private B b;

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }
    }

    private static class B {
        private A a;

        public A getA() {
            return a;
        }

        public void setA(A a) {
            this.a = a;
        }
    }

    @Test
    public void testLoopDependent() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-loopdepent.xml");
        Husband husband = context.getBean("husband", Husband.class);
        Wife wife = context.getBean("wife", Wife.class);
        System.out.println("老公的媳妇 " + husband.queryWife());
        System.out.println("媳妇的老公 " + wife.queryHusband());
    }

    @Test
    public void testLoopDependentWithAutowired() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-loopdepent-with-autowired.xml");
        Husband husband = context.getBean("husband", Husband.class);
        Wife wife = context.getBean("wife", Wife.class);
        System.out.println("老公的媳妇 " + husband.queryWife());
        System.out.println("媳妇的老公 " + wife.queryHusband());
    }

}
