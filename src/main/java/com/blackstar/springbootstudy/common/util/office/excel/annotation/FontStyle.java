package com.blackstar.springbootstudy.common.util.office.excel.annotation;

import com.blackstar.springbootstudy.common.util.office.excel.constant.FontSetterEnum;

import java.lang.annotation.*;

/**
 * Description：Excel需要输出的字段
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/6/2 15:13
 *
 * 该注解的作用是用于匹配XSSFCellStyle中的setXX方法,用于动态修改Excel的样式
 *
 * @see org.apache.poi.xssf.usermodel.XSSFFont
 */
@Documented
@Inherited
@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface FontStyle {
    /**
     * 字段对应的XSSFFont中的setXX方法
     *
     * @return
     */
    FontSetterEnum setterName();

}
