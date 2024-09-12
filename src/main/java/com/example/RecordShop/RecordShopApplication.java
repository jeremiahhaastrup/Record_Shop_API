package com.example.RecordShop;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class RecordShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordShopApplication.class, args);
	}

		@Bean
		public OpenAPI todoApiInfo() {
			return new OpenAPI()
					.info(new Info().title("Welcome to the Record Shop")
							.description("Need an Album or Artist? This is the API for you! \uD83D\uDE3A")
							.version("v1.1")
							.license(new License().name("Apache 2.0").url("http://springdoc.org")));
		}

}
