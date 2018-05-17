package com.shq.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.shq.pojo.Tb_user;
import com.shq.service.IUserService;


/** 
 * @author  作者：Shengke
 * @date 创建时间：2015年12月10日 上午9:40:12 
 * @version 1.0 
 */

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class Test {

private static Logger logger = Logger.getLogger(Test.class);
	
	@Resource
	private IUserService userService;

	
    @org.junit.Test
    public void test() {
        Tb_user user = userService.getUserById(1);
        logger.info(JSON.toJSONString(user));
    }
}
