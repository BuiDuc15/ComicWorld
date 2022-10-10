package com.comicworld.layer.domain.service.admin.auth_failure_handler;

import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.utils.Env;
import com.comicworld.utils.ResponseUtils;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("adminAuthenticationFailureHandlerV1")
public class AdminAuthenticationFailureHandlerV1 implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(exception instanceof BadCredentialsException) {
            String responseBody = this.objectMapper.writeValueAsString(
                    ResponseBodyFactoryV1.unauthorizedResponseBody(
                            new FailedBodyContentV1(Env.UNAUTHORIZED_EXCEPTION_TYPE,
                                    "Username or password is incorrect.")
                    )
            );
            ResponseUtils.doResetResponseToErrorState(response, 401, responseBody);
            return;
        }
        else if(exception instanceof AuthenticationServiceException) {
            String responseBody = this.objectMapper.writeValueAsString(
                    ResponseBodyFactoryV1.badRequestResponseBody(
                            new FailedBodyContentV1(Env.BAD_REQUEST_EXCEPTION_TYPE,
                                    exception.getMessage())
                    )
            );
            ResponseUtils.doResetResponseToErrorState(response, 400, responseBody);
            return;
        }
    }

}


























