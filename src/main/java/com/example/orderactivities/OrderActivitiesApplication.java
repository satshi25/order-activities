package com.example.orderactivities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class OrderActivitiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderActivitiesApplication.class, args);
	}

}
