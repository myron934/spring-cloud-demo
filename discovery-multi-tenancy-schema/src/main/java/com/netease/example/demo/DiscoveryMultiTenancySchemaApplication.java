package com.netease.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.netease.example.demo")
public class DiscoveryMultiTenancySchemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryMultiTenancySchemaApplication.class, args);
	}
}
