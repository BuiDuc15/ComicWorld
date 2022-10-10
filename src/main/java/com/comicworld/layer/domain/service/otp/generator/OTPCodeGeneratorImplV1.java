package com.comicworld.layer.domain.service.otp.generator;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service("otpCodeGeneratorImplV1")
public class OTPCodeGeneratorImplV1 implements OTPCodeGenerator {

    @Override
    public String generateCode() {
        Random random = new Random();

        return String.valueOf(random.ints(10000000, 100000000)
                .findFirst()
                .getAsInt());
    }
}
