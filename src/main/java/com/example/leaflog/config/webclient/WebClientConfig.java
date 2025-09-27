package com.example.leaflog.config.webclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    private static final String GITHUB_URL = "https://api.github.com";

    @Bean
    public WebClient githubWebClient(WebClient.Builder builder){
        return builder
                .baseUrl(GITHUB_URL)
                .build();
    }
}
