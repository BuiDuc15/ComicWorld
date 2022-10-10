package com.comicworld.layer.domain.service.otp.send;

import com.comicworld.layer.domain.entity.OTP;
import com.comicworld.layer.domain.entity.account.ClientAccount;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.client.account.ClientAccountService;
import com.comicworld.layer.domain.service.mail.MailService;
import com.comicworld.layer.domain.service.otp.crud.OTPService;
import com.comicworld.layer.domain.service.otp.generator.OTPCodeGenerator;
import com.comicworld.utils.ContentUtils;
import com.comicworld.utils.Env;
import com.comicworld.utils.TimeUtils;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service("otpSendingServiceImplV1")
@Transactional
public class OTPSendingServiceImplV1 implements OTPSendingService {

    @Autowired
    @Qualifier("clientAccountServiceImplV1")
    private ClientAccountService clientAccountService;

    @Autowired
    @Qualifier("otpServiceImplV1")
    private OTPService otpService;

    @Autowired
    @Qualifier("otpCodeGeneratorImplV1")
    private OTPCodeGenerator otpCodeGenerator;

    @Autowired
    @Qualifier("gmailServiceImplV1")
    private MailService mailService;

    @Value("${otp.forgot-password.duration.minutes}")
    private Long OTP_DURATION_IN_MINUTES;

    @Override
    public ResponseEntity<Object> send(Long receiverId) {
        Optional<ClientAccount> rs = this.clientAccountService.findByIdWithAllRelationshipsLoadedLazily(receiverId);

        if(rs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Account with ID " + receiverId + " does not exists.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        ClientAccount clientAccount = rs.get();

        if(clientAccount.getOtp() != null) this.otpService.delete(clientAccount.getOtp());

        Long createdAt = TimeUtils.getCurrentUTCTimeInMilliseconds();
        Long expiresAt = createdAt + TimeUtils.fromMinutesToMillis(this.OTP_DURATION_IN_MINUTES);

        OTP otp = new OTP();

        otp.setAccount(clientAccount);
        otp.setCode(this.otpCodeGenerator.generateCode());
        otp.setCreatedAt(createdAt);
        otp.setExpiresAt(expiresAt);
        otp.setValid(true);

        otp = this.otpService.saveOrUpdate(otp);

        Map<String, Object> attributes = new HashMap<>();

        attributes.put("to", clientAccount.getUsername());
        attributes.put("content", ContentUtils.buildContentForOTPMail(Map.of("otp", otp.getCode())));
        attributes.put("subject", "OTP RESET MẬT KHẨU - VUI LÒNG KHÔNG CHIA SẺ MÃ OTP CHO BẤT KỲ AI");
        attributes.put("personal", "ComicWorld Team");

        this.mailService.sendMail(attributes);

        HttpHeaders headers = new HttpHeaders();

        headers.set("id", otp.getId().toString());

        return new ResponseEntity<>(ResponseBodyFactoryV1.createdResponseBody(), headers, HttpStatus.CREATED);
    }
}












































