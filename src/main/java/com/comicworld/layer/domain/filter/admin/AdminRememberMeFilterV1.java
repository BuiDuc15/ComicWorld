package com.comicworld.layer.domain.filter.admin;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.comicworld.layer.domain.entity.account.AdminAccount;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.model.user_details.AdminDetailsModelV1;
import com.comicworld.layer.domain.service.admin.account.AdminAccountService;
import com.comicworld.layer.domain.service.auth_token.AuthTokenService;
import com.comicworld.utils.Env;
import com.comicworld.utils.ResponseUtils;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Component("adminRememberMeFilterV1")
public class AdminRememberMeFilterV1 extends OncePerRequestFilter {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("authTokenServiceImplV1")
    private AuthTokenService authTokenService;

    @Autowired
    @Qualifier("adminAccountServiceImplV1")
    private AdminAccountService adminAccountService;

    private Map<String, String> unprotectedEndPoints = Map.of(
            Env.ADMIN_SIGN_IN_ENDPOINT, "POST",
            "/admin/v1/access-token", "POST"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String endPoint = request.getRequestURI();
        String method = request.getMethod();

        if(!endPoint.startsWith("/admin") ||
            (unprotectedEndPoints.containsKey(endPoint) &&
            unprotectedEndPoints.get(endPoint).equalsIgnoreCase(method)))
            filterChain.doFilter(request, response);
        else {
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

            Optional<AdminAccount> rs = this.adminAccountService.findByIdWithAuthoritiesLoadedEagerly(Long.parseLong(decodedJWT.getSubject()));

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

            AdminDetailsModelV1 adminDetailsModel = new AdminDetailsModelV1(rs.get());

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(adminDetailsModel, null, adminDetailsModel.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        }

    }

}
