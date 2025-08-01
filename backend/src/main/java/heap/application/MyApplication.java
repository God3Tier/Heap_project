package heap.application;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.validation.constraints.NotNull;

@RestController
@EnableJpaRepositories(basePackages = {"heap.application.stalls", "heap.application.meal", "heap.application.user", "heap.application.review"})
@ComponentScan({"package heap.application.mapper;", "package heap.application.config;", "package heap.application.security.filter;", "package heap.application.security.service;", "package heap.application.security.exception;", "package heap.application.controller;", "package heap.application.service","package heap.application.security.filter;", "package heap.application.security.authentication;"})
@SpringBootApplication(scanBasePackages = "heap.application")
public class MyApplication {
    
    private static final Logger log = LoggerFactory.getLogger(MyApplication.class);
    private static final Dotenv dotenv = Dotenv.load();
    
	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
	}
	
	@Bean
	public DataSource dataSource() {
        return DataSourceBuilder.create()
            .url(dotenv.get("DATABASE_URL") + dotenv.get("DATABASE_PASSWORD"))
            .username(dotenv.get("DATABASE_USER"))
            .password(dotenv.get("DATABASE_PASSWORD"))
            .driverClassName("org.postgresql.Driver")
            .build();
    }

    @Bean 
    public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NotNull CorsRegistry registry) {
				registry.addMapping("/**")
                        .allowedOrigins(dotenv.get("FRONTEND_URL") + dotenv.get("FRONTEND_PORT"));
			}
		};
	}
}