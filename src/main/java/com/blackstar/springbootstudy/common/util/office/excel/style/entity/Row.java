package com.blackstar.springbootstudy.common.util.office.excel.style.entity;

import lombok.Data;

/**
 * Description：行
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/6/3 10:54
 */
@Data
public final class Row implements ExcelStyle{


    /**
     * 1/20 points
     */
    private short height;
    /**
     * 以points为单位设置行高
     */
   private float heightInPoints;



}
