package com.comicworld.layer.application.controller.client;

import com.comicworld.layer.domain.service.client.change_password.PasswordChangingService;
import com.comicworld.layer.domain.service.client.check_account_existence.CheckClientAccountExistenceService;
import com.comicworld.layer.domain.service.client.check_existence.CheckAccountExistenceServiceImplV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientAccountControllerV1 {

    @Autowired
    @Qualifier("checkAccountExistenceServiceImplV1")
    private CheckAccountExistenceServiceImplV1 checkAccountExistenceServiceImplV1;

    @Autowired
    @Qualifier("passwordChangingServiceImplV1")
    private PasswordChangingService passwordChangingService;

    @GetMapping(path = "/v1/client-accounts/existence")
    public ResponseEntity<Object> checkAccountExistence(@RequestParam("username") String username) {
        return this.checkAccountExistenceServiceImplV1.check(username);
    }

    @PutMapping(path = "/v1/client-accounts/{accountId}/reset-password")
    public ResponseEntity<Object> resetAccountPassword(@PathVariable("accountId") Long accountId,
                                                       String newPassword) {
        return this.passwordChangingService.changePassword(accountId, newPassword);
    }


}
