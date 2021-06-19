package org.stars.spring.context.support;

/**
 * @author : xian
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

    private String[] configLocations;

    public ClassPathXmlApplicationContext(){}

    /**
     * 从 xml 加载 BeanDefinition 并刷新上下文
     * @param configLocation 配置文件
     */
    public ClassPathXmlApplicationContext(String configLocation){
        this(new String[]{configLocation});
    }

    /**
     * 从 xml 加载 BeanDefinition 并刷新上下文
     * @param configLocations  配置文件集合
     */
    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }


    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
