package com.comicworld.layer.application.controller.client;

import com.comicworld.layer.domain.service.otp.send.OTPSendingService;
import com.comicworld.layer.domain.service.otp.validate.OTPValidatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OtpControllerV1 {

    @Autowired
    @Qualifier("otpSendingServiceImplV1")
    private OTPSendingService otpSendingService;

    @Autowired
    @Qualifier("otpValidatingServiceImplV1")
    private OTPValidatingService otpValidatingService;

    @PostMapping(path = "/v1/client-accounts/{accountId}/otp")
    public ResponseEntity<Object> sendOTP(@PathVariable("accountId") Long accountId) {
        return this.otpSendingService.send(accountId);
    }

    @GetMapping(path = "/v1/client-accounts/{accountId}/otp/validation")
    public ResponseEntity<Object> validateOTP(@PathVariable("accountId") Long accountId,
                                              @RequestParam("otp") String otp) {
        return this.otpValidatingService.validate(accountId, otp);
    }

}
