package com.linecorp.devday.handson.demo.configuration;

import com.linecorp.armeria.client.ClientFactory;
import com.linecorp.armeria.client.logging.LoggingClient;
import com.linecorp.armeria.spring.web.reactive.ArmeriaClientConfigurator;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ArmeriaClientConfiguration {

    @Bean
    public ClientFactory clientFactory() {

        return ClientFactory.builder().sslContextCustomizer(
                b -> b.trustManager(InsecureTrustManagerFactory.INSTANCE)).build();

    }

    @Bean
    public ArmeriaClientConfigurator armeriaClientConfigurator(ClientFactory clientFactory) {

        return clientBuilder -> {

            clientBuilder.decorator(LoggingClient.newDecorator());
            clientBuilder.factory(clientFactory);

        };

    }

    @Bean
    public WebClient webClient(WebClient.Builder armeriaWebClientBuilder) {

        return armeriaWebClientBuilder.build();

    }

}
