package com.comicworld.layer.domain.filter.admin;

import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminUsernamePasswordAuthenticationFilterV1 extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public AdminUsernamePasswordAuthenticationFilterV1(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        JSONObject credentials = null;
        String username = null;
        String password = null;

        try {
            credentials = new JSONObject(new String(request.getInputStream().readAllBytes()));
            username = credentials.getString("username");
            password = credentials.getString("password");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

//        String username = request.getParameter("username");
//        String password = request.getParameter("password");

        String method = request.getMethod();

        if(username == null || password == null)
            throw new AuthenticationServiceException("Missing credential parameters.");

        if(!method.equalsIgnoreCase("POST"))
            throw new AuthenticationServiceException("Invalid HTTP method.");

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password, null);

        return this.authenticationManager.authenticate(authentication);
    }
}
