package workstation.spring.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/11/16.
 */

@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.CONTEXT = applicationContext;
    }

    public static ApplicationContext getContext() {
        return CONTEXT;
    }

    public static <T> T getBean(Class<T> clazz) {
        return CONTEXT.getBean(clazz);
    }

    public static <T> T getBean(String beanName) {
        return (T)CONTEXT.getBean(beanName);
    }
}
