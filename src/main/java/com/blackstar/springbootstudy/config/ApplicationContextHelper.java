package com.blackstar.springbootstudy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Description：帮助非Spring管理的对象获取Spring管理的对象
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/3/19 13:44
 */

@Component
public class ApplicationContextHelper {

    private static ApplicationContext context;

    @Autowired
    private void  setApplicationContext(ApplicationContext applicationContext){
        context=applicationContext;
    }

    public  static <T> T getBean(Class<T> tClass){
        return context.getBean(tClass);
    }



}
