package com.comicworld.layer.application.controller.client;

import com.comicworld.layer.domain.service.client.profile.fetch_profile_of_account.FetchProfileOfAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientProfileControllerV1 {

    @Autowired
    @Qualifier("fetchProfileOfAccountServiceImplV1")
    private FetchProfileOfAccountService fetchProfileOfAccountService;

    @GetMapping(path = "/v1/client-accounts/{accountId}/profile")
    public ResponseEntity<Object> fetchProfileByAccountId(@PathVariable("accountId") Long accountId) {
        return this.fetchProfileOfAccountService.fetch(accountId);
    }

}
