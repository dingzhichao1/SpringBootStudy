package com.blackstar.springbootstudy.common.util.excel.style.temlate.font;

import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;

/**
 * Description：字体模板1
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/5/31 17:54
 */

public final class FontTemplate1 extends XSSFFont{

    public static volatile XSSFFont xssfFont=null;

    private FontTemplate1(){

    }
    public  static XSSFFont getFontTemplate(){
        if(xssfFont==null){
            synchronized (FontTemplate1.class){
                if(xssfFont==null){
                    xssfFont= new FontTemplate1();
                    xssfFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
                    xssfFont.setFontName("宋体");
                    xssfFont.setColor(new XSSFColor(java.awt.Color.BLACK));
                    xssfFont.setFontHeightInPoints((short) 11);
                }
            }
        }
        return xssfFont;
    }

}
