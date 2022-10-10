package com.comicworld.layer.domain.service.auth_token;


import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Map;

public interface AuthTokenService {

    public String generateAccessToken(Map<String, Object> payload);

    public String generateRefreshToken(Map<String, Object> payload);

    public DecodedJWT decodeToken(String token);

    public Boolean isAccessTokenValid(String token);

    public Boolean isRefreshTokenValid(String token);

}
