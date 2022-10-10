package com.comicworld.layer.domain.service.client.change_password;

import org.springframework.http.ResponseEntity;

public interface PasswordChangingService {

    public ResponseEntity<Object> changePassword(Long accountId, String newPassword);

}
