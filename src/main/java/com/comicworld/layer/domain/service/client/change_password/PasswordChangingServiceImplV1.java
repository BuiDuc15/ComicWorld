package com.comicworld.layer.domain.service.client.change_password;

import com.comicworld.layer.domain.entity.account.ClientAccount;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.client.account.ClientAccountService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("passwordChangingServiceImplV1")
@Transactional
public class PasswordChangingServiceImplV1 implements PasswordChangingService {

    @Autowired
    @Qualifier("clientAccountServiceImplV1")
    private ClientAccountService clientAccountService;

    @Autowired
    @Qualifier("clientPasswordEncoder")
    private PasswordEncoder clientPasswordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ResponseEntity<Object> changePassword(Long accountId, String newPassword) {
        Optional<ClientAccount> rs = this.clientAccountService.findByIdWithAllRelationshipsLoadedLazily(accountId);

        if(rs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Account with ID " + accountId + " does not exist.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        ClientAccount account = rs.get();

        account.setPassword(this.clientPasswordEncoder.encode(newPassword));

        account = this.clientAccountService.saveOrUpdate(account);

        HttpHeaders headers = new HttpHeaders();

        headers.set("id", account.getId().toString());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(), headers, HttpStatus.OK);
    }
}








































