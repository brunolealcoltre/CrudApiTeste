package com.crud.configuracao.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {


    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.crud.api.teste.resourse"))
                .paths(regex("/dadosClientes.*")) 
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Eventos API REST",
                "API REST de cadastro de eventos",
                "1.0",
                "Terms of Service",			
                new Contact("Bruno Coltre", "https://www.linkedin.com/in/bruno-leal-coltre-50aa57113/",
                        "bruno_coltre@hotmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html",null
        );

        return apiInfo;
    }
}























