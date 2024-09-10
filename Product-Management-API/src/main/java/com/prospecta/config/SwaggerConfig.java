package com.prospecta.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Prospecta", email = "raushanraj901@gmail.com", url = "https://www.prospecta.com/"), description = "Open API documentation for this Application", title = "Product Management API for Prospecta", version = "0.1", license = @License(name = "Coming..."), termsOfService = "Terms of service"), security = {
		@SecurityRequirement(name = "bearer-key") }, servers = { @Server(description = "Development", url = "/"),
				@Server(description = "prod", url = "http://api.v0.myapplication.com") })
@SecurityScheme(name = "bearer-key", description = "JWT Auth", scheme = "Bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
@Configuration
public class SwaggerConfig {

}