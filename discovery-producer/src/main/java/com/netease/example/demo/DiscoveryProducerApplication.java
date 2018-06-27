package com.netease.example.demo;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.netease.example.demo")
public class DiscoveryProducerApplication {
	public static ApplicationContext applicationContext;
	public static void main(String[] args) {
		applicationContext = SpringApplication.run(DiscoveryProducerApplication.class, args);
		DataSource dataSource= applicationContext.getBean(DataSource.class);
		System.out.println("datasource is :" + dataSource);
		//检查数据库是否是hikar数据库连接池
        if (!(dataSource instanceof HikariDataSource)) {
            System.err.println(" Wrong datasource type :"
                    + dataSource.getClass().getCanonicalName());
            System.exit(-1);
        }
	}
}
