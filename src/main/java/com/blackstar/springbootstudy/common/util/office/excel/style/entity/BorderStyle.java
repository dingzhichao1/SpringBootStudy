package com.blackstar.springbootstudy.common.util.office.excel.style.entity;


import com.blackstar.springbootstudy.common.util.office.excel.annotation.CellStyle;
import com.blackstar.springbootstudy.common.util.office.excel.constant.CellStyleSetterEnum;
import lombok.Data;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.lang.reflect.Method;

/**
 * Description：Excel边框实体
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/6/3 9:27
 */
@Data
public final class BorderStyle implements ExcelStyle{
    /**
     * 左边框线粗
     */
    @CellStyle(setterName = CellStyleSetterEnum.setBorderLeft)
    public  short borderLeft;

    /**
     * 右边框线粗
     */
    @CellStyle(setterName = CellStyleSetterEnum.setBorderRight)
    public  short borderRight;

    /**
     * 上边框线粗
     */
    @CellStyle(setterName = CellStyleSetterEnum.setBorderTop)
    public  short borderTop;


    /**
     * 下边框线粗
     */
    @CellStyle(setterName = CellStyleSetterEnum.setBorderBottom)
    public  short borderBottom;

    public static void main(String[] args) {
        Class<XSSFCellStyle> xssfCellStyleClass = XSSFCellStyle.class;
        Method[] methods = xssfCellStyleClass.getMethods();
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
