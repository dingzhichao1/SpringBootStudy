package com.blackstar.springbootstudy.common.util.excel.style;

import lombok.Data;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;

/**
 * Description：默认的Excel模板
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/5/31 16:13
 */
@Data
public class DefaultExcelTemplate extends ExcelStyleTemplate {


    @Override
    public final void buildCellStyle(XSSFCellStyle style){
        // 设置单元格样式
        style.setFillForegroundColor(new XSSFColor(java.awt.Color.gray));
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
    }


    @Override
    public final void buildFontStyle(XSSFFont font){
        //设置字体样式
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setColor(new XSSFColor(java.awt.Color.BLACK));
        font.setFontHeightInPoints((short) 11);
    }




}
