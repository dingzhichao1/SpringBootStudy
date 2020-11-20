package com.blackstar.springbootstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description：
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2020/11/16 9:39
 */
@Controller
@RequestMapping("test")
public class TestController {

    @GetMapping("test1")
    public void test1(){
        System.out.println("测试AutoConfigurationImportSelector");
    }


    public static void main(String[] args) {
        String[] split = "ssss".split(",");
        System.out.println(split[0]);
    }

}
