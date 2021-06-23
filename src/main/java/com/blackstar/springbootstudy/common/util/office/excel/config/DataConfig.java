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
public class DataConfig extends AbstractExcelConfig {

    private boolean isAutoMergedRegion = false;

    private boolean isUseTemplate = true;

    private XSSFCellStyle dataExcelStyle = null;

}
