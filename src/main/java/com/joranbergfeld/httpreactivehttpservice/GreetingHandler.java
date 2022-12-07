package com.joranbergfeld.httpreactivehttpservice;

import io.micrometer.core.annotation.Timed;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class GreetingHandler {

    @Timed(value = "http.handler.greeting.requests", histogram = true, percentiles = {0.95, 0.99}, extraTags = {"version", "v1"})
    public Mono<ServerResponse> greeting() {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting("Hi there!")));
    }

    @Timed(value = "http.handler.long_greeting.requests", histogram = true, percentiles = {0.95, 0.99}, extraTags = {"version", "v1"})
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
