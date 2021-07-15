package org.stars.spring.context.event;

import org.stars.spring.beans.BeansException;
import org.stars.spring.beans.factory.BeanFactory;
import org.stars.spring.beans.factory.BeanFactoryAware;
import org.stars.spring.context.ApplicationEvent;
import org.stars.spring.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author : xian
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    @SuppressWarnings("unchecked")
    public void addApplicationListener(ApplicationListener<?> listeners) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listeners);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listeners) {
        applicationListeners.remove(listeners);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 获取此事件对应的所有监听器
     *
     * @param event 事件
     * @return 此事件的所有监听器
     */
    protected Collection<ApplicationListener<?>> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener<?>> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportsEvent(listener, event)) {
                allListeners.add(listener);
            }
        }

        return allListeners;
    }

    /**
     * 监听器是否对此事件进行监听
     *
     * @param listener 监听器
     * @param event    事件
     * @return true 表示监听器对此事件进行监听
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> listener, ApplicationEvent event) {
        Class<?> listenerClass = listener.getClass();

        // 按照 CglibSubclassingInstantiationStrategy、SimpleInstantiationStrategy 不同的实例化类型，需要判断后获取目标 class
        // 也就是 listener 实际的 class 类型
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;

        // 获得泛型的类型,参考 https://www.jianshu.com/p/6bb9b8d6ee7a
        // TODO 不够严谨
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];

        String className = actualTypeArgument.getTypeName();
        // listener 要监听的的 ApplicationEvent 实现
        Class<?> eventClass;

        try {
            eventClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        // 父类.isAssignableFrom(子类)
        // 用来判断 eventClass 和具体 event.getClass() 的关系
        // 如果 eventClass 是 event.getClass() 的父类或者实现的接口，则表示是 listener 监听的事件
        return eventClass.isAssignableFrom(event.getClass());
    }
}
