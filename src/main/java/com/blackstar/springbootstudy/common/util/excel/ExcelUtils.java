package com.blackstar.springbootstudy.common.util.excel;

import com.blackstar.springbootstudy.common.util.excel.entity.ExcelEntity;
import com.blackstar.springbootstudy.config.exception.BusinessException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.GenericTypeResolver;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

/**
 * Description：Excel工具类
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/5/31 15:46
 */
public class ExcelUtils<T>{

    public static <T> void exportExcel2007(ExcelEntity<T> excelEntity) {
        Assert.notNull(excelEntity,"Excel的实体类不能为空");
        List<ExcelEntity.SheetEntity> excelSheets = excelEntity.getExcelSheets();

        Assert.notEmpty(excelSheets,"Sheet列表不能为空");
        for (ExcelEntity.SheetEntity excelSheet : excelSheets) {
            // 声明一个工作薄
            XSSFWorkbook workbook = new XSSFWorkbook();
            // 生成一个表格
            XSSFSheet sheet = workbook.createSheet(excelSheet.getSheetName());
            if(CollectionUtils.isEmpty(excelSheet.getDataList())) {
                try {
                    workbook.write(out);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        workbook.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return;
            }

            //进行内容读取

            //获取标题




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
    public Type getType(Class clazz) {
        Type type = clazz.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return parameterizedType.getActualTypeArguments()[0];
        }
        throw new BusinessException ("");
    }

//    private JavaType getJavaType(Type type, @Nullable Class<?> contextClass) {
//        //ObjectMapper objectMapper = new ObjectMapper();
//        TypeFactory typeFactory = objectMapper.getTypeFactory();
//        return typeFactory.constructType(GenericTypeResolver.resolveType(type, contextClass));
//    }



    private void  parse(){
        ObjectMapper objectMapper = new ObjectMapper();
        Type type = getType(getClass());
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        JavaType javaType = typeFactory.constructType(GenericTypeResolver.resolveType(type, getClass()));

    }

    public static void main(String[] args) {

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File("D://工作目标.xls"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }



}
