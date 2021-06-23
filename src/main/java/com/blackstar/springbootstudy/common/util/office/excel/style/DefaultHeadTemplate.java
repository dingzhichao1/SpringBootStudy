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
// * Description：Head表头样式模板
// *
// * @author zhichao.ding
// * @version 1.0
// * @date 2021/6/2 10:18
// */
//@Data
//public class DefaultHeadTemplate extends  ExcelStyleTemplate{
//
//    @Override
//    protected void buildCellStyle(XSSFCellStyle style) {
//        style.setFillForegroundColor(HSSFColor.WHITE.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//    }
//
//    @Override
//    protected void buildFontStyle(XSSFFont font) {
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//    }
//
//
//
//}
