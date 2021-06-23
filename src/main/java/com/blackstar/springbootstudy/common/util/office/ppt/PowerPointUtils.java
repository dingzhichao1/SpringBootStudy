package com.blackstar.springbootstudy.common.util.office.ppt;

import com.blackstar.springbootstudy.common.util.office.ppt.model.TableRowData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.sl.usermodel.*;
import org.apache.poi.xslf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTable;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableRow;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlide;

import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description：
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/6/16 14:04
 */
@Slf4j
@SuppressWarnings("Duplicates")
public class PowerPointUtils {

    /**
     * 保存ppt到指定路径
     *
     * @param ppt
     * @param outputFilePath
     */
    public static void savePowerPoint(XMLSlideShow ppt, String outputFilePath) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(outputFilePath);
            ppt.write(fileOutputStream);
        } catch (IOException e) {
            log.info("save powerpoint to destPath fail ", e);
        } finally {
            try {
                fileOutputStream.close();
                ppt.close();
            } catch (IOException e) {
                log.info("stream close fail  ", e);
            }
        }

    }

    /**
     * 构造文本
     *
     * @param textShape
     * @param textMap
     */
    public static void generatorTextBox(XSLFTextShape textShape, Map<String, String> textMap) {
        List<XSLFTextParagraph> textParagraphList = textShape.getTextParagraphs();
        for (XSLFTextParagraph textParagraph : textParagraphList) {
            //正则表达式匹配${}标识符的text
            String text = textParagraph.getText();
            String regex = "\\$\\{.*?\\}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            List<String> keys = new ArrayList<>();
            while (matcher.find()) {
                keys.add(matcher.group());
            }
            for (String key : keys) {
                String textKey = key.substring(2, key.length() - 1);
                text = text.replace(key, textMap.get(textKey) == null ? " " : textMap.get(textKey));
            }

            List<XSLFTextRun> textRuns = textParagraph.getTextRuns();
            for (XSLFTextRun textRun : textRuns) {
                textRun.setText("");
            }
            if (textRuns.size() > 0) {
                textRuns.get(0).setText(text);
            }
        }
    }


    /**
     * 构造表格
     *
     * @param table
     * @param tableDataList
     */
    public static void generatorTable(XSLFTable table, List<TableRowData> tableDataList) {
        int pageSize=10;
        int pageNum=1;
        if(tableDataList.size()>pageNum){
            pageNum=tableDataList.size()/pageSize;
        }

        //获取模板的样式
        List<XSLFTableRow> rows = table.getRows();

        //获取单元格的样式
        Map<Integer, Map<Integer, TemplateCellFontStyle>> cellStyle = getCellStyle(table);

        int rowSize = rows.size() - 1;

        double rowHight = 0;
        for (int i = 0; i < tableDataList.size(); i++) {
            //为了保证样式的不变判断数据量比模板的row多的时候，在原来表格基础上新增row
            Map<Integer, TemplateCellFontStyle> cellMap = cellStyle.get(i % cellStyle.size());
            if (i < rowSize) {
                XSLFTableRow row = rows.get(i + 1);
                rowHight= row.getHeight();
                List<XSLFTableCell> cells = row.getCells();
                for (int j = 0; j < tableDataList.get(i).getDataList().size(); j++) {
                    TemplateCellFontStyle tempCell = cellMap.get(j);
                    String s = tableDataList.get(i).getDataList().get(j);
                    XSLFTableCell cell = cells.get(j);
                    if(tempCell.getInsets()!=null){
                        cell.setInsets(tempCell.getInsets());
                    }

                    cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
                    System.out.println(cell.getText());
                    XSLFTextParagraph xslfTextRuns = cell.getTextParagraphs().get(0);
                    List<XSLFTextRun> textRuns = xslfTextRuns.getTextRuns();
                    for (int j1 = 0; j1 < textRuns.size(); j1++) {
                        XSLFTextRun textRun = textRuns.get(j1);
                        if(j1==0){
                            textRun.setFontFamily(tempCell.getFontFamily());
                            textRun.setFontSize(tempCell.getFontSize());
                            textRun.setText(s);
                        }else {
                            textRun.setFontFamily(tempCell.getFontFamily());
                            textRun.setFontSize(tempCell.getFontSize());
                            textRun.setText("");

                        }

                    }
                }



            } else {
                table.addRow();
                XSLFTableRow row = rows.get(i + 1);
                row.setHeight(rowHight);
                for (int j = 0; j < tableDataList.get(i).getDataList().size(); j++) {
                    String s = tableDataList.get(i).getDataList().get(j);
                    XSLFTableCell cell = row.addCell();

                    XSLFTextParagraph p = cell.addNewTextParagraph();
                    CTTextParagraph xmlObject1 = p.getXmlObject();

                    XSLFTextRun xslfTextRun = p.addNewTextRun();
                    TemplateCellFontStyle tempCell = cellMap.get(j);
                    if(j==0){
                        

                    }
                    //copyCellStyle(xslfTextParagraphs,cell,s);

                    //cell.setText(s);
                    //设置字体
                    p.setTextAlign(tempCell.getTextAlign());
                    p.setFontAlign(tempCell.getFontAlign());
                    p.setLeftMargin(tempCell.getLeftMargin());
                    p.setRightMargin(tempCell.getRightMargin());
                    xslfTextRun.setFontFamily(tempCell.getFontFamily());
                    xslfTextRun.setText(s);
                    xslfTextRun.setFontSize(tempCell.getFontSize());
                    


                    //在单元格的位置
                    cell.setInsets(tempCell.getInsets());
                    cell.setVerticalAlignment(tempCell.getVerticalAlignment());
                    cell.setAnchor(tempCell.getRectangle2D());

                }
            }
        }


        CTTable ctTable = table.getCTTable();
        int size = ctTable.getTrList().size();

        for (int i = 0; i < size-tableDataList.size()-1; i++) {
            ctTable.removeTr(tableDataList.size()+1);
        }

        //清空我的配置文件（根据自己解析数据的业务清空配置文件）
        //table.getCell(0, 0).setText("");
    }



//    private static void copyCellStyle(TemplateCellFontStyle te,XSLFTableCell target){
//        //水平样式
//        target.setVerticalAlignment(VerticalAlignment.MIDDLE);
//        //填充颜色
//        target.setFillColor(original.getFillColor());
//        //设置水平居中
//        target.setHorizontalCentered(false);
//
//    }

    @Data
    public static class TemplateCellFontStyle{
        /**
         * 字体
         */
        private String fontFamily;

        /**
         * 字体大小
         */
        private Double fontSize;

        /**
         * 字体颜色
         */
        private PaintStyle fontColor;

        /**
         * 文字在Paragraph的位置
         */
        private TextParagraph.TextAlign textAlign;


        private TextParagraph.FontAlign fontAlign;

        private double leftMargin;

        private double rightMargin;


        /**
         * Paragraph在Cell间隔
         */
        private Insets2D insets ;


        private  VerticalAlignment verticalAlignment;


        private Rectangle2D rectangle2D;


        private double textHeight;


    }


    /**
     * 获取单元格的样式
     */
    public static Map<Integer, Map<Integer, TemplateCellFontStyle>> getCellStyle(XSLFTable table){
        Map<Integer, Map<Integer, TemplateCellFontStyle>> tempRowMap = new HashMap<>();
        Map<Integer, Map<Integer, XSLFTableCell>> integerMapHashMap = new HashMap<>();
        //获取所有的列
        List<XSLFTableRow> rows = table.getRows();
        //从第二行开始读取样式
        int startIndex=1;

        int rowIndex=0;
        for (int i = startIndex; i < rows.size(); i++) {
            XSLFTableRow row = rows.get(i);
            Map<Integer, TemplateCellFontStyle> cellMap = new HashMap<>();
            Map<Integer, XSLFTableCell> cellMap1 = new HashMap<>();
            List<XSLFTableCell> cells = row.getCells();
            int cellIndex=0;
            for (XSLFTableCell cell : cells) {
                XSLFTextParagraph textParagraph = cell.getTextParagraphs().get(0);
                TextParagraph.TextAlign textAlign = textParagraph.getTextAlign();



                XSLFTextRun textRun = textParagraph.getTextRuns().get(0);
                TemplateCellFontStyle templateCellFontStyle = new TemplateCellFontStyle();
                templateCellFontStyle.setFontFamily(textRun.getFontFamily());
                templateCellFontStyle.setFontColor(textRun.getFontColor());
                templateCellFontStyle.setFontSize(textRun.getFontSize());
                templateCellFontStyle.setTextAlign(textAlign);
                //设置边距
                if(textParagraph.getRightMargin()!=null){
                    templateCellFontStyle.setRightMargin(textParagraph.getRightMargin());
                }
                if(textParagraph.getLeftMargin()!=null){
                    templateCellFontStyle.setLeftMargin(textParagraph.getLeftMargin());
                }



                VerticalAlignment verticalAlignment = cell.getVerticalAlignment();
//                if(cell.getAnchor()!=null){
//                    if(cell.getAnchor().getBounds2D()!=null){
//                        templateCellFontStyle.setRectangle2D(cell.getAnchor().getBounds2D());
//                    }
//
//                }

                templateCellFontStyle.setVerticalAlignment(verticalAlignment);

//                double textHeight = cell.getTextHeight();
//                templateCellFontStyle.setTextHeight(textHeight);


                cellMap.put(cellIndex,templateCellFontStyle);


                cellMap1.put(cellIndex,cell);
                cellIndex++;
            }
            tempRowMap.put(rowIndex,cellMap);
            integerMapHashMap.put(rowIndex,cellMap1);
            rowIndex++;
        }




        return tempRowMap;
    }


    /**
     *  复制Slide，包括样式和内容
     *
     * @param slideShow
     * @param sourceSlide
     * @return
     */
    public static Slide copyXSLFSlide(XMLSlideShow slideShow,XSLFSlide sourceSlide){
        XSLFSlide targetSlide = slideShow.createSlide(sourceSlide.getSlideLayout());
        targetSlide.importContent(sourceSlide);
        return targetSlide;
    }





}
