package com.blackstar.springbootstudy.common.util.office.excel.config;

import lombok.Data;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

/**
 * Description：抽象的ExcelConfig
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/6/2 13:05
 */
@Data
public abstract class AbstractExcelConfig {

     boolean isUseTemplate;

     boolean isAutoMergedRegion;

     XSSFCellStyle titleExcelStyle;

}
