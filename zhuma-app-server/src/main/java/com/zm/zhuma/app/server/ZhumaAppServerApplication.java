package com.zm.zhuma.app.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@ServletComponentScan
@SpringBootApplication
public class ZhumaAppServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhumaAppServerApplication.class, args);
	}

}
