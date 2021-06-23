//package com.blackstar.springbootstudy.common.util.office.ppt;
//
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.sl.usermodel.TextParagraph;
//import org.apache.poi.util.IOUtils;
//import org.apache.poi.xslf.usermodel.*;
//
//import java.awt.*;
//import java.io.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Description：
// *
// * @author zhichao.ding
// * @version 1.0
// * @date 2021/6/11 17:07
// */
//@Slf4j
//public class PPTUtils {
//
//    public static String exportPpt(String path, HashMap<String, Object> dataMap, String title) {
//        //文件的名称
//        //String absoluteStrFilePath = request.getServletContext().getRealPath("/").replace("rest", "web") +;
//        //文件的路径
//        // File fileMenu = new File(path);
////        if (!CommonMethod.isHasFolder(fileMenu)) {
////            CommonMethod.createFolder(fileMenu);
////        }
//        //创建ppt对象
//        XMLSlideShow ppt = new XMLSlideShow();
//        //首页
//        XSLFSlide slide0 = ppt.createSlide();
//        fillIndexContent(slide0, dataMap);
//        //目录
//        XSLFSlide slide1 = ppt.createSlide();
//        //内容5张ppt
//        List<XSLFSlide> contentSlides = new ArrayList<>();
//        for (int i = 2; i < 7; i++) {
//            contentSlides.add(ppt.createSlide());
//        }
//        //填充目录
//        String[] titles = new String[]{"123"};
//        fillSecondContent(slide1, contentSlides, dataMap, titles);
//        //填充其他ppt
//        //图片路径
//        String[] picturePaths = new String[]{dataMap.get("aroundPicPath").toString(),
//                dataMap.get("peakPicPath").toString(), dataMap.get("intenPicPath").toString(),
//                dataMap.get("earthquakeInfluencePath").toString(), dataMap.get("stationAccPicPath").toString()};
//        //图片生成时间
//        String[] picCreateTimes = new String[]{dataMap.get("aroundPicTime").toString(),
//                dataMap.get("peakPicTime").toString(), dataMap.get("intenPicTime").toString(),
//                dataMap.get("earthquakeInfluenceTime").toString(), dataMap.get("stationAccPicTime").toString()};
//        fillOtherContent(contentSlides, titles, picturePaths, picCreateTimes, ppt);
//        StringBuffer fileUrl = new StringBuffer();
//        String fileName = title + ".pptx";
//        fileUrl.append(path);
//        fileUrl.append(fileName);
//        FileOutputStream out = null;
//        try {
//            File file = new File(fileUrl.toString());
//            out = new FileOutputStream(file);
//            ppt.write(out);
//        } catch (FileNotFoundException e) {
//            log.error("文件创建失败，请尝试给web目录赋权777");
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (out != null) {
//                    out.close();
//                    out = null;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return fileName;
//    }
//
//    /**
//     * 填充图文ppt
//     *
//     * @param contentSlides
//     * @param titles
//     * @param picturePaths
//     * @param ppt
//     */
//    private static void fillOtherContent(List<XSLFSlide> contentSlides, String[] titles, String[] picturePaths, String[] picCreateTimes, XMLSlideShow ppt) {
//        if (contentSlides != null) {
//            int len = contentSlides.size();
//            for (int i = 0; i < len; i++) {
//                createTitle(contentSlides.get(i), titles[i]);
//                createPicture(contentSlides.get(i), picturePaths[i], ppt, i);
//                createTime(contentSlides.get(i), picCreateTimes[i]);
//            }
//        }
//    }
//
//    /**
//     * 填充时间
//     *
//     * @param xslfSlide
//     * @param picCreateTime
//     */
//    private static void createTime(XSLFSlide xslfSlide, String picCreateTime) {
//        //标题文本框
//        XSLFTextBox xslfTextBox = xslfSlide.createTextBox();
//        xslfTextBox.setAnchor(new Rectangle(400, 460, 300, 80));
//        xslfTextBox.setFlipHorizontal(true);
//        //段落
//        XSLFTextParagraph paragraph0 = xslfTextBox.addNewTextParagraph();
//        paragraph0.setTextAlign(TextParagraph.TextAlign.LEFT);
//        XSLFTextRun xslfTextRun = paragraph0.addNewTextRun();
//        xslfTextRun.setFontSize(18);
//        //宋体 (正文)
//        xslfTextRun.setFontFamily("\u5b8b\u4f53 (\u6b63\u6587)");
//        String text = "123";
//        xslfTextRun.setText(String.format(text, picCreateTime));
//    }
//
//    /**
//     * 填充图片
//     *
//     * @param slide
//     * @param picturePath
//     * @param ppt
//     */
//    private static void createPicture(XSLFSlide slide, String picturePath, XMLSlideShow ppt, int flag) {
//        try {
//            byte[] pictureData = IOUtils.toByteArray(new FileInputStream(picturePath));
//            int pictureIndex = ppt.addPicture(pictureData, XSLFPictureData.PICTURE_TYPE_PNG);
//            XSLFPictureShape pictureShape = slide.createPicture(pictureIndex);
//            if (flag == 4) {
//                pictureShape.setAnchor(new Rectangle(125, 100, 467, 200));
//            } else {
//                pictureShape.setAnchor(new Rectangle(125, 100, 467, 350));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 目录填充
//     *
//     * @param xslfSlide
//     * @param contentSlides
//     * @param dataMap
//     * @param titles
//     */
//    private static void fillSecondContent(XSLFSlide xslfSlide, List<XSLFSlide> contentSlides,
//                                          HashMap<String, Object> dataMap, String[] titles) {
//        createTitle(xslfSlide, "目录");
//        //内容文本框
//        XSLFTextBox xslfTextBox = xslfSlide.createTextBox();
//        xslfTextBox.setAnchor(new Rectangle(120, 140, 500, 250));
//        xslfTextBox.setFlipHorizontal(true);
//        //段落
//        XSLFTextParagraph paragraph = xslfTextBox.addNewTextParagraph();
//        paragraph.setTextAlign(TextParagraph.TextAlign.LEFT);
//        for (int i = 0; i <= 4; i++) {
//            XSLFTextRun xslfTextRun = paragraph.addNewTextRun();
//            xslfTextRun.setUnderline(true);
//            xslfTextRun.setFontSize(36);
//            xslfTextRun.setFontFamily("\u5b8b\u4f53 (\u6b63\u6587)");
//            xslfTextRun.setText(i + 1 + "、" + titles[i]);
//            xslfTextRun.createHyperlink().setAddress(contentSlides.get(i));
//        }
//    }
//
//    /**
//     * 生成标题头
//     *
//     * @param xslfSlide
//     * @param title
//     */
//    private static void createTitle(XSLFSlide xslfSlide, String title) {
//        //标题文本框
//        XSLFTextBox xslfTextBox = xslfSlide.createTextBox();
//        xslfTextBox.setAnchor(new Rectangle(10, 25, 700, 85));
//        xslfTextBox.setFlipHorizontal(true);
//        //段落
//        XSLFTextParagraph paragraph0 = xslfTextBox.addNewTextParagraph();
//        paragraph0.setTextAlign(TextAlign.CENTER);
//        XSLFTextRun xslfTextRun = paragraph0.addNewTextRun();
//        xslfTextRun.setFontSize(44);
//        //黑体
//        xslfTextRun.setFontFamily("\u9ed1\u4f53");
//        xslfTextRun.setBold(true);
//        xslfTextRun.setText(title);
//    }
//
//    /**
//     * 填充首页内容
//     *
//     * @param xslfSlide
//     * @param dataMap
//     */
//    private static void fillIndexContent(XSLFSlide xslfSlide, HashMap<String, Object> dataMap) {
//        //文本框
//        XSLFTextBox xslfTextBox = xslfSlide.createTextBox();
//        xslfTextBox.setAnchor(new Rectangle(65, 25, 616, 480));
//        xslfTextBox.setFlipHorizontal(true);
//        //段落
//        XSLFTextParagraph paragraph = xslfTextBox.addNewTextParagraph();
//        paragraph.setTextAlign(TextParagraph.TextAlign.CENTER);
//        //标题
//        XSLFTextRun xslfTextRun = paragraph.addNewTextRun();
//        xslfTextRun.setBold(true);
//        xslfTextRun.setFontSize(44);
//        //宋体 (标题)
//        xslfTextRun.setFontFamily("\u5b8b\u4f53 (\u6807\u9898)");
//        String title = "1234";
//        title = String.format(title, dataMap.get("seismicYear").toString(), dataMap.get("seismicMonth").toString(),
//                dataMap.get("seismicDay").toString(),
//                dataMap.get("place").toString() + dataMap.get("magnitude").toString());
//        xslfTextRun.setText(title);
//
//        //制作单位
//        XSLFTextRun xslfTextRun2 = paragraph.addNewTextRun();
//        xslfTextRun2.setBold(false);
//        xslfTextRun2.setFontSize(24);
//        //宋体 (正文)
//        xslfTextRun2.setFontFamily("\u5b8b\u4f53 (\u6b63\u6587)");
//        xslfTextRun2.setText("12345\n\n\n\n\n\n\n\n");
//        //生成时间
//        XSLFTextRun xslfTextRun3 = paragraph.addNewTextRun();
//        xslfTextRun3.setBold(true);
//        xslfTextRun3.setFontSize(44);
//        //宋体 (正文)
//        xslfTextRun3.setFontFamily("\u5b8b\u4f53 (\u6807\u9898)");
//        xslfTextRun3.setText(String.format("%s年%s月%s日", dataMap.get("createYear").toString(),
//                dataMap.get("createMonth").toString(), dataMap.get("createDay").toString()));
//    }
//
//    //PPTUtils.exportPpt("D:/",)
//    public static void main(String[] args) throws IOException {
//        Map<String, String> datamap = new HashMap<String, String>();
//        datamap.put("gateWay", "Gateway");
////        datamap.put("company", "**有限公司");
////        datamap.put("plan", "123");
////        datamap.put("actr", "456");
////        datamap.put("bias", "78");
////        datamap.put("cha", "123");
////        datamap.put("jing", "456");
////        datamap.put("bao", "789");
////        datamap.put("month", "4");
////        datamap.put("pric", "789");
////        datamap.put("one", " 123456789");
////        datamap.put("two", " 876555378");
//
//        //设置模板格式和路径
//        Configuration configuration = new Configuration();
//        configuration.setDefaultEncoding("utf-8");
//        configuration.setDirectoryForTemplateLoading(new File("D:\\zhichao.ding\\桌面"));
//        //读取模板路径下的PPT的.xml模板文件
//        Template template = configuration.getTemplate("template1.xml", "utf-8");
//        //设置导出路径
//        File outfile = new File("D:\\zhichao.ding\\桌面\\result.xml");
//        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outfile)), 10240);
//
//        try {
//        //将数据替换并导出PPT
//            template.process(datamap, out);
//        } catch (TemplateException e) {
//            e.printStackTrace();
//        } finally {
//            out.close();
//        }
//
//    }
//
//}
