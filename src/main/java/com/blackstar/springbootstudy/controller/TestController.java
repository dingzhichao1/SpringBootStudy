package com.blackstar.springbootstudy.controller;

import com.blackstar.springbootstudy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Description：
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2020/11/16 9:39
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private TestService testService;

    @GetMapping("test1")
    public String test1(){
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Object dictValue_1139 = valueOperations.get("dictValue_1139");

        //testService.test2();
        //testService.test3();
        //testService.test4();
        testService.test5();
        //testService.test6();
        return "测试AutoConfigurationImportSelector"+dictValue_1139;
    }


    public static void main(String[] args) {
        String[] split = "ssss".split(",");
        System.out.println(split[0]);
    }

}
