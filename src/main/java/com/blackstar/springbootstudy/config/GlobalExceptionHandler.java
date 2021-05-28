package com.blackstar.springbootstudy.config;

import com.blackstar.springbootstudy.config.exception.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Description：全局异常处理
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/5/28 13:23
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public ResultVo handle(){

        return ResultVo.ok("业务出现异常");

    }






}
