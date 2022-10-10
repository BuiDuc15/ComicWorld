package com.comicworld.layer.domain.service.otp.validate;

import org.springframework.http.ResponseEntity;

public interface OTPValidatingService {

    public ResponseEntity<Object> validate(Long receiverId, String code);

}
