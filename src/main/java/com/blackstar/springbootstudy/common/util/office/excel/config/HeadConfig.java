package com.blackstar.springbootstudy.common.util.office.excel.config;

import lombok.Data;
/**
 * Description：标题的配置
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/6/2 13:05
 */
@Data
public class HeadConfig extends AbstractExcelConfig {

    private boolean isUseTemplate = true;


}