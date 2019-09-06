package cn.caohangwei.dmlive.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * Spring ApplicationContext util.
 */
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext context = null;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz){
        T t = null;
        Map<String,T> map = context.getBeansOfType(clazz);
        for(Map.Entry<String,T> entry : map.entrySet()){
            t = entry.getValue();
        }
        return t;
    }
}
