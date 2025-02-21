package com.challenge.configuration;

import com.challenge.utils.JwtTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.challenge.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfoMetaData())
                .securitySchemes(List.of(apiKey()));
    }

    private ApiInfo apiInfoMetaData() {
        return new ApiInfoBuilder().title("API Documentation")
                .description("Movies Challenge API")
                .version("1.0.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey(JwtTokenUtil.AUTHORIZATION_HEADER, JwtTokenUtil.AUTHORIZATION_HEADER, "header");
    }

}