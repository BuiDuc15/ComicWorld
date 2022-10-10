package com.comicworld.layer.domain.service.auth_token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.comicworld.layer.domain.service.black_list_auth_code.BlackListAuthTokenService;
import com.comicworld.utils.ConvertUtils;
import com.comicworld.utils.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service("authTokenServiceImplV1")
public class AuthTokenServiceImplV1 implements AuthTokenService {

    @Autowired
    @Qualifier("tokenSigningAlgorithmV1")
    private Algorithm tokenSigningAlgorithm;

    @Autowired
    @Qualifier("blackListAuthTokenServiceImplV1")
    private BlackListAuthTokenService blackListAuthTokenService;

    @Value("${v1.token.issuer}")
    private String ISSUER;

    @Value("${v1.token.access-token.duration-in-minutes}")
    private String ACCESS_TOKEN_DURATION_IN_MINUTES;

    @Value("${v1.token.refresh-token.duration-in-minutes}")
    private String REFRESH_TOKEN_DURATION_IN_MINUTES;

    @Override
    public String generateAccessToken(Map<String, Object> payload) {

        Long expInMillis = ConvertUtils.fromMinutesToMillis(ConvertUtils.convertStringToLong(this.ACCESS_TOKEN_DURATION_IN_MINUTES));

        return JWT.create()
                .withIssuer(this.ISSUER)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expInMillis))
                .withPayload(payload)
                .sign(this.tokenSigningAlgorithm);
    }

    @Override
    public String generateRefreshToken(Map<String, Object> payload) {
        Long expInMillis = ConvertUtils.fromMinutesToMillis(ConvertUtils.convertStringToLong(this.REFRESH_TOKEN_DURATION_IN_MINUTES));

        return JWT.create()
                .withIssuer(this.ISSUER)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expInMillis))
                .withPayload(payload)
                .sign(this.tokenSigningAlgorithm);
    }

    @Override
    public DecodedJWT decodeToken(String token) {
        return JWT.decode(token);
    }

    @Override
    public Boolean isAccessTokenValid(String token) {
        try {
            JWTVerifier verifier = JWT.require(this.tokenSigningAlgorithm)
                                        .build();

            DecodedJWT decodedJWT = verifier.verify(token);

            Claim type = decodedJWT.getClaim("type");

            if(type == null || type.asInt() != Env.ACCESS_TOKEN_TYPE)
                throw new JWTVerificationException("INVALID TOKEN TYPE");

            if(this.blackListAuthTokenService.existsByCode(token))
                throw new JWTVerificationException(token + " IS IN THE TOKEN BLACK LIST.");

            return true;
        }
        catch (JWTVerificationException exception){
            return false;
        }
    }

    @Override
    public Boolean isRefreshTokenValid(String token) {
        try {
            JWTVerifier verifier = JWT.require(this.tokenSigningAlgorithm)
                                        .build();

            DecodedJWT decodedJWT = verifier.verify(token);

            Claim type = decodedJWT.getClaim("type");

            if(type == null || type.asInt() != Env.REFRESH_TOKEN_TYPE)
                throw new JWTVerificationException("INVALID TOKEN TYPE");

            if(this.blackListAuthTokenService.existsByCode(token))
                throw new JWTVerificationException(token + " IS IN THE TOKEN BLACK LIST.");

            return true;
        }
        catch (JWTVerificationException exception){
            return false;
        }
    }

}
