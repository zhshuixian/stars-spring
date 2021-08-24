package org.stars.spring.loopdepend;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.FactoryBean;
import org.stars.spring.stereotype.Component;
import org.stars.spring.util.ClassUtils;

import java.lang.reflect.Proxy;

/**
 * @author : xian
 */
@Component
public class HusbandMother implements FactoryBean<IMother> {


    @Override
    public IMother getObject() throws BeansException {
        return (IMother) Proxy.newProxyInstance(ClassUtils.getDefaultClassLoader(), new Class[]{IMother.class},
                (proxy, method, args) -> "婚后媳妇妈妈的职责被婆婆代理了！" + method.getName());
    }

    @Override
    public Class<?> getObjectType() {
        return IMother.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
