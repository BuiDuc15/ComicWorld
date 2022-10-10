package com.comicworld.layer.domain.filter.client;

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

public class ClientUsernamePasswordAuthenticationFilterV1 extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public ClientUsernamePasswordAuthenticationFilterV1(AuthenticationManager authenticationManager) {
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
        catch (IOException e) {
            e.printStackTrace();
        }

        String method = request.getMethod();

        if(username == null || password == null) throw new AuthenticationServiceException("Missing credentials.");

        if(!method.equalsIgnoreCase("POST")) throw new AuthenticationServiceException("Invalid HTTP Method.");

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password, null);

        return this.authenticationManager.authenticate(authentication);
    }
}




































