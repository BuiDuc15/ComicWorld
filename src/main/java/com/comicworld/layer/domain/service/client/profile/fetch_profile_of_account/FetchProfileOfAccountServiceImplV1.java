package com.comicworld.layer.domain.service.client.profile.fetch_profile_of_account;

import com.comicworld.layer.domain.dto.profile.ClientProfileOutDTO;
import com.comicworld.layer.domain.entity.profile.ClientProfile;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.client.profile.crud.ClientProfileService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("fetchProfileOfAccountServiceImplV1")
public class FetchProfileOfAccountServiceImplV1 implements FetchProfileOfAccountService {

    @Autowired
    @Qualifier("clientProfileServiceImplV1")
    private ClientProfileService clientProfileService;

    @Override
    public ResponseEntity<Object> fetch(Long accountId) {

        Optional<ClientProfile> rs = this.clientProfileService.findByAccountId(accountId);

        if(rs.isEmpty())
            return new ResponseEntity<>(
                    ResponseBodyFactoryV1.unprocessableResponseBody(new FailedBodyContentV1(
                            Env.UNPROCESSABLE_EXCEPTION_TYPE,
                            "Profile of account with ID " + accountId + " is not found."
                    )),
                    HttpStatus.UNPROCESSABLE_ENTITY
            );

        ClientProfile clientProfile = rs.get();

        clientProfile.setAccount(null);

        return new ResponseEntity<>(
                ResponseBodyFactoryV1.succeededResponseBody(
                        new ClientProfileOutDTO(clientProfile)
                ),
                HttpStatus.OK
        );
    }
}






































