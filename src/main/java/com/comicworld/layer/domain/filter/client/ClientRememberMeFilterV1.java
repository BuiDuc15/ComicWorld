package com.comicworld.layer.domain.filter.client;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.comicworld.layer.domain.entity.account.ClientAccount;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.model.user_details.ClientDetailsModelV1;
import com.comicworld.layer.domain.service.auth_token.AuthTokenService;
import com.comicworld.layer.domain.service.client.account.ClientAccountService;
import com.comicworld.utils.Env;
import com.comicworld.utils.ResponseUtils;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component("clientRememberMeFilterV1")
public class ClientRememberMeFilterV1 extends OncePerRequestFilter {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("authTokenServiceImplV1")
    private AuthTokenService authTokenService;

    @Autowired
    @Qualifier("clientAccountServiceImplV1")
    private ClientAccountService clientAccountService;

    private Map<String, String> protectedEndPoints = ImmutableMap.<String, String> builder()
                                                                .put("/v1/authentication/signout-POST", "POST")
                                                                .put("/v1/comics/*/chapters/*/comments-POST", "POST")
                                                                .put("/v1/comics/*/comments-POST", "POST")
                                                                .put("/v1/comments/*-PUT", "PUT")
                                                                .put("/v1/comments/*-DELETE", "DELETE")
                                                                .put("/v1/comments/*/upvote-PUT", "PUT")
                                                                .put("/v1/comments/*/dislike-PUT", "PUT")
                                                                .put("/v1/comments/*/replies-POST", "POST")
                                                                .put("/v1/replies/*-PUT", "PUT")
                                                                .put("/v1/replies/*-DELETE", "DELETE")
                                                                .put("/v1/replies/*/upvote-PUT", "PUT")
                                                                .put("/v1/replies/*/dislike-PUT", "PUT")
                                                                .put("/v1/comics/*/dislike-PUT", "PUT")
                                                                .put("/v1/comics/*/love-PUT", "PUT")
                                                                .build();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String endPoint = request.getRequestURI()
                                .replaceAll("/\\d+/{0,1}", "*")
                                .replaceAll("\\*", "/*/");

        if(endPoint.endsWith("/")) endPoint = endPoint.substring(0, endPoint.length() - 1);

        String method = request.getMethod();

        String finalEndpoint = endPoint + "-" + method;

        System.out.println(finalEndpoint);

        if(protectedEndPoints.containsKey(finalEndpoint)) {
            System.out.println(finalEndpoint + "  KAKAKKAKAKA");

            String authorizationHeader = request.getHeader("Authorization");

            if(authorizationHeader == null) {
                String responseBody = this.objectMapper.writeValueAsString(
                        ResponseBodyFactoryV1.badRequestResponseBody(
                                new FailedBodyContentV1(Env.BAD_REQUEST_EXCEPTION_TYPE,
                                        "Authorization header is missing.")
                        )
                );
                ResponseUtils.doResetResponseToErrorState(response, 400, responseBody);
                return;
            }

            String accessToken = authorizationHeader.substring("Bearer ".length());

            if(!this.authTokenService.isAccessTokenValid(accessToken)) {
                String responseBody = this.objectMapper.writeValueAsString(
                        ResponseBodyFactoryV1.unauthorizedResponseBody(
                                new FailedBodyContentV1(Env.UNAUTHORIZED_EXCEPTION_TYPE,
                                        "Invalid access token.")
                        )
                );
                ResponseUtils.doResetResponseToErrorState(response, 401, responseBody);
                return;
            }

            DecodedJWT decodedJWT = this.authTokenService.decodeToken(accessToken);

            Optional<ClientAccount> rs = this.clientAccountService.findByIdWithAuthoritiesLoadedEagerly(Long.parseLong(decodedJWT.getSubject()));

            if(rs.isEmpty()) {
                String responseBody = this.objectMapper.writeValueAsString(
                        ResponseBodyFactoryV1.unauthorizedResponseBody(
                                new FailedBodyContentV1(Env.UNAUTHORIZED_EXCEPTION_TYPE,
                                        "Invalid access token.")
                        )
                );
                ResponseUtils.doResetResponseToErrorState(response, 401, responseBody);
                return;
            }

            ClientDetailsModelV1 clientDetailsModel = new ClientDetailsModelV1(rs.get());

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(clientDetailsModel, null, clientDetailsModel.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        }
        else filterChain.doFilter(request, response);
    }
}
