package com.blackstar.springbootstudy.config.exception;

/**
 * Description：业务执行异常
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/5/28 13:34
 */

public class BusinessException extends RuntimeException{

    public BusinessException(String msg){
        super(msg);
    }


}
