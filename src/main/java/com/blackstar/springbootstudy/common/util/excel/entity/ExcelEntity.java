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
         * 标题
         */
        private TitleEntity titleEntity;


        /**
         * 数据
         */
        private List<T>  dataList;

        /**
         *  列名
         */
        private List<HeaderEntity>  headers;


    }

    /**
     *  Excel表格头
     */
    @Data
    public static class  HeaderEntity{
        /**
         * 标题名
         */
        private String name;


        /**
         * 字段名
         */
        private String fieldName;

    }


    /**
     * Excel表格标题
     */
    @Data
    public static class TitleEntity{
        /**
         * 标题名
         */
        private String name;

        /**
         * 标题所占列数
         */
        private Integer colNums;


        /**
         * 标题所占行数
         */
        private Integer rowNums;


    }


}



