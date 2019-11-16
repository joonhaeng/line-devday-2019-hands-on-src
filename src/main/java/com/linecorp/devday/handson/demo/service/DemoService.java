package com.linecorp.devday.handson.demo.service;

import com.linecorp.devday.handson.demo.model.DemoModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DemoService {

    private final WebClient webClient;

    public Mono<DemoModel> getRandomDemoModel() {

        Random random = new Random();

        return webClient.get()
                .uri((UriComponentsBuilder
                        .fromHttpUrl("https://postman-echo.com/get?randomNumber={randomNumber}&uuid={uuid}")
                        .build(random.nextInt(10), UUID.randomUUID())))
                .retrieve()
                .bodyToMono(DemoModel.class);

    }

    public Mono<String> getHello() {

        log.debug("Welcome Hello World");

        return Mono.just("Hello World");

    }

}
