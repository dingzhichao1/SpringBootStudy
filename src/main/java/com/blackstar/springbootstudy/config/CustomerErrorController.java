package com.blackstar.springbootstudy.config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description：自定义异常Controller
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/5/28 13:14
 */

@RestController
public class CustomerErrorController implements ErrorController {

    private static final String DEFAULT_ERROR_PATH="error";

    @Override
    public String getErrorPath() {
        return DEFAULT_ERROR_PATH;
    }

    @RequestMapping(DEFAULT_ERROR_PATH)
    public ResultVo error(){
        return ResultVo.ok();
    }



}
