package com.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;




public interface HelloInter {
	@RequestMapping(value="/sayhello")
	public String sayHello(String uNamess);
}
