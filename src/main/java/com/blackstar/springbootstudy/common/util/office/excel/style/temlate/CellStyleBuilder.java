//package com.blackstar.springbootstudy.common.util.office.excel.style.temlate;
//
//import com.blackstar.springbootstudy.common.util.office.excel.style.temlate.cell.AbstractCellStyleTemplate;
//import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.xssf.usermodel.XSSFColor;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.util.Assert;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Description：CellStyle构建者
// *
// * @author zhichao.ding
// * @version 1.0
// * @date 2021/6/1 9:53
// */
//@SuppressWarnings("unchecked")
//public class CellStyleBuilder {
//
//    /**
//     *
//     */
//    private static Map<CellStyleEnum, Method> methodAnnotationMap = new HashMap<>();
//
//    static {
//        Method[] methods = CellStyleBuilder.class.getMethods();
//        for (int i = 0; i < methods.length; i++) {
//            if (methods[i] != null) {
//                Method method = methods[i];
//                CellStyle cellStyle = methods[i].getAnnotation(CellStyle.class);
//                if (cellStyle != null && !methodAnnotationMap.containsKey(cellStyle)) {
//                    methodAnnotationMap.put(cellStyle.value(), method);
//                }
//            }
//        }
//    }
//
//
//    private XSSFCellStyle cellStyle = null;
//
//    private XSSFWorkbook workbook=null;
//
//    public CellStyleBuilder(XSSFWorkbook workbook) {
//        Assert.notNull(workbook, "WorkBook不能为空");
//        cellStyle = workbook.createCellStyle();
//        this.workbook=workbook;
//    }
//
//    private CellStyleBuilder() {
//    }
//
//    ;
//
//    public XSSFCellStyle build() {
//        return cellStyle;
//    }
//
//
//    /**
//     * 通过模板来获取单元格样式
//     *
//     * @param cellStyleTemplate
//     * @return
//     */
//    public XSSFCellStyle build( AbstractCellStyleTemplate cellStyleTemplate) {
//        return parseXSSFCellStyleByTemplate(workbook,cellStyleTemplate.getClass());
//    }
//
//
//    public XSSFCellStyle buildBorder(short borderLeft, short borderRight, short borderTop, short borderBottom) {
//        buildBorderLeft(borderLeft);
//        buildBorderRight(borderRight);
//        buildBorderTop(borderTop);
//        buildBorderBottom(borderBottom);
//        return cellStyle;
//    }
//
//
//    @CellStyle(value = CellStyleEnum.BORDER_RIGHT)
//    public XSSFCellStyle buildBorderRight(short borderRight) {
//        cellStyle.setBorderRight(borderRight);
//        return cellStyle;
//    }
//
//
//    @CellStyle(value = CellStyleEnum.BORDER_LEFT)
//    public XSSFCellStyle buildBorderLeft(short borderLeft) {
//        cellStyle.setBorderLeft(borderLeft);
//        return cellStyle;
//    }
//
//
//    @CellStyle(value = CellStyleEnum.BORDER_TOP)
//    public XSSFCellStyle buildBorderTop(short borderTop) {
//        cellStyle.setBorderTop(borderTop);
//        return cellStyle;
//    }
//
//
//    @CellStyle(value = CellStyleEnum.BORDER_BOTTOM)
//    public XSSFCellStyle buildBorderBottom(short borderBottom) {
//        cellStyle.setBorderBottom(borderBottom);
//        return cellStyle;
//    }
//
//
//    @CellStyle(value = CellStyleEnum.FILL_PATTERN)
//    public XSSFCellStyle buildFillPattern(short fillPattern) {
//        cellStyle.setFillPattern(fillPattern);
//        return cellStyle;
//    }
//
//
//    @CellStyle(value = CellStyleEnum.COLOR)
//    public XSSFCellStyle buildColor(XSSFColor xssfColor) {
//        cellStyle.setFillForegroundColor(xssfColor);
//        return cellStyle;
//    }
//
//
//    @CellStyle(value = CellStyleEnum.ALIGNMENT)
//    public XSSFCellStyle buildAlignment(short alignment) {
//        cellStyle.setAlignment(alignment);
//        return cellStyle;
//    }
//
//
//    @CellStyle(value = CellStyleEnum.FONT)
//    public XSSFCellStyle buildFont(XSSFFont font) {
//        cellStyle.setFont(font);
//        return cellStyle;
//    }
//
//
//    @CellStyle(value = CellStyleEnum.VERTIC_ALALIGNMENT)
//    public XSSFCellStyle buildVerticAlalignment(short verticAlalignment){
//        cellStyle.setVerticalAlignment(verticAlalignment);
//        return cellStyle;
//
//    }
//
//
//
//    public static void main(String[] args) {
//        CellStyleBuilder cellStyleBuilder = new CellStyleBuilder();
//        XSSFWorkbook xssfSheets = new XSSFWorkbook();
//
//
//    }
//
//    /**
//     * 解析
//     * @param xssfWorkbook
//     * @param cellStyleTemplateClass
//     * @return
//     */
//    private XSSFCellStyle parseXSSFCellStyleByTemplate(XSSFWorkbook xssfWorkbook, Class<? extends AbstractCellStyleTemplate> cellStyleTemplateClass) {
//
//        try {
//            AbstractCellStyleTemplate abstractCellStyleTemplate = cellStyleTemplateClass.newInstance();
//            Constructor<CellStyleBuilder> constructor = CellStyleBuilder.class.getConstructor(XSSFWorkbook.class);
//            CellStyleBuilder cellStyleBuilder = constructor.newInstance(xssfWorkbook);
//
//
//            Field[] declaredFields = cellStyleTemplateClass.getDeclaredFields();
//            for (Field declaredField : declaredFields) {
//                CellStyle cellStyle = declaredField.getAnnotation(CellStyle.class);
//                if (cellStyle != null) {
//
//                    CellStyleEnum value = cellStyle.value();
//                    Object filedValue = declaredField.get(abstractCellStyleTemplate);
//                    //对应的build属性的方法
//                    Method method = methodAnnotationMap.get(value);
//                    method.invoke(cellStyleBuilder, filedValue);
//                } else {
//                    //看父类是否有
//                    CellStyle superCellStyle = getSuperClassFieldAnnotation(declaredField.getName(), cellStyleTemplateClass, CellStyle.class);
//                    if (superCellStyle != null) {
//                        CellStyleEnum value = superCellStyle.value();
//                        Object filedValue = declaredField.get(abstractCellStyleTemplate);
//                        //对应的build属性的方法
//                        Method method = methodAnnotationMap.get(value);
//                        if(method!=null){
//                            method.invoke(cellStyleBuilder, filedValue);
//                        }
//
//                    }
//                }
//            }
//            XSSFCellStyle xssfCellStyle = cellStyleBuilder.build();
//            return xssfCellStyle;
//
//        } catch (InstantiationException | NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//
//
//    /**
//     * 获取父类上的字段注解
//     *
//     * @param fieldName
//     * @param clazz
//     * @param annotationClass
//     * @return
//     */
//    private CellStyle getSuperClassFieldAnnotation(String fieldName, Class clazz, Class<CellStyle> annotationClass) {
//        try {
//            Class superclass = clazz.getSuperclass();
//            if (superclass != null) {
//                //获取注解
//                Field field = null;
//                field = superclass.getField(fieldName);
//
//                if (field != null) {
//                    CellStyle annotation = field.getAnnotation(annotationClass);
//                    if (annotation != null) {
//                        return annotation;
//                    } else {
//                        return getSuperClassFieldAnnotation(fieldName, superclass, annotationClass);
//
//                    }
//                } else {
//                    return null;
//                }
//
//            } else {
//                //无父级
//                return null;
//            }
//        } catch (NoSuchFieldException e) {
//            return null;
//        }
//    }
//
//}
