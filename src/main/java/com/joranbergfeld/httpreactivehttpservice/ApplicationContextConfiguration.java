package com.joranbergfeld.httpreactivehttpservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class ApplicationContextConfiguration {

    @Bean
    GreetingHandler greetingHandler() {
        return new GreetingHandler();
    }

    @Bean
    public RouterFunction<ServerResponse> greeting(GreetingHandler greetingHandler) {
        return RouterFunctions.route(GET("/hello").and(accept(MediaType.APPLICATION_JSON)), request -> greetingHandler.greeting());
    }

    @Bean
    public RouterFunction<ServerResponse> longGreeting(GreetingHandler greetingHandler) {
        return RouterFunctions.route(GET("/wait-for-hello").and(accept(MediaType.APPLICATION_JSON)), request -> greetingHandler.longGreeting());
    }
}
