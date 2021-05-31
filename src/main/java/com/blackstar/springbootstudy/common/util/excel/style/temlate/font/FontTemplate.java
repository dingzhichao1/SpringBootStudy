package com.blackstar.springbootstudy.common.util.excel.style.temlate.font;

import org.apache.poi.xssf.usermodel.XSSFFont;

/**
 * Description：
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/5/31 17:56
 */
public enum FontTemplate {

    FONT_TEMPLATE_1("FONT_TEMPLATE_1",FontTemplate1.class);

    private String templateName;

    private Class<? extends XSSFFont> font;

    public String getTemplateName() {
        return templateName;
    }

    public Class<? extends XSSFFont> getFont() {
        return font;
    }

    FontTemplate(String templateName, Class<? extends XSSFFont> font) {
        this.templateName = templateName;
        this.font=font;
    }
}
