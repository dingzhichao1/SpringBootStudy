package com.blackstar.springbootstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description：SpringBoot的启动类
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2020/11/14 10:57
 */

@SpringBootApplication
public class SpringBootStudyApplication {

    /**
     * <p>@SpringBootApplication是由@SpringBootConfiguration、@EnableAutoConfiguration、@ComponentScan</p>
     *  三个注解组合而成的。
     *
     *  注解介绍：
     *  @SpringBootConfiguration 同@Configuration 申明当前类是配置类，本质上也是一个@Componet
     *  @EnableAutoConfiguration 开启AutoConfiguration配置类
     *  @ComponetScan 扫描通过IOC注入的实例
     *  @Import 1、将导入的类注入到IOC容器中
     *
     *
     *  相关类：
     *  ImportSelector  查询自动导
     *  ServletWebServerFactory   ServletWebServerFactoryAutoConfiguration
     *
     *  ImportSelector
     *
     *  spring.boot.enableautoconfiguration  EnableAutoConfiguration 的开启关闭,一般来说不关闭，如果
     *  关闭会导致内置的tomcat无法启动
     *
     *
     */




    public static void main(String[] args) {
        SpringApplication.run(SpringBootStudyApplication.class, args);
    }
}
