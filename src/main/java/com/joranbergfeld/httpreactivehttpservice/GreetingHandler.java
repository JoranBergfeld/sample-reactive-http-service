package com.joranbergfeld.httpreactivehttpservice;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class GreetingHandler {

    public Mono<ServerResponse> greeting() {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting("Hi there!")));
    }

    public Mono<ServerResponse> longGreeting() {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(longWaitForGreeting()));
    }

    public Greeting longWaitForGreeting() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new Greeting("Hi there, after a while...");
    }
}
