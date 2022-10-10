package com.comicworld.layer.domain.service.client.check_account_existence;

import com.comicworld.layer.domain.service.client.account.ClientAccountService;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("checkClientAccountExistenceServiceImplV1")
public class CheckClientAccountExistenceServiceImplV1 implements CheckClientAccountExistenceService {

    @Autowired
    @Qualifier("clientAccountServiceImplV1")
    private ClientAccountService clientAccountService;

    @Override
    public ResponseEntity<Object> check(String username) {
        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(
                this.clientAccountService.existsByUsername(username)
        ), HttpStatus.OK);
    }
}
