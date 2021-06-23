//package com.blackstar.springbootstudy.common.util.office.excel.style;
//
//import lombok.Data;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//
///**
// * Description：默认的Excel模板
// *
// * @author zhichao.ding
// * @version 1.0
// * @date 2021/5/31 16:13
// */
//@Data
//public class DefaultTitleTemplate extends ExcelStyleTemplate {
//
//
//    @Override
//    public final void buildCellStyle(XSSFCellStyle style){
//        // 设置单元格样式
//        style.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//    }
//
//
//    @Override
//    public final void buildFontStyle(XSSFFont font){
//        //设置字体样式
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        font.setFontName("宋体");
//        font.setColor(HSSFColor.WHITE.index);
//        font.setFontHeightInPoints((short) 11);
//    }
//
//
//
//
//}
