package com.zhuma.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
@EnableFeignClients
@ServletComponentScan(basePackages = {"com.zhuma.demo", "com.zm.zhuma.commons.web"})
public class ZhumaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhumaDemoApplication.class, args);
	}

}
