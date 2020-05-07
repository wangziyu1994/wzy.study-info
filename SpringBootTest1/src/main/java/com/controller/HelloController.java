package com.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.HelloDao;
import com.model.HelloUser;

@RestController

public class HelloController implements HelloInter{
	private static Logger logger=LoggerFactory.getLogger("HelloController.class");
	@Autowired
	private  HelloDao hd;
	
	@RequestMapping(value="/sayhello")
	  
 public String sayHello(String uName) { 
		
		/*
		 * try { int i=9/0; } catch(ArithmeticException ae) { logger.error("日志记录算术异常");
		 * } int i=0; while(i<200) { logger.trace("trace日志记录开始");
		 * logger.debug("debug日志记录开始"); logger.info("info日志记录开始");
		 * logger.warn("warn日志记录开始"); logger.error("error日志记录开始"); i++; }
		 */
		List<HelloUser>  lh=hd.selectHello();
		return lh.get(0).getUserName()+"   "+lh.get(1).getUserName(); 
		}
	
	@RequestMapping(value="/sayhello1")
	  
 public String sayHello1(String uName) { 
		
		/*
		 * try { int i=9/0; } catch(ArithmeticException ae) { logger.error("日志记录算术异常");
		 * } int i=0; while(i<200) { logger.trace("trace日志记录开始");
		 * logger.debug("debug日志记录开始"); logger.info("info日志记录开始");
		 * logger.warn("warn日志记录开始"); logger.error("error日志记录开始"); i++; }
		 */
		List<HelloUser>  lh=hd.selectHello1();
		return lh.get(0).getUserName()+"   "+lh.get(1).getUserName(); 
		}
	@RequestMapping(value="/wzyaisaber")

	public String wzyaisaber( ) {

	System.out.println("===============登录成功================");

		return "登录成功!!";
	}
	 
}
