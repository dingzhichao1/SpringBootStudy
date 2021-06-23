package com.blackstar.springbootstudy.common.util.office.excel.config;

import lombok.Data;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

/**
 * Description：标题的配置
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/6/2 13:05
 */
@Data
public class TitleConfig extends AbstractExcelConfig {
    /**
     * 是否自动合并单元格
     */
    private boolean isAutoMergedRegion = true;

    private boolean isUseTemplate = true;

    /**
     * 通过传入自定义的ExcelStyle来修改模板的配置
     */
    private XSSFCellStyle titleExcelStyle = null;

}