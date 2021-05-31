package com.blackstar.springbootstudy.common.util.excel.entity;

import lombok.Data;

import java.util.List;

/**
 * Description：Excel实体
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/5/31 15:48
 */
@Data
public class ExcelEntity<T> {

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * Sheet列表
     */
    private List<SheetEntity> excelSheets;


    /**
     * ExcelSheet实体
     * @param <T>
     */
    @Data
    public static class SheetEntity<T>{

        /**
         * sheet的唯一标识
         */
        private Integer sheetId;

        /**
         * sheet的名称
         */
        private String sheetName;

        /**
         * 数据
         */
        private List<T>  dataList;


        /**
         *  列名
         */
        private List<String>  dataNameList;


    }



}



