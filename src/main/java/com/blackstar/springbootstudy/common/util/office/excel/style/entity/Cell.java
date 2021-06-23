package com.blackstar.springbootstudy.common.util.office.excel.style.entity;

import com.blackstar.springbootstudy.common.util.office.excel.annotation.CellStyle;
import com.blackstar.springbootstudy.common.util.office.excel.constant.CellStyleSetterEnum;
import lombok.Data;

/**
 * Description：单元格
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/6/3 10:38
 */
@Data
public class Cell implements ExcelStyle{

    @CellStyle(setterName = CellStyleSetterEnum.setFillBackgroundColor)
    private short fillBackgroundColor;

    @CellStyle(setterName = CellStyleSetterEnum.setFillForegroundColor)
    private short fillForegroundColor ;

    @CellStyle(setterName = CellStyleSetterEnum.setVerticalAlignment)
    private short verticalAlignment;

    @CellStyle(setterName = CellStyleSetterEnum.setLocked)
    private boolean locked;

    @CellStyle(setterName = CellStyleSetterEnum.setDataFormat)
    private short dataFormat;

    @CellStyle(setterName = CellStyleSetterEnum.setAlignment)
    private short alignment;

    @CellStyle(setterName = CellStyleSetterEnum.setFillPattern)
    private short fillPattern;

    @CellStyle(setterName = CellStyleSetterEnum.setWrapText)
    private boolean wrapText;

    @CellStyle(setterName = CellStyleSetterEnum.setShrinkToFit)
    private boolean shrinkToFit;

    @CellStyle(setterName = CellStyleSetterEnum.setIndention)
    private short indention;

    @CellStyle(setterName = CellStyleSetterEnum.setRotation)
    private short rotation;

    @CellStyle(setterName = CellStyleSetterEnum.setHidden)
    private boolean hidden;



}
