package com.blackstar.springbootstudy.common.util.office.ppt.template;

import com.blackstar.springbootstudy.common.util.office.ppt.PowerPointUtils;
import com.blackstar.springbootstudy.common.util.office.ppt.model.*;
import com.blackstar.springbootstudy.common.util.office.ppt.parse.PowerPointTemplateParser;
import com.blackstar.springbootstudy.config.exception.BusinessException;
import lombok.Data;
import org.apache.poi.sl.usermodel.StrokeStyle;
import org.apache.poi.xslf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;

/**
 * Description：PPT的模板实体
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/6/16 10:13
 */
@Data
public class PowerPointTemplate {

    /**
     * 模板的页数
     */
    private int templatePageIndex;

    /**
     * 文本类型
     */
    private List<NameAndValue> text;


    /**
     * 获取MasterMap
     *
     * @param ppt
     * @return
     */
    public  static  Map<Integer,XSLFSlideMaster> getMasterMap(XMLSlideShow ppt){
        Map<Integer,XSLFSlideMaster> map=new HashMap<>();
        List<XSLFSlideMaster> slideMasters = ppt.getSlideMasters();
        for (int i = 0; i < slideMasters.size(); i++) {
            map.put(i,slideMasters.get(i));
        }
        return map;
    }


    public static void main(String[] args) {

        List<ChartCategory> categoryDataList = new ArrayList<>();
        ChartCategory categoryData = new ChartCategory("烟草", 0.79);
        ChartCategory categoryData2 = new ChartCategory("白酒", 0.21);
        categoryDataList.add(categoryData);
        categoryDataList.add(categoryData2);

        List<ChartSeries> seriesDataList = new ArrayList<>();
        ChartSeries seriesData = new ChartSeries();
        seriesData.setSeriesName("销售额");
        seriesData.setChartCategoryList(categoryDataList);
        seriesDataList.add(seriesData);

        ChartData chartData = new ChartData();
        chartData.setChartSeriesList(seriesDataList);

        List<ChartData> chartDataList = new ArrayList<>();
        chartDataList.add(chartData);


        String templateFile = System.getProperty("user.dir") +
                File.separator + "src\\main\\resources\\template4.pptx";

        String resultFile = System.getProperty("user.dir") +
                File.separator + "src\\main\\resources\\result1.pptx";



        Map<Integer, SlideData> map = new HashMap<>();
        //3页饼图
        SlideData slideData5 = new SlideData();
        slideData5.setChartDataList(chartDataList);
        map.put(1, slideData5);


        //第二页表格数据
        List<TableData> tableTest = getTableTest();
        SlideData slideData4 = new SlideData();
        slideData4.setTableDataList(tableTest);
        map.put(2, slideData4);


        //PowerPointGenerator.generatorPowerPoint(templateFile,resultFile,map);
        PowerPointTemplateParser parser = new PowerPointTemplateParser();
        XMLSlideShow slideShow = parser.parse("template4.pptx");

        List<XSLFSlide> slides = slideShow.getSlides();
        XSLFSlide newSlide = slideShow.createSlide(slides.get(1).getSlideLayout());
        newSlide.importContent(slides.get(1))


//        Map<Integer, XSLFSlideMaster> masterMap = getMasterMap(slideShow);
//        XSLFSlide blankSlide = slideShow.createSlide();
//        XSLFSlideMaster defaultMaster = masterMap.get(0);
//        XSLFSlideLayout titleLayout = defaultMaster.getLayout(SlideLayout.TITLE);
//
//
//        // fill the placeholders
//        XSLFSlide slide1 = slideShow.createSlide(titleLayout);
//        XSLFTextShape title1 = slide1.getPlaceholder(0);
//        title1.setText("First Title");
//
//        List<XSLFSlide> slides = slideShow.getSlides();
//        XSLFSlideLayout titleBodyLayout = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
//        XSLFSlide slide2 = slideShow.createSlide(titleBodyLayout);
//        XSLFTextShape title2 = slide2.getPlaceholder(0);
//        title2.setText("Second Title");
//        XSLFTextShape body2 = slide2.getPlaceholder(1);
//        body2.clearText(); // unset any existing text
//        body2.addNewTextParagraph().addNewTextRun().setText("First paragraph");
//        body2.addNewTextParagraph().addNewTextRun().setText("Second paragraph");
//        body2.addNewTextParagraph().addNewTextRun().setText("Third paragraph");
//
//
//
//
//        if (tableTest.get(0).getTableRowDataList().size() > 5) {
//            XSLFSlide oldSlide = slides.get(1);
//            List<XSLFShape> oldShapes = oldSlide.getShapes();
//            XSLFSlide newSlide = slideShow.createSlide(oldSlide.getSlideLayout());
//           // copyStyle(oldSlide, newSlide);
//        }

       


        slides.forEach(slide -> {
            int slideNumber = slide.getSlideNumber();
//            if(slideNumber==1){
//                List<POIXMLDocumentPart> relations = slide.getRelations();
//                //当一页有多个图表的时候
//                int chartNum = 0;
//                for (POIXMLDocumentPart part : relations) {
//                    if (part instanceof XSLFChart) {
//                        if (chartData == null) {
//                            throw new BusinessException("");
//                        }
//                        if (seriesDataList == null) {
//                            throw new BusinessException("");
//                        }
//                        //构造PPT
//                        PowerPointGenerator.generatorChart((XSLFChart) part, seriesDataList);
//
//                        chartNum++;
//
//                    }
//
//
//                }
//
//            }

            if (slideNumber == 3) {
                List<XSLFShape> shapes = slide.getShapes();


                shapes.forEach(shape -> {
                    if (shape instanceof XSLFTable) {
                        System.out.println(((XSLFTable) shape).getRows().size());
                        //PowerPointUtils.generatorTable((XSLFTable) shape, tableTest.get(0).getTableRowDataList());
                    }

                });

            }

            if (slideNumber == 2) {
                List<XSLFShape> shapes = slide.getShapes();


                shapes.forEach(shape -> {
                    if (shape instanceof XSLFTable) {
                        PowerPointUtils.generatorTable((XSLFTable) shape, tableTest.get(0).getTableRowDataList());
                    }

                });

            }


        });

        PowerPointUtils.savePowerPoint(slideShow, "test.pptx");


    }


    private static List<TableData> getTableTest() {
        List<TableRowData> tableRowDataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TableRowData tableRowData1 = new TableRowData();
            List<String> strings11 = new ArrayList<>();
            strings11.add("问题" + i);
            strings11.add("答案" + i);
            strings11.add(String.valueOf(new Random().nextInt(100)));
            tableRowData1.setDataList(strings11);
            tableRowDataList.add(tableRowData1);
        }
        TableData tableData = new TableData();
        tableData.setTableRowDataList(tableRowDataList);

        List<TableData> list = new ArrayList<>();
        list.add(tableData);
        return list;
    }


    private static void copyStyle(XSLFSlide oldSlide, XSLFSlide newSlide) {
        List<XSLFShape> oldSlideShapes = oldSlide.getShapes();
        List<XSLFShape> newSlideShapes = newSlide.getShapes();
        int oldNum = oldSlideShapes.size();
        int newNum = newSlideShapes.size();

        if (oldNum >= newNum) {
            //文本
            for (int K= 0; K < oldNum; K++) {
                XSLFShape oldShape = oldSlideShapes.get(K);
                if(K<newNum){
                    XSLFShape newShape = newSlideShapes.get(K);

                    if (newShape instanceof XSLFAutoShape&& oldShape instanceof XSLFAutoShape) {
                        XSLFAutoShape newShape01 = (XSLFAutoShape) newShape;
                        StrokeStyle strokeStyle = newShape01.getStrokeStyle();
                        XSLFAutoShape oldShape01 = (XSLFAutoShape) oldShape;
//                        newShape01.setParent(oldShape01.getParent());
//                        newShape01.getTextParagraphs().clear();
//                        newShape01.getTextParagraphs().addAll(oldShape01.getTextParagraphs());
//                        if(!StringUtils.isEmpty(oldShape01.getText())){
//                            newShape01.setText(oldShape01.getText());
//                        }

                    }else{
                        throw new BusinessException("类型不一致");
                    }

                }else {
                    //单元格
                    if (oldShape instanceof XSLFTable) {
                        XSLFTable oldShape21 = (XSLFTable) oldShape;
                        XSLFTable table = newSlide.createTable(oldShape21.getNumberOfRows(), oldShape21.getNumberOfColumns());

                        Map<Integer, Map<Integer, PowerPointUtils.TemplateCellFontStyle>> cellStyle = PowerPointUtils.getCellStyle(oldShape21);
                        List<XSLFTableRow> rows = table.getRows();
                        for (int i = 1; i < rows.size(); i++) {
                            XSLFTableRow row = rows.get(i);
                            List<XSLFTableCell> cells = row.getCells();
                            Map<Integer, PowerPointUtils.TemplateCellFontStyle> cellMap = cellStyle.get(i % cellStyle.size());
                            for (int j = 0; j < cells.size(); j++) {
                                XSLFTableCell cell = cells.get(j);
                                XSLFTextParagraph p = cell.addNewTextParagraph();
                                XSLFTextRun xslfTextRun = p.addNewTextRun();
                                PowerPointUtils.TemplateCellFontStyle tempCell = cellMap.get(j);

                                //cell.setText(s);

                                if (tempCell != null) {
                                    //设置字体
                                    if (tempCell.getTextAlign() != null) {
                                        p.setTextAlign(tempCell.getTextAlign());
                                    }
                                    p.setFontAlign(tempCell.getFontAlign());
                                    p.setLeftMargin(tempCell.getLeftMargin());
                                    p.setRightMargin(tempCell.getRightMargin());
                                    xslfTextRun.setFontFamily(tempCell.getFontFamily());
                                    xslfTextRun.setText("sssss");
                                    xslfTextRun.setFontSize(tempCell.getFontSize());
                                    //在单元格的位置

                                    if (tempCell.getInsets() != null) {
                                        cell.setInsets(tempCell.getInsets());
                                    }

                                    cell.setVerticalAlignment(tempCell.getVerticalAlignment());
                                    if (tempCell.getRectangle2D() != null) {
                                        cell.setAnchor(tempCell.getRectangle2D());
                                    }
                                }

                            }

                        }


                    }


                }

            }


        } else {


        }

    }


}
