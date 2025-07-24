package com.example.meta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class MetaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetaApplication.class, args);
	}

}
