package com.zm.zhuma.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ZhumaUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhumaUserApplication.class, args);
	}

}
