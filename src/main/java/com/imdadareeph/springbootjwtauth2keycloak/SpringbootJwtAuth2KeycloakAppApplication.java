package com.imdadareeph.springbootjwtauth2keycloak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

/**
 * @author imdadareeph
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,
		UserDetailsServiceAutoConfiguration.class})
public class SpringbootJwtAuth2KeycloakAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJwtAuth2KeycloakAppApplication.class, args);
	}

}
