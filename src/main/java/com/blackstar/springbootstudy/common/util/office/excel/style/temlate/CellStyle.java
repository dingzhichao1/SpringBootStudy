package com.blackstar.springbootstudy.common.util.office.excel.style.temlate;

import java.lang.annotation.*;

/**
 * Description：单元格样式
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/6/1 11:24
 */

@Documented
@Inherited
@Target(value = {ElementType.FIELD,ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CellStyle {

    CellStyleEnum value();


}
