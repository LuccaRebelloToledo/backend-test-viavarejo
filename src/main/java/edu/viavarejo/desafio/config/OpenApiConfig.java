package edu.viavarejo.desafio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI customOpenApi() {

		return new OpenAPI().info(new Info().title("RESTful API by Lucca Toledo com Spring Boot").version("V1")
				.description(
						"Endpoint que simula a compra de um produto e retornar uma lista de parcelas, acrescidas de juros com base na taxa SELIC de 1.15% ao mês consultando a taxa em tempo real somente quando o número de parcelas for superior a 06 (seis) parcelas.")
				.termsOfService("https://github.com/LuccaRebelloToledo/backend-test-viavarejo").license(new License()
						.name("Maven Apache").url("https://github.com/LuccaRebelloToledo/backend-test-viavarejo")));

	}

}
