package org.stars.spring.aop.framework;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.stars.spring.aop.AdvisedSupport;
import org.stars.spring.util.ClassUtils;

import java.lang.reflect.Method;

/**
 * @author : xian
 */
public class Cglib2AopProxy implements AopProxy {
    private final AdvisedSupport advised;

    public Cglib2AopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        Class<?> targetClass = advised.getTargetSource().getTarget().getClass();
        targetClass = ClassUtils.isCglibProxyClass(targetClass) ? targetClass.getSuperclass() : targetClass;
        enhancer.setSuperclass(targetClass);
        enhancer.setInterfaces(advised.getTargetSource().getTargetClass());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));

        return enhancer.create();
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {
        private final AdvisedSupport advised;

        private DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
        }

        @Override
        public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            CglibMethodInvocation invocation = new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, args, methodProxy);
            if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
                return advised.getMethodInterceptor().invoke(invocation);
            }
            return invocation.proceed();
        }

    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {
        private final MethodProxy methodProxy;

        private CglibMethodInvocation(Object target, Method method, Object[] arguments, MethodProxy methodProxy) {
            super(target, method, arguments);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(this.target, this.arguments);
        }
    }
}
