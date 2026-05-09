package com.stm.smart_task_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableCaching
@EnableAsync
@SpringBootApplication
public class SmartTaskManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartTaskManagementApplication.class, args);
	}

}
