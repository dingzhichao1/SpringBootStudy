package com.blackstar.springbootstudy.common.util.excel.style;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Assert;

/**
 * Description：ExcelTemplate
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/5/31 16:32
 */
public abstract class ExcelStyleTemplate {

    /**
     * 构建Excel的样式模板
     * @param workbook
     */
    public void buildExcelTemplate(XSSFWorkbook workbook){
        Assert.notNull(workbook,"workbook不能为空");
        XSSFCellStyle style = workbook.createCellStyle();
        //设置CellStyle
        buildCellStyle(style);
        XSSFFont font = workbook.createFont();
        //设置字体
        buildFontStyle(font);
        style.setFont(font);

    }


    /**
     * 设置单元格的样式
     * @param style
     */
    protected abstract void buildCellStyle(XSSFCellStyle style);


    /**
     * 设置字体的样式
     * @param font
     */
    protected abstract void buildFontStyle(XSSFFont font);





}
