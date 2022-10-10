package com.comicworld.layer.domain.service.otp.validate;

import com.comicworld.layer.domain.entity.OTP;
import com.comicworld.layer.domain.entity.account.ClientAccount;
import com.comicworld.layer.domain.service.otp.crud.OTPService;
import com.comicworld.utils.TimeUtils;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("otpValidatingServiceImplV1")
public class OTPValidatingServiceImplV1 implements OTPValidatingService {

    @Autowired
    @Qualifier("otpServiceImplV1")
    private OTPService otpService;

    @Override
    public ResponseEntity<Object> validate(Long receiverId, String code) {
        Optional<OTP> rs = this.otpService.findByCodeWithAllRelationshipsLoadedEagerly(code);

        if(rs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(false), HttpStatus.OK);

        OTP otp = rs.get();

        ClientAccount account = otp.getAccount();

        if(account.getId().compareTo(receiverId) != 0)
            return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(false), HttpStatus.OK);

        if(!otp.getValid())
            return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(false), HttpStatus.OK);

        if(TimeUtils.getCurrentUTCTimeInMilliseconds() >= otp.getExpiresAt())
            return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(false), HttpStatus.OK);

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(true), HttpStatus.OK);
    }
}













































