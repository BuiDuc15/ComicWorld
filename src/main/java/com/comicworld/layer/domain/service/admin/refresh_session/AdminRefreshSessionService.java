package com.comicworld.layer.domain.service.admin.refresh_session;

import org.springframework.http.ResponseEntity;

public interface AdminRefreshSessionService {

    public ResponseEntity<Object> refreshSession(String refreshToken);

}
