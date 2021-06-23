package com.blackstar.springbootstudy.common.util.office.ppt.parse;

import com.blackstar.springbootstudy.common.util.office.Parser;
import com.blackstar.springbootstudy.common.util.office.ppt.template.PowerPointTemplate;
import com.blackstar.springbootstudy.config.exception.BusinessException;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFBackground;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Description：PPT模板解析器
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/6/16 10:08
 */
public class PowerPointTemplateParser implements Parser<PowerPointTemplate> {

    private XMLSlideShow xmlSlideShow=null;

    @Override
    public XMLSlideShow parse(String templatePath) {
        XMLSlideShow ppt = null;
        try {
            File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX+templatePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            ppt = new XMLSlideShow(fileInputStream);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new BusinessException("获取模板资源出现错误");
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("获取模板资源出现错误:"+e);
        }
        //赋给全局变量
        this.xmlSlideShow=ppt;
        return ppt;
    }






    public static void main(String[] args) {
        XMLSlideShow slideShow=new XMLSlideShow();
        List<XSLFSlide> slides = slideShow.getSlides();
        XSLFSlide xslfSlide = slides.get(0);
        //当前页数
        int slideNumber = xslfSlide.getSlideNumber();

        //背景
        XSLFBackground background = xslfSlide.getBackground();

        List<POIXMLDocumentPart> relations = xslfSlide.getRelations();
        POIXMLDocumentPart documentPart = relations.get(0);
        PackagePart packagePart = documentPart.getPackagePart();

    }


}
