package com.comicworld.layer.domain.service.admin.sign_out;

import org.springframework.http.ResponseEntity;

public interface AdminSignOutService {

    public ResponseEntity<Object> signOut(String authorizationHeader);

}
