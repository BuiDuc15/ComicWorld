package com.comicworld.layer.domain.service.client.refresh_session;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.comicworld.layer.domain.entity.AuthTokenPair;
import com.comicworld.layer.domain.entity.Authority;
import com.comicworld.layer.domain.entity.BlackListAuthToken;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.auth_token.AuthTokenService;
import com.comicworld.layer.domain.service.auth_token_pair.AuthTokenPairService;
import com.comicworld.layer.domain.service.authority.AuthorityService;
import com.comicworld.layer.domain.service.black_list_auth_code.BlackListAuthTokenService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service("clientRefreshSessionServiceImplV1")
@Transactional
public class ClientRefreshSessionServiceImplV1 implements ClientRefreshSessionService {

    @Autowired
    @Qualifier("authTokenServiceImplV1")
    private AuthTokenService authTokenService;

    @Autowired
    @Qualifier("authorityServiceImplV1")
    private AuthorityService authorityService;

    @Autowired
    @Qualifier("authTokenPairServiceImplV1")
    private AuthTokenPairService authTokenPairService;

    @Autowired
    @Qualifier("blackListAuthTokenServiceImplV1")
    private BlackListAuthTokenService blackListAuthTokenService;

    @Override
    public ResponseEntity<Object> refresh(String refreshToken) {
        if(refreshToken.equalsIgnoreCase(""))
            return new ResponseEntity<>(
                    ResponseBodyFactoryV1.badRequestResponseBody(
                            new FailedBodyContentV1(Env.BAD_REQUEST_EXCEPTION_TYPE, "Missing refresh token.")
                    ),
                    HttpStatus.BAD_REQUEST
            );

        if(!authTokenService.isRefreshTokenValid(refreshToken))
            return new ResponseEntity<>(
                    ResponseBodyFactoryV1.unauthorizedResponseBody(
                            new FailedBodyContentV1(Env.UNAUTHORIZED_EXCEPTION_TYPE, "Invalid refresh token.")
                    ),
                    HttpStatus.UNAUTHORIZED
            );

        DecodedJWT decodedJWT = this.authTokenService.decodeToken(refreshToken);

        Long accountId = Long.parseLong(decodedJWT.getSubject());

        Set<Authority> authorities = this.authorityService.findByClientAccountId(accountId);

        if(authorities.isEmpty())
            return new ResponseEntity<>(
                    ResponseBodyFactoryV1.unauthorizedResponseBody(
                            new FailedBodyContentV1(Env.UNAUTHORIZED_EXCEPTION_TYPE, "Invalid refresh token.")
                    ),
                    HttpStatus.UNAUTHORIZED
            );

        List<String> roles = authorities.stream()
                .map(authority -> authority.getRole())
                .collect(Collectors.toList());

        Map<String, Object> payload = Map.of(
                "sub", accountId,
                "roles", roles,
                "type", Env.ACCESS_TOKEN_TYPE
        );

        String accessToken = this.authTokenService.generateAccessToken(payload);

        Optional<AuthTokenPair> rs = this.authTokenPairService.findByRefreshToken(refreshToken);

        if(rs.isEmpty())
            return new ResponseEntity<>(
                    ResponseBodyFactoryV1.unauthorizedResponseBody(
                            new FailedBodyContentV1(Env.UNAUTHORIZED_EXCEPTION_TYPE, "Invalid refresh token.")
                    ),
                    HttpStatus.UNAUTHORIZED
            );

        AuthTokenPair authTokenPair = rs.get();

        BlackListAuthToken blackListAuthToken = new BlackListAuthToken();
        blackListAuthToken.setCode(authTokenPair.getAccessToken());

        this.blackListAuthTokenService.saveOrUpdate(blackListAuthToken);

        authTokenPair.setAccessToken(accessToken);

        this.authTokenPairService.saveOrUpdate(authTokenPair);

        HttpHeaders headers = new HttpHeaders();

        headers.set("access-token", accessToken);

        return new ResponseEntity<>(
                ResponseBodyFactoryV1.createdResponseBody(),
                headers,
                HttpStatus.CREATED
        );
    }
}












































