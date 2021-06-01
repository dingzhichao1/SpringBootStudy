package com.blackstar.springbootstudy.common.util.excel;

import com.blackstar.springbootstudy.common.util.excel.entity.ExcelEntity;
import com.blackstar.springbootstudy.common.util.excel.style.temlate.CellStyleBuilder;
import com.blackstar.springbootstudy.common.util.excel.style.temlate.Test;
import com.blackstar.springbootstudy.common.util.excel.style.temlate.TitleConfig;
import com.blackstar.springbootstudy.common.util.excel.style.temlate.cell.AbstractCellStyleTemplate;
import com.blackstar.springbootstudy.common.util.excel.style.temlate.cell.DefaultHeaderCellStyleTemplate;
import com.blackstar.springbootstudy.config.exception.BusinessException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.core.GenericTypeResolver;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description：Excel工具类
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/5/31 15:46
 */
public class ExcelUtils<T> {

    private final static int DEFAULT_TITLE_ROW = 0;


    public static <T> void exportExcel2007(ExcelEntity<T> excelEntity, OutputStream out, TitleConfig titleConfig, AbstractCellStyleTemplate headerCellTemplate) {
        Assert.notNull(excelEntity, "Excel的实体类不能为空");
        List<ExcelEntity.SheetEntity> excelSheets = excelEntity.getExcelSheets();

        Assert.notEmpty(excelSheets, "Sheet列表不能为空");
        for (ExcelEntity.SheetEntity excelSheet : excelSheets) {
            // 声明一个工作薄
            XSSFWorkbook workbook = new XSSFWorkbook();
            // 生成一个表格
            XSSFSheet sheet = workbook.createSheet(excelSheet.getSheetName());
            //获取标题
            int rowIndex = 0;
            ExcelEntity.TitleEntity titleEntity = excelSheet.getTitleEntity();

            if (titleEntity!=null) {
                String titleName = titleEntity.getName();
                Assert.notNull(titleName,"标题不能为空");
                XSSFRow titleRow = sheet.createRow(rowIndex);
                //通过默认的模板样式
                XSSFCellStyle headerCellStyle = new CellStyleBuilder(workbook).build(headerCellTemplate);
                XSSFCell cell = titleRow.createCell(0);
                cell.setCellValue(titleName);
                cell.setCellStyle(headerCellStyle);
                XSSFSheet sheet1 = titleRow.getSheet();
                if (titleConfig != null) {
                    boolean autoMergedRegion = titleConfig.isAutoMergedRegion();
                    if (autoMergedRegion) {
                        //标题所占列数
                        Integer titleColNum = titleEntity.getColNums();
                        if (titleColNum == null) {
                            //TODO
                            titleColNum = excelSheet.getHeaders().size();
                        }
                        //标题所占行数
                        Integer rowNums = titleEntity.getRowNums();
                        if (rowNums == null) {
                            rowNums = rowIndex;
                        }
                        int firstRow=rowIndex;
                        int lastRow=firstRow+rowNums-1;
                        sheet1.addMergedRegion(new CellRangeAddress(rowIndex, lastRow, 0, titleColNum));
                        rowIndex=++lastRow;
                    }else {
                        //不合并单元格


                    }

                }

                //按照不合并单元格

            }

            Map<String, Integer> map = new HashMap<>();

            //tab名
            List<ExcelEntity.HeaderEntity> headers = excelSheet.getHeaders();
            if(!CollectionUtils.isEmpty(headers)){
                int colIndex=0;
                XSSFRow row = sheet.createRow(rowIndex);
                for (ExcelEntity.HeaderEntity header : headers) {
                    String name = header.getName();
                    XSSFCell cell = row.createCell(colIndex);
                    XSSFCellStyle headerCellStyle = new CellStyleBuilder(workbook).build(headerCellTemplate);
                    cell.setCellStyle(headerCellStyle);
                    cell.setCellValue(name);
                    //设置保存字段的列
                    String fieldName = header.getFieldName();
                    map.put(fieldName,colIndex++);
                }
                rowIndex++;
            }

            List<T> dataList = excelSheet.getDataList();
            if(!CollectionUtils.isEmpty(dataList)){
                for (T data : dataList) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Type type = getType(data.getClass());
                    TypeFactory typeFactory = objectMapper.getTypeFactory();
                    JavaType javaType = typeFactory.constructType(GenericTypeResolver.resolveType(type, data.getClass()));
                    System.out.println(javaType);
                }

            }


            try {
                workbook.write(out);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }


//    private void parseDeclai(T cellData){
//        // 实际表格标题行下标
//        int realHeaderIndex = 0;
//        for (int i = 0; i < declaredFields.length; i++) {
//            if(declaredFields[i].isAnnotationPresent(ExcelFieldControl.class)) {
//                ExcelFieldControl annotation = declaredFields[i].getAnnotation(ExcelFieldControl.class);
//                if(!annotation.ignore()) {
//                    cellHeader = row.createCell(realHeaderIndex++);
//                    cellHeader.setCellStyle(style);
//                    cellHeader.setCellValue(new XSSFRichTextString(annotation.header()));
//                }
//            }
//        }
//
//    }


//    private <T> T createCell (String cellData){
//        ObjectMapper objectMapper = new ObjectMapper();
//        Type type = getType(getClass());
//        JavaType javaType = getJavaType(type, getClass());
//        return objectMapper.readValue(cellData, javaType);
//    }

    /**
     * 通过类获取该类的泛型类
     */
    public static Type getType(Class clazz) {
        Type type = clazz.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return parameterizedType.getActualTypeArguments()[0];
        }
        throw new BusinessException("");
    }

    private JavaType getJavaType(Type type, @Nullable Class<?> contextClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        return typeFactory.constructType(GenericTypeResolver.resolveType(type, contextClass));
    }


    private void parse() {
        ObjectMapper objectMapper = new ObjectMapper();
        Type type = getType(getClass());
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        JavaType javaType = typeFactory.constructType(GenericTypeResolver.resolveType(type, getClass()));

    }

    public static void main(String[] args) {

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File("D://工作目标.xls"));
            ExcelEntity<String> excelEntity = new ExcelEntity<>();
            excelEntity.setFileName("D://工作目标.xls");
            ExcelEntity.SheetEntity sheetEntity = new ExcelEntity.SheetEntity();
            sheetEntity.setSheetName("秒查数据导出1");
            ExcelEntity.TitleEntity titleEntity = new ExcelEntity.TitleEntity();
            sheetEntity.setTitleEntity(titleEntity);
            titleEntity.setName("六一儿童节快乐");
            titleEntity.setRowNums(3);
            titleEntity.setColNums(10);

        
            List<ExcelEntity.SheetEntity> list = Stream.of(sheetEntity).collect(Collectors.toList());
            excelEntity.setExcelSheets(list);
            TitleConfig titleConfig = new TitleConfig();
            titleConfig.setAutoMergedRegion(true);

            ExcelEntity.HeaderEntity headerEntity = new ExcelEntity.HeaderEntity();
            headerEntity.setFieldName("user");
            headerEntity.setName("用户");


            ExcelEntity.HeaderEntity headerEntity1 = new ExcelEntity.HeaderEntity();
            headerEntity1.setFieldName("age");
            headerEntity1.setName("年龄");

            List<ExcelEntity.HeaderEntity> objects = new ArrayList<>();
            objects.add(headerEntity);
            objects.add(headerEntity1);
            sheetEntity.setHeaders(objects);


            List<Test> objects1 = new ArrayList<>();

            Test test1 = new Test();
            test1.setName("王大锤");
            test1.setAge(11);
            objects1.add(test1);


            Test test2 = new Test();
            test2.setName("王大锤");
            test2.setAge(11);
            objects1.add(test2);



            sheetEntity.setDataList(objects1);
            ExcelUtils.exportExcel2007(excelEntity, fileOutputStream, titleConfig, new DefaultHeaderCellStyleTemplate());



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
