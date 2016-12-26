package io.github.bobdeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAutoConfiguration
@ComponentScan("io.github.bobdeng")
@EnableAspectJAutoProxy
@SpringBootApplication
public class RestmonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestmonitorApplication.class, args);
	}
}
