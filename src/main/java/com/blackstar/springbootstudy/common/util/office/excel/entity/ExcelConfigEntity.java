package com.blackstar.springbootstudy.common.util.office.excel.entity;

import com.blackstar.springbootstudy.common.util.office.excel.config.DataConfig;
import com.blackstar.springbootstudy.common.util.office.excel.config.ExcelConfig;
import com.blackstar.springbootstudy.common.util.office.excel.config.HeadConfig;
import com.blackstar.springbootstudy.common.util.office.excel.config.TitleConfig;
import lombok.Data;

/**
 * Description：Excel相关配置
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/6/2 10:29
 */
@Data
public class ExcelConfigEntity {

    /**
     * 全局的Excel配置
     */
    private ExcelConfig excelConfig=new ExcelConfig();

    /**
     *  标题的配置
     */
    private TitleConfig titleConfig=new TitleConfig();

    /**
     *  表头的配置
     */
    private HeadConfig headConfig=new HeadConfig();

    /**
     *  数据的配置
     */
    private DataConfig dataConfig=new DataConfig();


}
