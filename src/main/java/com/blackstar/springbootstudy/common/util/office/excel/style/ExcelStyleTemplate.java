package com.blackstar.springbootstudy.common.util.office.excel.style;

import lombok.Data;
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
@Data
public abstract class ExcelStyleTemplate {

    public XSSFWorkbook workbook;

    /**
     * 行高
     */
    protected Short rowHeight;

    /**
     * 单位：points  行高
     */
    private Float rowHeightInPoints;

    /**
     * 构建Excel的样式模板
     * @param workbook
     */
    public XSSFCellStyle build(XSSFWorkbook workbook){
        Assert.notNull(workbook,"workbook不能为空");
        XSSFCellStyle style = workbook.createCellStyle();
        //设置CellStyle
        buildCellStyle(style);
        XSSFFont font = workbook.createFont();
        //设置字体
        buildFontStyle(font);
        style.setFont(font);
        this.workbook=workbook;
        return style;
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
