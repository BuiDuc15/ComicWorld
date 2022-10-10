package com.comicworld.layer.infrastructure;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenSigningAlgorithmConfiguration {

    @Value("${v1.token.secret-key}")
    private String TOKEN_SECRET_KEY;

    @Bean
    public Algorithm tokenSigningAlgorithmV1() {
        return Algorithm.HMAC256(this.TOKEN_SECRET_KEY);
    }


}
