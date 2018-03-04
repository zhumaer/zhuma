package com.zhuma.demo;

import com.zm.zhuma.user.client.LoginCredentialClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.zm.zhuma.user.**.client"})
@ServletComponentScan
@EnableEurekaClient
public class ZhumaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhumaDemoApplication.class, args);
	}

}
