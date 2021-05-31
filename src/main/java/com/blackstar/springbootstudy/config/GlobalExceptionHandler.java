package com.blackstar.springbootstudy.config;

import com.blackstar.springbootstudy.config.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description：全局异常处理
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/5/28 13:23
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResultVo handle(BusinessException e){
        log.error(e.getMessage(),e);
        return ResultVo.error("业务出现异常");
    }






}
