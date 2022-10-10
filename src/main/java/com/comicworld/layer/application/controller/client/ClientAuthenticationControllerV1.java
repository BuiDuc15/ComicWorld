package com.comicworld.layer.application.controller.client;

import com.comicworld.layer.domain.dto.account.ClientAccountInDTO;
import com.comicworld.layer.domain.service.client.refresh_session.ClientRefreshSessionService;
import com.comicworld.layer.domain.service.client.sign_out.ClientSignOutService;
import com.comicworld.layer.domain.service.client.sign_up.ClientSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientAuthenticationControllerV1 {

    @Autowired
    @Qualifier("clientRefreshSessionServiceImplV1")
    private ClientRefreshSessionService clientRefreshSessionService;

    @Autowired
    @Qualifier("clientSignOutServiceImplV1")
    private ClientSignOutService clientSignOutService;

    @Autowired
    @Qualifier("clientSignUpServiceImplV1")
    private ClientSignUpService clientSignUpService;

    @PostMapping(path = "/v1/access-token")
    public ResponseEntity<Object> refreshAccessToken(@RequestHeader(value = "refresh-token",
                                                        required = false,
                                                        defaultValue = "") String refreshToken) {
        return this.clientRefreshSessionService.refresh(refreshToken);
    }

    @PostMapping(path = "/v1/authentication/signout")
    public ResponseEntity<Object> signOut(@RequestHeader(value = "Authorization") String authorizationHeader) {
        return this.clientSignOutService.signOut(authorizationHeader);
    }

    @PostMapping(path = "/v1/authentication/signup")
    public ResponseEntity<Object> signUp(@RequestBody ClientAccountInDTO clientAccountInDTO) {
        return this.clientSignUpService.signUp(clientAccountInDTO);
    }

}
