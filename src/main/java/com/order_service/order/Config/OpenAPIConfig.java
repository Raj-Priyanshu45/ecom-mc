package com.order_service.order.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {
    
    @Bean
    public OpenAPI productServiceDocsAPI(){
        return new OpenAPI()
                .info(new Info().title("Order Service API")
                    .description("this is the REST API for order servie")
                    .version("v0.0.1")
                    .license(new License().name("Satan Verified")))
                .externalDocs(new ExternalDocumentation()
                    .description("you can refer for further more")
                    .url("https://google.com"));
    }
}
