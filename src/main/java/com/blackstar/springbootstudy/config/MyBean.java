package com.blackstar.springbootstudy.config;

public class MyBean{

    private String beanName;


    public MyBean(){
        System.out.println("创建Bean对象");

    }

    public static void main(String[] args) {
        ResultVo resultVo = new ResultVo.ResultBuilder<String>().setCode(201).setData("这是一个好人啊").setMessage("失败").build();
        System.out.println(resultVo);

    }


}



