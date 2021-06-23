package com.blackstar.springbootstudy.common.util.office;

import java.util.Collection;

/**
 * Description：解析器
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/6/16 10:11
 */
public interface Parser<T> {

    /**
     * 解析模板
     *
     * @param templatePath
     * @return
     */
     <T> T parse(String templatePath);

}
