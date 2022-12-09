package com.datauser.goalmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GoalManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoalManagementApplication.class, args);
	}

//	@Bean
//	public WebMvcConfigurer corsMappingConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("http://localhost:8081/employee/goals")
//						.allowedOrigins("*")
//						.allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS", "HEAD")
//						.maxAge(3600)
//						.allowedHeaders("Requestor-Type")
//						.exposedHeaders("X-Get-Header");
//			}
//		};
//	}
}
