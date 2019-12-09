package com.example.entry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.example"})
@MapperScan(value = "com.example.entry.mapper,com.example.base.mapper")
public class EntryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntryApplication.class, args);
	}

}
