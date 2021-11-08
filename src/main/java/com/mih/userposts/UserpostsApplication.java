package com.mih.userposts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.mih.userposts.api.UserPostEndpoint.USERS_AND_POSTS_ENDPOINT_URI;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.HEAD;

/**
 * Main entrypoint to the Users and Posts microservice.
 *
 * This will start a Spring Boot app running on Tomcat on localhost:8080.
 */
@SpringBootApplication
public class UserpostsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserpostsApplication.class, args);
	}

	/**
	 * Cross-origin/CORS configuration.
	 * This allows clients (eg, Angular) who have been served from a front-end web server (eg, NodeJS) running
	 * from a different port to connect to these APIs, running on the Spring Boot Tomcat server on port 8080.
	 * See:
	 * https://spring.io/guides/gs/rest-service-cors/#global-cors-configuration
	 *
	 * Clients will issue 'preflight' queries via HTTP OPTION to check CORS constraints.
	 *
	 * @return WebMvcConfigurer
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api"+ USERS_AND_POSTS_ENDPOINT_URI)
						.allowedOrigins("http://localhost:8080", "http://localhost:4200")
						.allowedMethods(GET.name(), HEAD.name() )
						.allowCredentials(true);
			}
		};
	}
}
