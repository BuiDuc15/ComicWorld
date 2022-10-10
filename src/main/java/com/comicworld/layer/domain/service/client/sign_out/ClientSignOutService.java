package com.comicworld.layer.domain.service.client.sign_out;

import org.springframework.http.ResponseEntity;

public interface ClientSignOutService {

    public ResponseEntity<Object> signOut(String authorizationHeader);

}
