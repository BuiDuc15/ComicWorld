package com.comicworld.layer.domain.service.auth_token_pair;

import com.comicworld.layer.domain.entity.AuthTokenPair;

import java.util.Optional;

public interface AuthTokenPairService {

    public AuthTokenPair saveOrUpdate(AuthTokenPair pair);

    public Optional<AuthTokenPair> findByRefreshToken(String refreshToken);

    public Optional<AuthTokenPair> findByAccessToken(String accessToken);

}
