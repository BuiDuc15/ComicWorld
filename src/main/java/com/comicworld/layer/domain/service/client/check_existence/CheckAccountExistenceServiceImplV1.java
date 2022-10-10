package com.comicworld.layer.domain.service.client.check_existence;

import com.comicworld.layer.domain.entity.account.ClientAccount;
import com.comicworld.layer.domain.service.client.account.ClientAccountService;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("checkAccountExistenceServiceImplV1")
public class CheckAccountExistenceServiceImplV1 implements CheckAccountExistenceService {

    @Autowired
    @Qualifier("clientAccountServiceImplV1")
    private ClientAccountService clientAccountService;

    @Override
    public ResponseEntity<Object> check(String username) {
        Optional<ClientAccount> rs = this.clientAccountService.findByUsernameWithAuthoritiesLoadedLazily(username);

        if(rs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(false), HttpStatus.OK);

        HttpHeaders headers = new HttpHeaders();

        headers.set("id", rs.get().getId().toString());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(true), headers, HttpStatus.OK);
    }
}
