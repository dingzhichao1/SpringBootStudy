package com.blackstar.springbootstudy.service;

import com.blackstar.springbootstudy.config.ApplicationContextHelper;
import com.blackstar.springbootstudy.config.exception.BusinessException;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Description：
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2021/5/28 15:53
 */

@Service
public class TestService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TestService testService;

    @Resource
    private SysUserMapper sysUserMapper;


    /**
     * 不支持事务
     */
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NOT_SUPPORTED)
    public void test1(){
        //jdbcTemplate.update("UPDATE sys_user SET name ='老孔' WHERE  id=142");
        sysUserMapper.updateSysUser();
    }


    /**
     * test1会回滚
     */
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void test2(){
        TestService service = this;
        service.test1();
        throw new BusinessException("测试失败");
    }

    /**
     *  test1的事务生效不回滚
     */
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void test3(){
        TestService service = (TestService)AopContext.currentProxy();
        service.test1();
        final int i = 5 / 0;
    }


    /**
     * test1的事务生效不回滚
     */
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void test4(){
        TestService service = ApplicationContextHelper.getBean(TestService.class);
        service.test1();
        throw new BusinessException("测试失败");

    }

    /**
     *test1上的事务不生效会回滚
     */
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void test5(){
        this.test1();
        throw new BusinessException("测试失败");

    }

    /**
     * test1的事务生效不回滚
     */
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void test6(){
        testService.test1();
        throw new BusinessException("测试失败");

    }

}
