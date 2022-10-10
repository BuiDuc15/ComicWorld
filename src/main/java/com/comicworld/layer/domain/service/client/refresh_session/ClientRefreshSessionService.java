package com.comicworld.layer.domain.service.client.refresh_session;

import org.springframework.http.ResponseEntity;

public interface ClientRefreshSessionService {

    public ResponseEntity<Object> refresh(String refreshToken);

}
