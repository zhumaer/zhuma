package com.zm.zhuma.console.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ZhumaConsoleServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhumaConsoleServerApplication.class, args);
	}

}
