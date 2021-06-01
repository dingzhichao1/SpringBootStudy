package com.blackstar.springbootstudy.common.util.excel.style.temlate.cell;

import com.blackstar.springbootstudy.common.util.excel.style.temlate.CellStyle;
import com.blackstar.springbootstudy.common.util.excel.style.temlate.CellStyleEnum;
import com.blackstar.springbootstudy.common.util.excel.style.temlate.font.FontTemplate1;
import lombok.Data;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;

/**
 * Description：CellStyle的模板继承AbstractCellStyleTemplate
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/6/1 9:53
 */
@Data
public  class DefaultHeaderCellStyleTemplate extends AbstractCellStyleTemplate {

    @CellStyle(value = CellStyleEnum.COLOR)
    public XSSFColor color= new XSSFColor(java.awt.Color.gray);

    @CellStyle(value = CellStyleEnum.FILL_PATTERN)
    public  short fillPattern =XSSFCellStyle.SOLID_FOREGROUND;

    public  short borderLeft=XSSFCellStyle.BORDER_THIN;

    public  short borderRight=XSSFCellStyle.BORDER_THIN;

    public  short borderTop=XSSFCellStyle.BORDER_THIN;

    public  short borderBottom=XSSFCellStyle.BORDER_THIN;

    public  short alignment=XSSFCellStyle.ALIGN_CENTER;

    @CellStyle(value = CellStyleEnum.VERTIC_ALALIGNMENT)
    public  short verticalAlignment=XSSFCellStyle.VERTICAL_CENTER;

    protected XSSFFont font= FontTemplate1.getFontTemplate();

}
