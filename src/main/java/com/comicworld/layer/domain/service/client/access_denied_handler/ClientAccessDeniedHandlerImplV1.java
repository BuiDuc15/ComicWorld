package com.comicworld.layer.domain.service.client.access_denied_handler;

import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.utils.Env;
import com.comicworld.utils.ResponseUtils;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("clientAccessDeniedHandlerImplV1")
public class ClientAccessDeniedHandlerImplV1 implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String responseBody = this.objectMapper.writeValueAsString(
                ResponseBodyFactoryV1.badRequestResponseBody(
                        new FailedBodyContentV1(Env.ACCESS_DENIED_EXCEPTION_TYPE,
                                "Access Denied")
                )
        );
        ResponseUtils.doResetResponseToErrorState(response, 403, responseBody);
        return;
    }
}







































