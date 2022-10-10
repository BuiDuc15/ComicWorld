package com.comicworld.layer.domain.service.admin.auth_success_handler;

import com.comicworld.layer.domain.entity.AuthTokenPair;
import com.comicworld.layer.domain.entity.account.AdminAccount;
import com.comicworld.layer.domain.model.user_details.AdminDetailsModelV1;
import com.comicworld.layer.domain.service.auth_token.AuthTokenService;
import com.comicworld.layer.domain.service.auth_token_pair.AuthTokenPairService;
import com.comicworld.utils.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("adminAuthenticationSuccessHandlerV1")
public class AdminAuthenticationSuccessHandlerV1 implements AuthenticationSuccessHandler {

    @Autowired
    @Qualifier("authTokenServiceImplV1")
    private AuthTokenService authTokenService;

    @Autowired
    @Qualifier("authTokenPairServiceImplV1")
    private AuthTokenPairService authTokenPairService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        AdminDetailsModelV1 adminDetails = (AdminDetailsModelV1) authentication.getPrincipal();

        AdminAccount adminAccount = adminDetails.getAccount();

        List<String> authorities = adminAccount.getAuthorities().stream()
                .map(authority -> authority.getRole())
                .collect(Collectors.toList());

        Map<String, Object> accessTokenPayload = Map.of(
                "sub", adminAccount.getId(),
                "roles", authorities,
                "type", Env.ACCESS_TOKEN_TYPE
        );

        String accessToken = this.authTokenService.generateAccessToken(accessTokenPayload);

        Map<String, Object> refreshTokenPayload = Map.of(
                "sub", adminAccount.getId(),
                "roles", authorities,
                "type", Env.REFRESH_TOKEN_TYPE
        );

        String refreshToken = this.authTokenService.generateRefreshToken(refreshTokenPayload);

        response.addHeader("access-token", accessToken);
        response.addHeader("refresh-token", refreshToken);

        AuthTokenPair authTokenPair = new AuthTokenPair();

        authTokenPair.setAccessToken(accessToken);
        authTokenPair.setRefreshToken(refreshToken);

        this.authTokenPairService.saveOrUpdate(authTokenPair);

        response.setStatus(200);
    }

}

































