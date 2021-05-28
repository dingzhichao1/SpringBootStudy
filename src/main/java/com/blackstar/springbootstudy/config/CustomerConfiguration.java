package com.blackstar.springbootstudy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description：
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2020/11/14 13:50
 */

@Configuration
public class CustomerConfiguration {

    @Value("${config.test.value}")
    private String test;

    @Bean
    public MyBean  myBean(){
        return new MyBean();
    }


}




