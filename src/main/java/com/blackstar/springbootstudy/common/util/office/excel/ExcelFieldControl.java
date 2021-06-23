package com.blackstar.springbootstudy.common.util.office.excel;
import com.blackstar.springbootstudy.common.util.office.excel.style.temlate.font.FontTemplate1;
import org.apache.poi.xssf.usermodel.XSSFFont;
import java.lang.annotation.*;

/**
 * Description：ExcelTemplate
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/5/31 16:32
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelFieldControl {



    // 列头名称
    String header();

    //列的字体样式
    Class<? extends XSSFFont> fontStyle() default FontTemplate1.class;

    // 是否忽略该属性
    boolean ignore() default false;
}
