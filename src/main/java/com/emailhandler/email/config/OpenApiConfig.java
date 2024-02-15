package com.emailhandler.email.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
        contact = @Contact(
                name="vishwajeet",
                email="vishwajeetak@gmail.com"
        ),
        description = "OpenApi documentation for Email Handling",
        title = "Email Handling",
        version = "1.0",
        termsOfService = "Terms of service"
),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                )
        })
public class OpenApiConfig {
}

