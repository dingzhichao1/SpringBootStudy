package com.blackstar.springbootstudy.common.util.office.excel.style.entity;

import com.blackstar.springbootstudy.common.util.office.excel.annotation.CellStyle;
import com.blackstar.springbootstudy.common.util.office.excel.constant.CellStyleSetterEnum;
import lombok.Data;

/**
 * Description：边框颜色
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/6/3 9:31
 */
@Data
public final class BorderColor implements ExcelStyle {

    /**
     * 左边框颜色
     */
    @CellStyle(setterName = CellStyleSetterEnum.setLeftBorderColor)
    private  short leftBorderColor;

    /**
     * 右边框颜色
     */
    @CellStyle(setterName = CellStyleSetterEnum.setRightBorderColor)
    private  short rightBorderColor;

    /**
     * 上边框颜色
     */
    @CellStyle(setterName = CellStyleSetterEnum.setTopBorderColor)
    private  short topBorderColor;

    /**
     * 下边框颜色
     */
    @CellStyle(setterName = CellStyleSetterEnum.setBottomBorderColor)
    private  short bottomBorderColor;

}
