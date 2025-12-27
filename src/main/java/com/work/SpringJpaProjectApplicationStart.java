package com.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ComponentScan({"com.work"})
@EnableJpaAuditing
@EnableCaching
public class SpringJpaProjectApplicationStart {
	public static void main(String[] args) {
		SpringApplication.run(SpringJpaProjectApplicationStart.class, args);
	}

}