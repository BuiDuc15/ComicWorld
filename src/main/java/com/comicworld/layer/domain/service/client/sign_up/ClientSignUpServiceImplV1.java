package com.comicworld.layer.domain.service.client.sign_up;

import com.comicworld.layer.domain.dto.account.ClientAccountInDTO;
import com.comicworld.layer.domain.entity.Authority;
import com.comicworld.layer.domain.entity.account.ClientAccount;
import com.comicworld.layer.domain.entity.profile.ClientProfile;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.authority.AuthorityService;
import com.comicworld.layer.domain.service.client.account.ClientAccountService;
import com.comicworld.layer.domain.service.client.profile.crud.ClientProfileService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("clientSignUpServiceImplV1")
@Transactional
public class ClientSignUpServiceImplV1 implements ClientSignUpService {

    @Autowired
    @Qualifier("clientAccountServiceImplV1")
    private ClientAccountService clientAccountService;

    @Autowired
    @Qualifier("clientProfileServiceImplV1")
    private ClientProfileService clientProfileService;

    @Autowired
    @Qualifier("clientPasswordEncoder")
    private PasswordEncoder clientPasswordEncoder;

    @Autowired
    @Qualifier("authorityServiceImplV1")
    private AuthorityService authorityService;

    @Override
    public ResponseEntity<Object> signUp(ClientAccountInDTO clientAccountInDTO) {
        String username = clientAccountInDTO.getUsername();

        if(this.clientAccountService.existsByUsername(username)) {
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Account with username '" + username + "' has already existed.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Authority authority = this.authorityService.findByRoleWithClientAccountsLoadedEagerly("ROLE_USER").get();

        ClientAccount clientAccount = clientAccountInDTO.toClientAccount();

        clientAccount.setPassword(this.clientPasswordEncoder.encode(clientAccount.getPassword()));

        ClientProfile clientProfile = clientAccount.getClientProfile();

        clientAccount.setAccountType(1);

        clientAccount.getAuthorities().add(authority);

        clientAccount = this.clientAccountService.saveOrUpdate(clientAccount);

        authority.getClientAccounts().add(clientAccount);

        this.authorityService.saveOrUpdate(authority);

        clientProfile.setAccount(clientAccount);

        this.clientProfileService.saveOrUpdate(clientProfile);

        HttpHeaders headers = new HttpHeaders();

        headers.set("id", clientAccount.getId().toString());

        return new ResponseEntity<>(ResponseBodyFactoryV1.createdResponseBody(), headers, HttpStatus.OK);
    }
}
