package com.blackstar.springbootstudy.common.util.office.excel;

import com.blackstar.springbootstudy.common.util.office.excel.style.temlate.CellStyle;
import com.blackstar.springbootstudy.common.util.office.excel.style.temlate.CellStyleEnum;
import lombok.Data;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
/**
 * Description：新增的
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/6/2 16:39
 */
@Data
public abstract class AbstractCellStyle  {

    @CellStyle(value = CellStyleEnum.COLOR)
    public  XSSFColor color;

    @CellStyle(value = CellStyleEnum.FILL_PATTERN)
    public  short fillPattern ;

    @CellStyle(value = CellStyleEnum.BORDER_LEFT)
    public  short borderLeft;

    @CellStyle(value = CellStyleEnum.BORDER_RIGHT)
    public  short borderRight;

    @CellStyle(value = CellStyleEnum.BORDER_TOP)
    public  short borderTop;

    @CellStyle(value = CellStyleEnum.BORDER_BOTTOM)
    public  short borderBottom;

    @CellStyle(value = CellStyleEnum.ALIGNMENT)
    public  short alignment;

    @CellStyle(value = CellStyleEnum.FONT)
    protected XSSFFont font;

    @CellStyle(value = CellStyleEnum.VERTIC_ALALIGNMENT)
    protected short verticalAlignment;


}
