package org.stars.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;
import org.stars.spring.aop.aspectj.AspectJExpressionPointcut;
import org.stars.spring.aop.framework.Cglib2AopProxy;
import org.stars.spring.aop.framework.JdkDynamicAopProxy;
import org.stars.spring.aop.framework.ReflectiveMethodInvocation;
import org.stars.spring.context.support.ClassPathXmlApplicationContext;
import org.stars.spring.util.ClassUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : xian
 */
public class AopTest {

    @Test
    public void testProxyClass() {
        IAopService service = (IAopService) Proxy.newProxyInstance(ClassUtils.getDefaultClassLoader(), new Class[]{IAopService.class},
                (proxy, method, args) -> "你被代理了" + method.getName());
        System.out.println(service.query());
    }

    @Test
    public void testProxyMethod() {
        Object target = new AopService();

        IAopService proxy = (IAopService) Proxy.newProxyInstance(ClassUtils.getDefaultClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* org.stars.spring.aop.IAopService.*(..))");

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodMatcher.matches(method, target.getClass())) {
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            System.out.println("监控开始 -----");
                            System.out.println("方法名称：" + invocation.getMethod());
                            System.out.println("执行耗时：" + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("监控结束 ----");
                        }
                    };
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(target, method, args));
                }

                return method.invoke(target, args);
            }
        });

        System.out.println(proxy.query());


    }

    @Test
    public void testAop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* org.stars.spring.aop.AopService.*(..))");
        Class<AopService> serviceClass = AopService.class;

        Method method = serviceClass.getMethod("query");

        System.out.println(pointcut.matches(serviceClass));
        System.out.println(pointcut.matches(method, serviceClass));
    }

    @Test
    public void testDynamic() {
        IAopService service = new AopService();

        AdvisedSupport advised = new AdvisedSupport();
        advised.setTargetSource(new TargetSource(service));
        advised.setMethodInterceptor(new AopServiceInterceptor());
        advised.setMethodMatcher(new AspectJExpressionPointcut("execution(* org.stars.spring.aop.IAopService.*(..))"));

        IAopService jdkProxy = (IAopService) new JdkDynamicAopProxy(advised).getProxy();
        System.out.println("JDK 动态代理测试" + jdkProxy.query());


        IAopService cglibProxy = (IAopService) new Cglib2AopProxy(advised).getProxy();
        System.out.println("Cglib 动态代理测试" + cglibProxy.register("花花"));

    }

    @Test
    public void testAutoAop() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-aop.xml");

        IAopService service = context.getBean("aopService", IAopService.class);

        System.out.println("测试结果：" + service.query());
    }
}
