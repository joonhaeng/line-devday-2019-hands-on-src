package com.linecorp.devday.handson.demo.configuration;

import com.linecorp.armeria.server.logging.AccessLogWriter;
import com.linecorp.armeria.server.logging.LoggingService;
import com.linecorp.armeria.spring.ArmeriaServerConfigurator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArmeriaServerConfiguration {

    @Bean
    public ArmeriaServerConfigurator armeriaServerConfigurator() {

        return serverBuilder -> {

            serverBuilder.decorator(LoggingService.newDecorator());
            serverBuilder.accessLogWriter(AccessLogWriter.combined(), false);

        };

    }

}
