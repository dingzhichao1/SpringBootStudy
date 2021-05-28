package com.blackstar.springbootstudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * Description：自定义的WebMvcConfigurer
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/5/28 10:40
 */

/**
 * WebMvcConfigurerAdapter  仅仅是对WebMvcConfigurer进行了空实现，现在由于Java1.8已经
 * 允许接口默认实现，所以该抽象类已经没有了存在的必要
 * SpringBoot中的默认实现是WebMvcAutoConfigurationAdapter，
 *
 *
 *
 */

//@Component
@Configuration
public class CustomerWebMvcConfigurer implements  WebMvcConfigurer {



    /**
     * 跨域支持（本质是过滤器）
     * @param registry registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS").maxAge(3600);
    }


}
