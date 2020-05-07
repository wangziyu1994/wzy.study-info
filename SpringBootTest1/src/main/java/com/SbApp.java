
package com;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;



//@Controller
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
//@SpringBootConfiguration    //自动配置注解代替 configuration
//@ComponentScan
//@Configuration
public class SbApp {
	

public static void main(String args[]) {
	SpringApplication.run(SbApp.class,args);
}
}
