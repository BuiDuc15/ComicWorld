package com.comicworld.layer.domain.service.otp.send;

import org.springframework.http.ResponseEntity;

public interface OTPSendingService {

    public ResponseEntity<Object> send(Long receiverId);

}
