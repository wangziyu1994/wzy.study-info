package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@EnableEurekaClient
@RestController
@FeignClient(value="helloController")
public class UserController {

	@Autowired
	HelloController h;
	

	@RequestMapping(value="/use")
	public void useHello(String uName) {
		System.out.println("调用前"+uName);
		String a=h.sayHello(uName);
		System.out.println("调用后"+a);
	}
}
