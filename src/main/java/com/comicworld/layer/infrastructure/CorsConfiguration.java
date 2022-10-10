package com.comicworld.layer.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfiguration {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        org.springframework.web.cors.CorsConfiguration adminCorsConfiguration = new org.springframework.web.cors.CorsConfiguration();
        adminCorsConfiguration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:3000", "http://localhost:3000"));
        adminCorsConfiguration.setAllowedMethods(Arrays.asList("POST", "GET", "HEAD", "OPTIONS", "DELETE", "PUT"));
        adminCorsConfiguration.setAllowCredentials(false);
        adminCorsConfiguration.setExposedHeaders(Arrays.asList("*"));
        adminCorsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "refresh-token"));

        org.springframework.web.cors.CorsConfiguration clientCorsConfiguration = new org.springframework.web.cors.CorsConfiguration();
        clientCorsConfiguration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:3000", "http://localhost:3000"));
        clientCorsConfiguration.setAllowedMethods(Arrays.asList("POST", "GET", "HEAD", "OPTIONS", "DELETE", "PUT"));
        clientCorsConfiguration.setAllowCredentials(false);
        clientCorsConfiguration.setExposedHeaders(Arrays.asList("*"));
        clientCorsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "refresh-token"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/admin/**", adminCorsConfiguration);
        source.registerCorsConfiguration("/**", clientCorsConfiguration);

        return source;
    }
}
