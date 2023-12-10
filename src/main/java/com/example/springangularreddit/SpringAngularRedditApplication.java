package com.example.springangularreddit;

import com.example.springangularreddit.config.OpenAPIConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(OpenAPIConfiguration.class)
public class SpringAngularRedditApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAngularRedditApplication.class, args);
	}

}
