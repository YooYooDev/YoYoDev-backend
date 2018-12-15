package com.yooyoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {"com.yooyoo"})
@EnableJpaAuditing
public class YoyoApplication {

	public static void main(String[] args) {
		SpringApplication.run(YoyoApplication.class, args);
	}
}
