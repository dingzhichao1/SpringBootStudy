package com.blackstar.springbootstudy.service;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * Description：
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/5/28 16:27
 */
@Mapper
public interface SysUserMapper {

    @Update("UPDATE sys_user SET name ='老孔' WHERE  id=142")
    void updateSysUser();

}
