//package com.blackstar.springbootstudy.common.util.office.excel;
//
//import com.blackstar.springbootstudy.common.util.office.excel.config.AbstractExcelConfig;
//import com.blackstar.springbootstudy.common.util.office.excel.config.DataConfig;
//import com.blackstar.springbootstudy.common.util.office.excel.config.HeadConfig;
//import com.blackstar.springbootstudy.common.util.office.excel.config.TitleConfig;
//import com.blackstar.springbootstudy.common.util.office.excel.entity.ExcelConfigEntity;
//import com.blackstar.springbootstudy.common.util.office.excel.entity.ExcelEntity;
//import com.blackstar.springbootstudy.common.util.office.excel.entity.ExcelStyleTemplateEntity;
//import com.blackstar.springbootstudy.common.util.office.excel.style.ExcelStyleTemplate;
//import com.blackstar.springbootstudy.common.util.office.excel.style.temlate.Test;
//import com.blackstar.springbootstudy.config.exception.BusinessException;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.xssf.usermodel.*;
//import org.springframework.util.Assert;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//
//import java.io.*;
//import java.lang.reflect.Field;
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.*;
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
//public class ExcelUtils<T> {
//
//
//
//    public static <T> void exportExcel2007(ExcelEntity<T> excelEntity) {
//        exportExcel2007(excelEntity, new ExcelStyleTemplateEntity(), new ExcelConfigEntity());
//    }
//
//    public static <T> void exportExcel2007(ExcelEntity<T> excelEntity, ExcelStyleTemplateEntity excelStyleEntity, ExcelConfigEntity excelConfigEntity) {
//        String path = excelEntity.getPath();
//        if (StringUtils.isEmpty(path)) {
//            throw new BusinessException("文件保存路径不能为空");
//        }
//        String fileName = excelEntity.getFileName();
//        if (StringUtils.isEmpty(fileName)) {
//            fileName = UUID.randomUUID().toString();
//        }
//        FileOutputStream out = null;
//        try {
//            out = new FileOutputStream(new File(path, fileName + ".xls"));
//            exportExcel2007(excelEntity, excelStyleEntity, excelConfigEntity, out);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     *
//     * @param excelEntity
//     * @param excelStyleEntity
//     * @param excelConfigEntity
//     * @param out
//     * @param <T>
//     */
//    public static <T> void exportExcel2007(ExcelEntity<T> excelEntity, ExcelStyleTemplateEntity excelStyleEntity, ExcelConfigEntity excelConfigEntity, OutputStream out) {
//        Assert.notNull(excelEntity, "Excel的实体类不能为空");
//        List<ExcelEntity.SheetEntity> excelSheets = excelEntity.getExcelSheets();
//        Assert.notEmpty(excelSheets, "Sheet列表不能为空");
//        for (ExcelEntity.SheetEntity excelSheet : excelSheets) {
//            // 声明一个工作薄
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            // 生成一个表格
//            XSSFSheet sheet = workbook.createSheet(excelSheet.getSheetName());
//
//            //获取标题
//            int rowIndex = 0;
//
//            //获取总的列数
//            int totalColNums = getTotalColNum(excelSheet);
//
//            //设置标题
//            ExcelEntity.TitleEntity titleEntity = excelSheet.getTitleEntity();
//            ExcelStyleTemplate titleExcelStyle = excelStyleEntity.getTitleStyleTemplate();
//            TitleConfig titleConfig = excelConfigEntity.getTitleConfig();
//            rowIndex = setTitle(titleEntity, titleExcelStyle, titleConfig, rowIndex, sheet, workbook, totalColNums);
//
//            //tab Head
//            Map<String, Integer> fieldIndexMap = new HashMap<>();
//            List<ExcelEntity.HeaderEntity> headers = excelSheet.getHeaders();
//            HeadConfig headConfig = excelConfigEntity.getHeadConfig();
//            ExcelStyleTemplate headExcelStyle = excelStyleEntity.getHeadStyleTemplate();
//            rowIndex = setHeader(headers, headExcelStyle, headConfig, rowIndex, sheet, workbook, fieldIndexMap, totalColNums);
//
//
//
//            //data数据
//            List<T> dataList = excelSheet.getDataList();
//            DataConfig dataConfig = excelConfigEntity.getDataConfig();
//            ExcelStyleTemplate dataExcelStyle = excelStyleEntity.getDataStyleTemplate();
//            rowIndex = setDataList(dataList, dataExcelStyle, dataConfig, rowIndex, sheet, workbook, fieldIndexMap, totalColNums);
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
//    /**
//     * 设置标题
//     *
//     * @param titleEntity
//     * @param titleExcelStyleTemp
//     * @param titleConfig
//     * @param rowIndex
//     * @param sheet
//     * @param workbook
//     * @return
//     */
//    private static int setTitle(ExcelEntity.TitleEntity titleEntity, ExcelStyleTemplate titleExcelStyleTemp, TitleConfig titleConfig, int rowIndex, XSSFSheet sheet, XSSFWorkbook workbook, int totalColNum) {
//        if (titleEntity != null) {
//            String titleName = titleEntity.getName();
//            XSSFRow titleRow = sheet.createRow(rowIndex);
//            titleRow.setHeight((short) 800);
//            //titleRow.setRowNum(10);
//           // titleRow.setHeightInPoints((float)10.0);
//            //titleRow.setRowStyle(titleExcelStyle);
//            XSSFSheet sheet1 = titleRow.getSheet();
//            //通过默认的模板样式
//            XSSFCellStyle titleExcelStyle = null;
//            if (titleConfig != null) {
//                //使用模板
//                titleExcelStyle = getCellStyleByConfig(titleConfig, titleExcelStyleTemp, workbook);
//                Integer colNums = titleEntity.getColNums();
//                //标题所占行数
//                Integer rowNums = titleEntity.getRowNums();
//                //合并单元格
//                rowIndex = mergedRegion(titleConfig, sheet1, rowIndex, colNums, rowNums, totalColNum);
//            }
//
//            XSSFCell cell = titleRow.createCell(0);
//            cell.setCellValue(titleName);
//            cell.setCellStyle(titleExcelStyle);
//
//        }
//
//        return rowIndex;
//    }
//
//
//    /**
//     * 设置表头
//     *
//     * @param headers
//     * @param headExcelStyleTemp
//     * @param headConfig
//     * @param rowIndex
//     * @param sheet
//     * @param workbook
//     * @return
//     */
//    private static int setHeader(List<ExcelEntity.HeaderEntity> headers, ExcelStyleTemplate headExcelStyleTemp, HeadConfig headConfig, int rowIndex, XSSFSheet sheet, XSSFWorkbook workbook, Map<String, Integer> fieldIndexMap, int totalColNum) {
//        if (!CollectionUtils.isEmpty(headers)) {
//            int colIndex = 0;
//            XSSFRow row = sheet.createRow(rowIndex);
//            XSSFCellStyle headerCellStyle = getCellStyleByConfig(headConfig, headExcelStyleTemp, workbook);
//            for (ExcelEntity.HeaderEntity header : headers) {
//                String name = header.getName();
//                XSSFCell cell = row.createCell(colIndex);
//                cell.setCellStyle(headerCellStyle);
//                cell.setCellValue(name);
//                //设置保存字段的列
//                String fieldName = header.getFieldName();
//                fieldIndexMap.put(fieldName, colIndex++);
//            }
//            rowIndex++;
//        }
//        return rowIndex;
//    }
//
//
//    /**
//     * 设置数据
//     *
//     * @param dataList
//     * @param dataExcelStyleTemp
//     * @param dataConfig
//     * @param rowIndex
//     * @param sheet
//     * @param workbook
//     * @param fieldIndexMap
//     * @param <T>
//     * @return
//     */
//    private static <T> int setDataList(List<T> dataList, ExcelStyleTemplate dataExcelStyleTemp, DataConfig dataConfig, int rowIndex, XSSFSheet sheet, XSSFWorkbook workbook, Map<String, Integer> fieldIndexMap, int totalColNum) {
//        if (!CollectionUtils.isEmpty(dataList)) {
//            XSSFCellStyle dataCellStyle = getCellStyleByConfig(dataConfig, dataExcelStyleTemp, workbook);
//            for (T data : dataList) {
//                Class<?> aClass = data.getClass();
//                Field[] fields = aClass.getDeclaredFields();
//                //判断是否head是否为空
//                boolean flag = CollectionUtils.isEmpty(fieldIndexMap);
//                if (flag) {
//                    fieldIndexMap = new HashMap<>();
//                }
//                if (fields != null) {
//                    XSSFRow row = sheet.createRow(rowIndex);
//
//                    for (int i = 0; i < fields.length; i++) {
//                        Field field = fields[i];
//                        field.setAccessible(true);
//                        Object fieldValue = null;
//                        try {
//                            //在没有表头的情况下，按照数据的字段类型进行排序
//                            if (flag) {
//                                fieldIndexMap.put(field.getName(), i);
//                            }
//                            fieldValue = field.get(data);
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        }
//                        Integer colIndex = fieldIndexMap.get(field.getName());
//                        if (colIndex != null) {
//                            XSSFCell cell = row.createCell(colIndex);
//                            cell.setCellValue(convertToString(fieldValue));
//                            cell.setCellStyle(dataCellStyle);
//                        }
//                    }
//                    rowIndex++;
//                }
//            }
//
//        }
//        return rowIndex;
//    }
//
//
//    /**
//     * 根据配置设置当前的的单元格风格
//     *
//     * @param excelConfig
//     * @param excelStyleTemplate
//     * @param workbook
//     * @return
//     */
//    private static XSSFCellStyle getCellStyleByConfig(AbstractExcelConfig excelConfig, ExcelStyleTemplate excelStyleTemplate, XSSFWorkbook workbook) {
//        XSSFCellStyle titleExcelStyle = null;
//        //通过默认的模板样式
//        if (excelConfig != null) {
//            //使用模板
//            if (excelConfig.isUseTemplate()) {
//                //无模板的情况下，使用默认模板
//                if (excelStyleTemplate == null) {
//                    excelStyleTemplate = new DefaultTitleTemplate();
//                }
//                //使用模板的样式风格
//                titleExcelStyle = excelStyleTemplate.build(workbook);
//                if (excelConfig.getTitleExcelStyle() != null) {
//                    //TODO 将非null的字段进行复制
//                    XSSFCellStyle customCellStyle = excelConfig.getTitleExcelStyle();
//
//
//                }
//            } else {
//                //自定义的title样式
//                if (excelConfig.getTitleExcelStyle() != null) {
//                    //titleExcelStyle = excelConfig.getTitleExcelStyle();
//
//                } else {
//
//
//                }
//
//
//            }
//
//
//            return titleExcelStyle;
//        }
//        return null;
//    }
//
//    private static int mergedRegion(AbstractExcelConfig excelConfig, XSSFSheet sheet1, Integer rowIndex, Integer targetColLen, Integer targetRowLen, Integer totalColNum) {
//        //是否自动合并
//        if (excelConfig.isAutoMergedRegion()) {
//            //标题所占列数
//            if (targetColLen == null) {
//                targetColLen = totalColNum;
//            }
//            //标题所占行数
//            if (targetRowLen == null) {
//                targetRowLen = rowIndex;
//            }
//            int firstRow = rowIndex;
//            int lastRow = targetRowLen > 0 ? (firstRow + targetRowLen - 1) : firstRow;
//
//            int lastCol = targetColLen > 0 ? targetColLen - 1 : 0;
//            sheet1.addMergedRegion(new CellRangeAddress(rowIndex, lastRow, 0, lastCol));
//            rowIndex = lastRow;
//        }
//        return ++rowIndex;
//    }
//
//
//    /**
//     * 当前Sheet的总列数
//     *
//     * @param sheetEntity
//     * @return
//     */
//    private static int getTotalColNum(ExcelEntity.SheetEntity sheetEntity) {
//        int num = 0;
//        List headers = sheetEntity.getHeaders();
//        if (!CollectionUtils.isEmpty(headers)) {
//            return headers.size();
//        }
//        if (!CollectionUtils.isEmpty(sheetEntity.getDataList())) {
//            Object o = sheetEntity.getDataList().get(0);
//            //
//            Class<?> aClass = o.getClass();
//            return aClass.getDeclaredFields().length;
//        }
//
//        return num;
//
//    }
//
//    /**
//     * 转换为字符串
//     *
//     * @param value
//     * @return
//     */
//    private static String convertToString(Object value) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        if (value instanceof Boolean) {
//            if (!(Boolean) value) {
//                return "否";
//            }
//            return "是";
//        } else if (value instanceof Date) {
//            return sdf.format((Date) value);
//        } else if (value instanceof LocalDate) {
//
//
//        } else if (value instanceof LocalDateTime) {
//
//
//        } else if (value instanceof LocalTime) {
//
//
//        } else if (value instanceof BigDecimal) {
//            BigDecimal value1 = (BigDecimal) value;
//            return value1.toString();
//
//        } else {
//            return String.valueOf(value);
//        }
//        throw new BusinessException("无匹配的数据类型");
//    }
//
//    public static void main(String[] args) {
//
//        ExcelEntity<String> excelEntity = new ExcelEntity<>();
//        excelEntity.setPath("D://");
//        excelEntity.setFileName("测试Excel");
//        ExcelEntity.SheetEntity sheetEntity = new ExcelEntity.SheetEntity();
//        sheetEntity.setSheetName("秒查数据导出1");
//        ExcelEntity.TitleEntity titleEntity = new ExcelEntity.TitleEntity();
//        sheetEntity.setTitleEntity(titleEntity);
//        titleEntity.setName("六一儿童节快乐");
//        titleEntity.setRowNums(3);
//        titleEntity.setColNums(null);
//
//
//        List<ExcelEntity.SheetEntity> list = Stream.of(sheetEntity).collect(Collectors.toList());
//        excelEntity.setExcelSheets(list);
//        TitleConfig titleConfig = new TitleConfig();
//        titleConfig.setAutoMergedRegion(true);
//
//
//        ExcelEntity.HeaderEntity headerEntity = new ExcelEntity.HeaderEntity();
//        headerEntity.setFieldName("name");
//        headerEntity.setName("用户");
//
//
//        ExcelEntity.HeaderEntity headerEntity1 = new ExcelEntity.HeaderEntity();
//        headerEntity1.setFieldName("age");
//        headerEntity1.setName("年龄");
//
//
//        ExcelEntity.HeaderEntity headerEntity2 = new ExcelEntity.HeaderEntity();
//        headerEntity2.setFieldName("age");
//        headerEntity2.setName("年龄");
//
//        ExcelEntity.HeaderEntity headerEntity3 = new ExcelEntity.HeaderEntity();
//        headerEntity3.setFieldName("name");
//        headerEntity3.setName("用户");
//
//        List<ExcelEntity.HeaderEntity> objects = new ArrayList<>();
//        objects.add(headerEntity);
//        objects.add(headerEntity1);
//        sheetEntity.setHeaders(objects);
//
//
//        List<Test> objects1 = new ArrayList<>();
//
//        Test test1 = new Test();
//        test1.setName("王大锤");
//        test1.setAge(11);
//        test1.setAddress("ssssss");
//        test1.setChildren(Boolean.FALSE);
//        objects1.add(test1);
//
//
//        Test test2 = new Test();
//        test2.setName("白客");
//        test2.setAge(18);
//        test2.setChildren(Boolean.FALSE);
//        test2.setAddress("aaaaaaaaaaaaaaaaa");
//        objects1.add(test2);
//
//
//        sheetEntity.setDataList(objects1);
//        ExcelStyleTemplateEntity excelStyleEntity = new ExcelStyleTemplateEntity();
//
////        excelStyleEntity.setTitleExcelStyle(new ExcelStyleTemplate() {
////            @Override
////            protected void buildCellStyle(XSSFCellStyle style) {
////                style.set
////            }
////
////            @Override
////            protected void buildFontStyle(XSSFFont font) {
////                font.setColor(XSSFFont.COLOR_RED);
////            }
////        });
//        ExcelConfigEntity excelConfigEntity = new ExcelConfigEntity();
//        TitleConfig titleConfig1 = new TitleConfig();
//        titleConfig1.setAutoMergedRegion(true);
//
//
//        XSSFWorkbook workbook = new XSSFWorkbook();
//
//        XSSFCellStyle cellStyle = workbook.createCellStyle();
//        XSSFFont font = workbook.createFont();
//        font.setColor(XSSFFont.COLOR_RED);
//        cellStyle.setFont(font);
//        titleConfig1.setTitleExcelStyle(cellStyle);
//        excelConfigEntity.setTitleConfig(titleConfig1);
//
//        ExcelUtils.exportExcel2007(excelEntity,excelStyleEntity,excelConfigEntity);
//
//
//    }
//
//
//}
//
//
