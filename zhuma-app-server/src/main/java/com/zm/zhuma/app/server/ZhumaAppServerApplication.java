package com.zm.zhuma.app.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ZhumaAppServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhumaAppServerApplication.class, args);
	}

}
