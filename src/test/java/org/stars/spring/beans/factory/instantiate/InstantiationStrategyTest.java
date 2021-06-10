package org.stars.spring.beans.factory.instantiate;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;
import org.stars.spring.beans.UserService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

/**
 * @author : xian
 */
public class InstantiationStrategyTest {

    @Test
    public void testNewInstance() throws InstantiationException, IllegalAccessException {
        UserService service = UserService.class.newInstance();
        service.queryUserInfo();
    }

    @Test
    public void testConstructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<UserService> userServiceClass = UserService.class;

        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);

        UserService service = declaredConstructor.newInstance("小先哥哥");

        service.queryUserInfo();
    }

    @Test
    public void testParameterTypes() throws  Exception {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<?>[] declaredConstructors = userServiceClass.getDeclaredConstructors();
        Constructor<?> declaredConstructor = declaredConstructors[1];

        Constructor<UserService> constructor = userServiceClass.getDeclaredConstructor(declaredConstructor.getParameterTypes());

        UserService service = constructor.newInstance("小先哥哥 呢");

        service.queryUserInfo();
    }

    @Test
    public void testCglib(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object object = enhancer.create(new Class[]{String.class}, new Object[]{"小先哥哥"});

        System.out.println(object);
    }

}