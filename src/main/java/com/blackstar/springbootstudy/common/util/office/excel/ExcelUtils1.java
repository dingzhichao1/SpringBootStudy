//package com.blackstar.springbootstudy.common.util.office.excel;
//
//import com.blackstar.springbootstudy.common.util.office.excel.config.TitleConfig;
//import com.blackstar.springbootstudy.common.util.office.excel.entity.ExcelEntity;
//import com.blackstar.springbootstudy.common.util.office.excel.style.temlate.CellStyleBuilder;
//import com.blackstar.springbootstudy.common.util.office.excel.style.temlate.Test;
//import com.blackstar.springbootstudy.common.util.office.excel.style.temlate.cell.AbstractCellStyleTemplate;
//import com.blackstar.springbootstudy.common.util.office.excel.style.temlate.cell.DefaultHeaderCellStyleTemplate;
//import com.blackstar.springbootstudy.config.exception.BusinessException;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.xssf.usermodel.*;
//import org.springframework.util.Assert;
//import org.springframework.util.CollectionUtils;
//
//import java.io.*;
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
///**
// * Description：Excel工具类
// *
// * @author zhichao.ding
// * @version 1.0
// * @date 2021/5/31 15:46
// */
//@SuppressWarnings("Duplicates")
//public class ExcelUtils1<T> {
//
//    private final static int DEFAULT_TITLE_ROW = 0;
//
//
//
//    public static <T> void exportExcel2007(ExcelEntity<T> excelEntity, OutputStream out, TitleConfig titleConfig, AbstractCellStyleTemplate headerCellTemplate) {
//        Assert.notNull(excelEntity, "Excel的实体类不能为空");
//        List<ExcelEntity.SheetEntity> excelSheets = excelEntity.getExcelSheets();
//
//        Assert.notEmpty(excelSheets, "Sheet列表不能为空");
//        for (ExcelEntity.SheetEntity excelSheet : excelSheets) {
//            // 声明一个工作薄
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            // 生成一个表格
//            XSSFSheet sheet = workbook.createSheet(excelSheet.getSheetName());
//            //获取标题
//            int rowIndex = 0;
//            ExcelEntity.TitleEntity titleEntity = excelSheet.getTitleEntity();
//
//            if (titleEntity != null) {
//                String titleName = titleEntity.getName();
//                Assert.notNull(titleName, "标题不能为空");
//                XSSFRow titleRow = sheet.createRow(rowIndex);
//                //通过默认的模板样式
//                XSSFCellStyle headerCellStyle = new CellStyleBuilder(workbook).build(headerCellTemplate);
//                XSSFCell cell = titleRow.createCell(0);
//                cell.setCellValue(titleName);
//                cell.setCellStyle(headerCellStyle);
//                XSSFSheet sheet1 = titleRow.getSheet();
//                if (titleConfig != null) {
//                    boolean autoMergedRegion = titleConfig.isAutoMergedRegion();
//                    if (autoMergedRegion) {
//                        //标题所占列数
//                        Integer titleColNum = titleEntity.getColNums();
//                        if (titleColNum == null) {
//                            //TODO
//                            titleColNum = excelSheet.getHeaders().size();
//                        }
//                        //标题所占行数
//                        Integer rowNums = titleEntity.getRowNums();
//                        if (rowNums == null) {
//                            rowNums = rowIndex;
//                        }
//                        int firstRow = rowIndex;
//                        int lastRow = firstRow + rowNums - 1;
//                        sheet1.addMergedRegion(new CellRangeAddress(rowIndex, lastRow, 0, titleColNum));
//                        rowIndex = ++lastRow;
//                    } else {
//                        //不合并单元格
//
//
//                    }
//
//                }
//
//                //按照不合并单元格
//
//            }
//
//            Map<String, Integer> map = new HashMap<>();
//
//            //tab名
//            List<ExcelEntity.HeaderEntity> headers = excelSheet.getHeaders();
//            if (!CollectionUtils.isEmpty(headers)) {
//                int colIndex = 0;
//                XSSFRow row = sheet.createRow(rowIndex);
//                for (ExcelEntity.HeaderEntity header : headers) {
//                    String name = header.getName();
//                    XSSFCell cell = row.createCell(colIndex);
//                    XSSFCellStyle headerCellStyle = new CellStyleBuilder(workbook).build(headerCellTemplate);
//                    cell.setCellStyle(headerCellStyle);
//                    cell.setCellValue(name);
//                    //设置保存字段的列
//                    String fieldName = header.getFieldName();
//                    map.put(fieldName, colIndex++);
//                }
//                rowIndex++;
//            }
//
//
//            //data数据
//            List<T> dataList = excelSheet.getDataList();
//            if (!CollectionUtils.isEmpty(dataList)) {
//                for (T data : dataList) {
//                    Class<?> aClass = data.getClass();
//                    Field[] fields = aClass.getDeclaredFields();
//                    if (fields != null) {
//                        XSSFRow row = sheet.createRow(rowIndex);
//                        XSSFCellStyle dataCellStyle = new CellStyleBuilder(workbook).build(headerCellTemplate);
//                        for (int i = 0; i < fields.length; i++) {
//                            Field field = fields[i];
//                            field.setAccessible(true);
//                            Object fieldValue = null;
//                            try {
//                                fieldValue = field.get(data);
//                            } catch (IllegalAccessException e) {
//                                e.printStackTrace();
//                            }
//                            Integer colIndex = map.get(field.getName());
//                            if (colIndex == null) {
//                                throw new BusinessException("请检验字段");
//                            }
//                            XSSFCell cell = row.createCell(colIndex);
//                            if (fieldValue instanceof String) {
//
//                                cell.setCellValue((String) fieldValue);
//                            } else if (fieldValue instanceof Integer) {
//
//                                cell.setCellValue((Integer) fieldValue);
//                            }
//
//                            cell.setCellStyle(dataCellStyle);
//
//                        }
//                        rowIndex++;
//
//                    }
//
//
//                }
//
//            }
//
//
//            try {
//                workbook.write(out);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    workbook.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//
//    }
//
//
//    public static void main(String[] args) {
//
//        FileOutputStream fileOutputStream = null;
//        try {
//            fileOutputStream = new FileOutputStream(new File("D://工作目标.xls"));
//            ExcelEntity<String> excelEntity = new ExcelEntity<>();
//            excelEntity.setFileName("D://工作目标.xls");
//            ExcelEntity.SheetEntity sheetEntity = new ExcelEntity.SheetEntity();
//            sheetEntity.setSheetName("秒查数据导出1");
//            ExcelEntity.TitleEntity titleEntity = new ExcelEntity.TitleEntity();
//            sheetEntity.setTitleEntity(titleEntity);
//            titleEntity.setName("六一儿童节快乐");
//            titleEntity.setRowNums(3);
//            titleEntity.setColNums(10);
//
//
//            List<ExcelEntity.SheetEntity> list = Stream.of(sheetEntity).collect(Collectors.toList());
//            excelEntity.setExcelSheets(list);
//            TitleConfig titleConfig = new TitleConfig();
//            titleConfig.setAutoMergedRegion(true);
//
//            ExcelEntity.HeaderEntity headerEntity = new ExcelEntity.HeaderEntity();
//            headerEntity.setFieldName("name");
//            headerEntity.setName("用户");
//
//
//            ExcelEntity.HeaderEntity headerEntity1 = new ExcelEntity.HeaderEntity();
//            headerEntity1.setFieldName("age");
//            headerEntity1.setName("年龄");
//
//            List<ExcelEntity.HeaderEntity> objects = new ArrayList<>();
//            objects.add(headerEntity);
//            objects.add(headerEntity1);
//            sheetEntity.setHeaders(objects);
//
//
//            List<Test> objects1 = new ArrayList<>();
//
//            Test test1 = new Test();
//            test1.setName("王大锤");
//            test1.setAge(11);
//            objects1.add(test1);
//
//
//            Test test2 = new Test();
//            test2.setName("白客");
//            test2.setAge(18);
//            objects1.add(test2);
//
//
//            sheetEntity.setDataList(objects1);
//            ExcelUtils1.exportExcel2007(excelEntity, fileOutputStream, titleConfig, new DefaultHeaderCellStyleTemplate());
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//}
