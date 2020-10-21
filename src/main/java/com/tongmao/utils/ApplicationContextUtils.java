package com.tongmao.utils;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public class ApplicationContextUtils implements ApplicationContextAware {
    private static ApplicationContext context;

    private ApplicationContextUtils() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public static <T> T getBean(String id) {
        if(context.containsBean(id))
        return (T) context.getBean(id);
        else return null;
    }
}
