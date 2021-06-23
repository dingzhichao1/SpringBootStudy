package com.blackstar.springbootstudy.controller;

import com.blackstar.springbootstudy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

    @Resource
    private HttpServletResponse response;

    @Resource
    private HttpServletRequest request;

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

    @GetMapping("get")
    public void get(){
        String head="测试.xlsx";
        String filename="";
        String userAgent=request.getHeader("User-Agent");
        if (userAgent.contains("MSIE") ||userAgent.contains("Trident")||userAgent.contains("Edge")) {
            try {
                filename = URLEncoder.encode(head, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            try {
                filename = new String((head).getBytes("UTF-8"), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        String fileNameURL = null;
        try {
            fileNameURL = URLEncoder.encode(head, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //response.setHeader("Content-disposition", "attachment;filename="+fileNameURL+";"+"filename*=utf-8''"+fileNameURL);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Content-Type", "application/octet-stream;charset=utf-8");
        response.setHeader("fileName", head);
        response.setHeader("Content-Disposition", "attachment;filename=" +head);
    }

    @PostMapping("download")
    public void download(){
        String head="测试.xlsx";
        String filename="";
        String userAgent=request.getHeader("User-Agent");
        if (userAgent.contains("MSIE") ||userAgent.contains("Trident")||userAgent.contains("Edge")) {
            try {
                filename = URLEncoder.encode(head, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            try {
                filename = new String((head).getBytes("UTF-8"), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        String fileNameURL = null;
        try {
            fileNameURL = URLEncoder.encode(head, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-disposition", "attachment;filename="+fileNameURL+";"+"filename*=utf-8''"+fileNameURL);
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Content-Type", "application/octet-stream;charset=utf-8");
//        response.setHeader("fileName", head);
//        response.setHeader("Content-Disposition", "attachment;filename=" +head);
    }

    public static void main(String[] args) {
        String[] split = "ssss".split(",");
        System.out.println(split[0]);
    }

}
