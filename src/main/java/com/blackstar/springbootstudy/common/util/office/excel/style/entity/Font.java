package com.blackstar.springbootstudy.common.util.office.excel.style.entity;

import com.blackstar.springbootstudy.common.util.office.excel.annotation.FontStyle;
import com.blackstar.springbootstudy.common.util.office.excel.constant.FontSetterEnum;
import lombok.Data;
import org.apache.poi.xssf.usermodel.XSSFFont;

import java.lang.reflect.Method;

/**
 * Description：字体
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/6/3 9:35
 */
@Data
public final class Font  implements ExcelStyle{

    @FontStyle(setterName = FontSetterEnum.setColor)
    short color;

    @FontStyle(setterName = FontSetterEnum.setCharSet)
    int charSet;

    @FontStyle(setterName = FontSetterEnum.setItalic)
    boolean italic;

    @FontStyle(setterName = FontSetterEnum.setBoldweight)
    short boldWeight;

    @FontStyle(setterName = FontSetterEnum.setStrikeout)
    boolean strikeout;

    @FontStyle(setterName = FontSetterEnum.setBold)
    boolean bold;

    @FontStyle(setterName = FontSetterEnum.setFontName)
    String fontName;

    @FontStyle(setterName = FontSetterEnum.setThemeColor)
    short themeColor;

    @FontStyle(setterName = FontSetterEnum.setUnderline)
    byte underline;

    @FontStyle(setterName = FontSetterEnum.setFamily)
    int family;

    @FontStyle(setterName = FontSetterEnum.setTypeOffset)
    short typeOffset;

    @FontStyle(setterName = FontSetterEnum.setFontHeight)
    short fontHeight;

    @FontStyle(setterName = FontSetterEnum.setFontHeightInPoints)
    short fontHeightInPoints;

    public static void main(String[] args) {

        Class<XSSFFont> xssfFontClass = XSSFFont.class;
        Method[] methods = xssfFontClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            if(methods[i].getName().startsWith("set")){
                System.out.print(methods[i].getName()+",");
                Class<?>[] parameterTypes = methods[i].getParameterTypes();
                for (int j = 0; j < parameterTypes.length; j++) {
                    System.out.print(parameterTypes[j].toGenericString());
                }
                System.out.println("");

            }
        }
    }

}
