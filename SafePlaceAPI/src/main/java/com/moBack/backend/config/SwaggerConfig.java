package com.moBack.backend.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.globalOperationParameters(
						Arrays.asList(new ParameterBuilder()
								.name("JWT")
								.description("Bearer + Token")
								.modelRef(new ModelRef("string"))
								.parameterType("authorization")
								.required(true)
								.build()))
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.moBack.backend"))
				.paths(PathSelectors.ant("/api/**"))
				.build()
				.apiInfo(metaData())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()));



	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title("Safe Place REST API")
				.description("Safe Place : Make your local society better")
				.version("0.0.0")
				.contact(new Contact("Jaegoo Kim", "https://github.com/Team-MoBack/Safe-Place-API", "kimwithglasses@naver.com"))
				.build();


	}

	private ApiKey apiKey() {
		return new ApiKey("Bearer Token", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return springfox
				.documentation
				.spi.service
				.contexts
				.SecurityContext
				.builder()
				.securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

}

