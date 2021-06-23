package com.blackstar.springbootstudy.common.util.office.excel.style.temlate.cell;

import com.blackstar.springbootstudy.common.util.office.excel.style.temlate.CellStyle;
import com.blackstar.springbootstudy.common.util.office.excel.style.temlate.CellStyleEnum;
import com.blackstar.springbootstudy.common.util.office.excel.style.temlate.font.FontTemplate1;
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
    public  short fillPattern =0;

    public  short borderLeft=0;

    public  short borderRight=0;

    public  short borderTop=0;

    public  short borderBottom=0;

    public  short alignment=0;

    @CellStyle(value = CellStyleEnum.VERTIC_ALALIGNMENT)
    public  short verticalAlignment=0;

    protected XSSFFont font= FontTemplate1.getFontTemplate();

}
